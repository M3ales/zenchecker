package m3ales.zenchecker;

import m3ales.zenchecker.jsonparser.Annotation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class ParsedAnnotation {
    public ParsedAnnotation(Annotation jsonAnnotation)
    {
        //do the magics
    }
    private ParsedAnnotation(){}
    public String toZenString()
    {
        return "NOT_IMPLEMENTED";
    }
    public String toPrettyString()
    {
        return "NOT_IMPLEMENTED";
    }
    public String toQualifiedString()
    {
        return "NOT_IMPLEMENTED";
    }
    public static List<ParsedAnnotation> filterByClassName(List<ParsedAnnotation> annotationList, String search)
    {
        List<ParsedAnnotation> result = annotationList.stream().filter((ParsedAnnotation a) ->
        {
            if(a instanceof ZenGetterAnnotation)
                return ((ZenGetterAnnotation) a).qualifiedMethodName.className.compareToIgnoreCase(search) == 0;

            if(a instanceof ZenMethodAnnotation){
                return ((ZenMethodAnnotation) a).containingClass.qualifiedClassName.className.compareToIgnoreCase(search) == 0;
            }
            if(a instanceof ZenClassAnnotation)
                return ((ZenClassAnnotation) a).qualifiedClassName.className.compareToIgnoreCase(search) == 0;
            return false;
        }).collect(Collectors.toList());
        return result;
    }
}
