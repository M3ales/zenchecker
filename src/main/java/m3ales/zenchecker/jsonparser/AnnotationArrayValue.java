package m3ales.zenchecker.jsonparser;


import java.util.Map;

public class AnnotationArrayValue {
    public AnnotationValue acceptedMinecraftVersions;
    public AnnotationValue dependencies;
    public AnnotationValue modid;
    public AnnotationValue name;
    public AnnotationValue version;
    public boolean isModHeader()
    {
        return acceptedMinecraftVersions != null && dependencies != null && modid != null && name != null && version != null;
    }
}
