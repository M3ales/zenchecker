package m3ales.zenchecker;

public class QualifiedMethodName extends QualifiedClassName {
    protected String methodName;

    public QualifiedMethodName(String name, QualifiedClassName Clazz) {
        super(name);
        methodName = name.split("[(]")[0];
        packageName = Clazz.packageName;
        className = Clazz.className;
    }

    @Override
    public String getLastFragment() {
        return String.format("%s.%s",super.getLastFragment(), methodName);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public String prettyString() {
        return super.prettyString() + "." + methodName;
    }
}
