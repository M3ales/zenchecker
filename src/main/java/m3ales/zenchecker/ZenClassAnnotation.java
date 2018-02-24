package m3ales.zenchecker;

import m3ales.zenchecker.jsonparser.Annotation;

public class ZenClassAnnotation extends ParsedAnnotation{
    private ModInfoAnnotation mod;

    public ModInfoAnnotation getMod() {
        return mod;
    }

    public QualifiedClassName className;
    public ZenClassAnnotation(Annotation jsonAnnotation, ModInfoAnnotation mod) {
        super(jsonAnnotation);
        this.className = new QualifiedClassName(jsonAnnotation.target);
        this.mod = mod;
    }
}
