package m3ales.zenchecker;

import m3ales.zenchecker.jsonparser.Annotation;
import m3ales.zenchecker.jsonparser.Util;

public class ModInfo extends ParsedAnnotation{
    public String acceptedMinecraftVersions;
    public String dependancies;
    public String modid;
    public String modName;
    public String version;
    public String packageName;
    public ModInfo(Annotation jsonAnnotation) {
        super(jsonAnnotation);
        this.packageName = Util.cleanupPackageNames(jsonAnnotation.target);
        //this.modName = jsonAnnotation.values.getNestedValue("name");
        //this.acceptedMinecraftVersions = jsonAnnotation.values.getNestedValue("acceptedMinecraftVersions");
    }
}
