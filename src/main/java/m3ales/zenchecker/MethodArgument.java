package m3ales.zenchecker;

public class MethodArgument {
    public QualifiedClassName type;
    public int argNumber;

    public MethodArgument(String type, int argNumber) {
        if(type.length() == 1)
            this.type = new QualifiedClassName(ZenMethodAnnotation.getReturnType(type.charAt(0)));
        else
            this.type = new QualifiedClassName(type);
        this.argNumber = argNumber;
    }
    public String getPrettyString()
    {
        return type.className + " " + makeVarname(type.className) + argNumber;
    }
    public String makeVarname(String className)
    {
        if(className.startsWith("I"))
            className = className.substring(1);
        return className.toLowerCase();
    }
    @Override
    public String toString() {
        return type.toString() + " " + type.className.charAt(0) + argNumber;
    }
}