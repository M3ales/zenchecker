package m3ales.zenchecker;

import m3ales.zenchecker.jsonparser.Annotation;
import m3ales.zenchecker.jsonparser.Mod;


public class ParsedAnnotationFactory {
    private Annotation annotation;
    private Mod sourceMod;
    public ParsedAnnotationFactory setSourceMod(Mod m){
        this.sourceMod = m;
        return this;
    }
    public ParsedAnnotationFactory setAnnotation(Annotation annotation)
    {
        this.annotation = annotation;
        return this;
    }
    private static ModInfoAnnotation currentMod;//TODO check that the modinfo is always the header
    private static ZenClassAnnotation currentClass;
    public static void clearStaticInfo()
    {
        currentMod = null;
        currentClass = null;
    }
    public ParsedAnnotation create()
    {
        //ModInfo
        switch(annotation.getZenType())
        {
            case FORGE_MOD:
            {
                currentMod = new ModInfoAnnotation(annotation, sourceMod.srcJar);
                return currentMod;
            }
            case CLASS:
            {
                currentClass = new ZenClassAnnotation(annotation,currentMod);
                return currentClass;
            }
            case METHOD:
            {
                return new ZenMethodAnnotation(annotation, currentClass);
            }
            case SETTER:
            {
                return new ZenSetterAnnotation(annotation,currentClass);
            }
            case GETTER:
            {
                return new ZenGetterAnnotation(annotation, currentClass);
            }
            //<minecraft:item>.gette
        }
        return null;
    }
}
