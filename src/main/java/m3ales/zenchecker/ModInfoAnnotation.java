package m3ales.zenchecker;

import m3ales.zenchecker.jsonparser.Annotation;
import m3ales.zenchecker.jsonparser.Util;

public class ModInfoAnnotation extends ParsedAnnotation{
    public String acceptedMinecraftVersions;
    public String dependencies;
    public String modid;
    public String modName;
    public String version;
    public String packageName;
    public String srcJar;
    public ModInfoAnnotation(Annotation jsonAnnotation, String srcJar) {
        super(jsonAnnotation);
        this.acceptedMinecraftVersions = jsonAnnotation.values.acceptedMinecraftVersions.value;
        this.dependencies = jsonAnnotation.values.dependencies.value;
        this.modid = jsonAnnotation.values.modid.value;
        this.modName = jsonAnnotation.values.name.value;
        this.version = jsonAnnotation.values.version.value;
        this.packageName = Util.cleanupPackageNames(jsonAnnotation.target);
        this.srcJar = srcJar;
    }
    public String ToString()
    {
        String sep = "|";
        StringBuilder builder = new StringBuilder("[");
        builder.append("package-name: ").append(packageName).append(sep);
        builder.append("mod-id: ").append(modid).append(sep);
        builder.append("mod-name: ").append(modName).append(sep);
        builder.append("version: ").append(version).append(sep);
        builder.append("mc-version: ").append(acceptedMinecraftVersions).append(sep);
        builder.append("dependencies: ").append(dependencies).append(sep);
        return builder.append("]").toString();
    }
}
