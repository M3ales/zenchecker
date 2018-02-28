package m3ales.zenchecker;

import m3ales.zenchecker.jsonparser.Annotation;

import java.util.stream.Collectors;

public class ZenSetterAnnotation extends ZenMethodAnnotation {
    public String varName;

    public ZenSetterAnnotation(Annotation jsonAnnotation, ZenClassAnnotation clazz) {
        super(jsonAnnotation, clazz);
        if(jsonAnnotation.value != null)
            varName = jsonAnnotation.value.value;
        else
            varName = qualifiedMethodName.methodName;
    }
    @Override
    public String toZenString() {
        return String.format("%s.%s accepts %s", qualifiedMethodName.className, varName, argumentList.stream().map(a->a.type.className).collect(Collectors.joining(", ")));
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
