package m3ales.zenchecker;

public class QualifiedClassName {
    public QualifiedClassName(String name)
    {
        packageName = "";
        className = "";
        System.out.println("Name: " + name);
        if(name.contains("(") || name.contains(")"))
            return;//ignore, it's a method
        if(name.contains(".")){
            String[] fragments = name.split("[.]");
            if(fragments[fragments.length-1].matches("[A-Z][a-zA-Z0-9]*"))
            {
                className = fragments[fragments.length-1];
                //classname
                for (int i = 0; i < fragments.length-1; i++) {
                    System.out.println(fragments[i]);
                    if(fragments[i] != null)
                        if(i != fragments.length-2)
                            packageName += fragments[i] + ".";
                        else
                            packageName += fragments[i];
                }
            }
        }else
        {
            //primative
            packageName = "primative";
            className = name;
        }
    }

    protected String packageName;
    protected String className;
    public String getPackageName()
    {
        return packageName;
    }
    public String getLastFragment(){
        return "." + className;
    }
    public String getFullName()
    {
        return packageName + getLastFragment();
    }

    @Override
    public String toString() {
        return packageName + getLastFragment();
    }
    public String prettyString()
    {
        return className;
    }
}
