package m3ales.zenchecker;

import m3ales.zenchecker.jsonparser.Annotation;

public class ZenMethodAnnotation extends ParsedAnnotation {

    protected ZenClassAnnotation containingClass;
    public ZenMethodAnnotation(Annotation jsonAnnotation, ZenClassAnnotation containingClass) {
        super(jsonAnnotation);
        this.containingClass = containingClass;
    }
}
