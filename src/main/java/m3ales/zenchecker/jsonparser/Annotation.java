package m3ales.zenchecker.jsonparser;

import m3ales.zenchecker.*;

public class Annotation {

    public static final String ZEN_REGISTER = "Lcrafttweaker.annotations.ZenRegister;";
    public static final String ZEN_CLASS = "Lstanhebben.zenscript.annotations.ZenClass;";
    public static final String ZEN_METHOD = "Lstanhebben.zenscript.annotations.ZenMethod;";
    public static final String MOD_ONLY = "Lcrafttweaker.annotations.ModOnly;";
    public static final String ZEN_EXPANSION = "Lstanhebben.zenscript.annotations.ZenExpansion;";
    public static final String FORGE_MOD = "Lnet.minecraftforge.fml.common.Mod;";

    public Annotation(AnnotationType type, String name, String target, AnnotationValue value)
    {
        this.type = type;
        this.name = Util.cleanupPackageNames(name);
        this.target = Util.cleanupPackageNames(target);
        this.value = value;
    }

    public void cleanupPackageNames()
    {
        name = Util.cleanupPackageNames(name);
        target = Util.cleanupPackageNames(target);
    }

    public AnnotationType type;
    public String name;
    public String target;
    public AnnotationValue value;
    public AnnotationArrayValue values;

    public ZenType getZenType()
    {
        switch(name){
            case ZEN_METHOD:
            {
                return ZenType.METHOD;
            }
            case ZEN_CLASS:
            {
                return ZenType.CLASS;
            }
            case MOD_ONLY:
            {
                return ZenType.MOD_ONLY;
            }
            case ZEN_REGISTER:
            {
                return ZenType.REGISTER;
            }
            case ZEN_EXPANSION:
            {
                return ZenType.EXPANSION;
            }
            case FORGE_MOD:
            {
                return ZenType.FORGE_MOD;
            }
            default:
            {
                System.out.println("UNHANDLED TYPE: " + name);
                return ZenType.NULL;
                //throw new IllegalStateException(String.format("Name '%s' did not match valid ZenTypes", name));
            }
        }
    }
}
