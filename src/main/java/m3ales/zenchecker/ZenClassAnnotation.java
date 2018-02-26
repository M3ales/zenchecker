package m3ales.zenchecker;

import m3ales.zenchecker.jsonparser.Annotation;

public class ZenClassAnnotation extends ParsedAnnotation{
    private ModInfoAnnotation mod;

    public ModInfoAnnotation getMod() {
        return mod;
    }

    public QualifiedClassName qualifiedClassName;
    public ZenClassAnnotation(Annotation jsonAnnotation, ModInfoAnnotation mod) {
        super(jsonAnnotation);
        this.qualifiedClassName = new QualifiedClassName(jsonAnnotation.target);
        this.mod = mod;
    }
    @Override
    public String toZenString() {
        return String.format("mods.%s.%s",mod.modid,qualifiedClassName.className);
    }

    @Override
    public String toPrettyString() {
        return qualifiedClassName.className;
    }

    @Override
    public String toQualifiedString() {
        return super.toQualifiedString();
    }
}
