package m3ales.zenchecker;

import com.sun.istack.internal.NotNull;
import m3ales.zenchecker.jsonparser.Annotation;

import java.util.ArrayList;
import java.util.List;

public class ZenMethodAnnotation extends ParsedAnnotation {
    public QualifiedMethodName qualifiedMethodName;
    public String returnType;
    public List<MethodArgument> argumentList;
    protected ZenClassAnnotation containingClass;
    public ZenMethodAnnotation(Annotation jsonAnnotation, @NotNull ZenClassAnnotation containingClass) {
        super(jsonAnnotation);
        this.containingClass = containingClass;
        argumentList = new ArrayList<>();
        this.returnType = getReturnType(jsonAnnotation.target.substring(jsonAnnotation.target.lastIndexOf(")")+1));
        String[] params = jsonAnnotation.target.split("[(]")[1].split("[)]")[0].split(";");
        for (int i = 0; i < params.length; i++) {
            argumentList.add(new MethodArgument(params[i],i));
        }
        qualifiedMethodName = new QualifiedMethodName(jsonAnnotation.target,containingClass.qualifiedClassName);
        System.out.println(qualifiedMethodName);
    }

    public ZenClassAnnotation getContainingClass() {
        return containingClass;
    }

    @Override
    public String toPrettyString()
    {
        StringBuilder builder = new StringBuilder(returnType);
        builder.append(" ").append(qualifiedMethodName.prettyString()).append("(");
        for(MethodArgument arg : argumentList){
            builder.append(arg.getPrettyString());
            if(argumentList.size()-1 != argumentList.indexOf(arg))
                builder.append(", ");
        }
        builder.append(")");
        return builder.toString();
    }
    @Override
    public String toQualifiedString()
    {
        StringBuilder builder = new StringBuilder(returnType);
        builder.append(" ").append(qualifiedMethodName.getFullName()).append("(");
        for(MethodArgument arg : argumentList){
            builder.append(arg);
            if(argumentList.size()-1 != argumentList.indexOf(arg))
                builder.append(", ");
        }
        builder.append(")");
        return builder.toString();
    }
    @Override
    public String toZenString()
    {
        StringBuilder builder = new StringBuilder(returnType);
        builder.append(" ").append("mods").append(".").append(containingClass.getMod().modid).append(".")
                .append(containingClass.qualifiedClassName.prettyString()).append(".").append(qualifiedMethodName.methodName).append("(");
        for(MethodArgument arg : argumentList){
            builder.append(arg.getPrettyString());
            if(argumentList.size()-1 != argumentList.indexOf(arg))
                builder.append(", ");
        }
        builder.append(")");
        return builder.toString();
    }

    @Override
    public String toString() {
        return "ZenMethodAnnotation{" +
                "qualifiedMethodName=" + qualifiedMethodName +
                ", returnType='" + returnType + '\'' +
                ", argumentList=" + argumentList +
                ", containingClass=" + containingClass +
                '}';
    }

    public static String getReturnType(String fragment) {
        //reference:
    /*
    String humanizeArg(String arg){
	def primitiveMap =  [
	    Z: "Boolean",
		B: "Byte",
		C: "Char",
		D: "Double",
		F: "Float",
		I: "Integer",
		J: "Long",
		L: "Object",
		S: "Short"
	]
	from https://github.com/TechReborn/TechReborn/blob/1.12/build.gradle
     */
        if (fragment.length() == 1) {
            switch (fragment.charAt(0)) {
                case 'Z': {
                    return "boolean";
                }
                case 'B': {
                    return "byte";
                }
                case 'C': {
                    return "char";
                }
                case 'D': {
                    return "double";
                }
                case 'F': {
                    return "float";
                }
                case 'I': {
                    return "int";
                }
                case 'J': {
                    return "long";
                }
                case 'L': {
                    return "object";
                }
                case 'S': {
                    return "short";
                }
                case 'V': {
                    return "void";
                }
            }
            //System.out.println("Undefined type " + fragment);
            return String.format("UNDEFINED(%s)",fragment);
        }
        else
        {
            if(fragment.charAt(0) == 'L')
                return fragment.substring(1);
            return fragment;
        }
    }
}
