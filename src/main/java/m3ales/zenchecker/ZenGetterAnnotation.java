package m3ales.zenchecker;

import m3ales.zenchecker.jsonparser.Annotation;

public class ZenGetterAnnotation extends ZenMethodAnnotation {
    public String varName;
    public ZenGetterAnnotation(Annotation jsonAnnotation, ZenClassAnnotation clazz) {
        super(jsonAnnotation, clazz);
        if(jsonAnnotation.value != null)
            varName = jsonAnnotation.value.value;
        else
            varName = qualifiedMethodName.methodName;
    }

    @Override
    public String toZenString() {
        return String.format("%s.%s",qualifiedMethodName.className, varName);
    }

    @Override
    public String toPrettyString() {
        return super.toPrettyString();
    }

    @Override
    public String toQualifiedString() {
        return super.toQualifiedString();
    }
}
