package m3ales.zenchecker;

import m3ales.zenchecker.jsonparser.Annotation;

public class ModInfo extends ParsedAnnotation{
    public String modName;

    public ModInfo(Annotation jsonAnnotation) {

        super(jsonAnnotation);
    }
}
