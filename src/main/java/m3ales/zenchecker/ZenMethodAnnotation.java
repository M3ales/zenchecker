package m3ales.zenchecker;

import com.sun.istack.internal.NotNull;
import m3ales.zenchecker.jsonparser.Annotation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ZenMethodAnnotation extends ParsedAnnotation {
    QualifiedMethodName methodName;
    public String returnType;
    public List<MethodArgument> argumentList;
    protected ZenClassAnnotation containingClass;
    public ZenMethodAnnotation(Annotation jsonAnnotation, @NotNull ZenClassAnnotation containingClass) {
        super(jsonAnnotation);
        argumentList = new ArrayList<>();
        this.containingClass = containingClass;
        this.returnType = getReturnType(jsonAnnotation.target.charAt(jsonAnnotation.target.length()-1));//TODO return type
        String[] params = jsonAnnotation.target.split("[(]")[1].split("[)]")[0].split(";");
        for (int i = 0; i < params.length; i++) {
            argumentList.add(new MethodArgument(params[i],i));
        }
        methodName = new QualifiedMethodName(jsonAnnotation.target,containingClass.className);
        System.out.println(methodName);
    }
    public String toPrettyMethod()
    {
        StringBuilder builder = new StringBuilder(returnType);
        builder.append(" ").append(methodName.prettyString()).append("(");
        for(MethodArgument arg : argumentList){
            builder.append(arg.getPrettyString());
            if(argumentList.size()-1 != argumentList.indexOf(arg))
                builder.append(", ");
        }
        builder.append(")");
        return builder.toString();
    }
    public String toQualifiedMethod()
    {
        StringBuilder builder = new StringBuilder(returnType);
        builder.append(" ").append(methodName.getFullName()).append("(");
        for(MethodArgument arg : argumentList){
            builder.append(arg);
            if(argumentList.size()-1 != argumentList.indexOf(arg))
                builder.append(", ");
        }
        builder.append(")");
        return builder.toString();
    }
    public String toZenMethod()
    {
        StringBuilder builder = new StringBuilder(returnType);
        builder.append(" ").append("mods").append(".").append(containingClass.mod.modid).append(".")
                .append(containingClass.className.prettyString()).append(".").append(methodName.methodName).append("(");
        for(MethodArgument arg : argumentList){
            builder.append(arg.getPrettyString());
            if(argumentList.size()-1 != argumentList.indexOf(arg))
                builder.append(", ");
        }
        builder.append(")");
        return builder.toString();
    }
    public String toString()
    {
        StringBuilder builder = new StringBuilder("[");
        return builder.toString();
    }
    public static String getReturnType(char fragment)
    {
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
        switch(fragment){
            case 'Z':
            {
                return "boolean";
            }
            case 'B':
            {
                return "byte";
            }
            case 'C':
            {
                return "char";
            }
            case 'D':
            {
                return "double";
            }
            case 'F':
            {
                return "float";
            }
            case 'I':
            {
                return "int";
            }
            case 'J':
            {
                return "long";
            }
            case 'L':
            {
                return "object";
            }
            case 'S':
            {
                return "short";
            }
            case 'V':
            {
                return "void";
            }
        }
        System.out.println("Undefined type " + fragment);
        return "UNDEFINED";
    }
}
