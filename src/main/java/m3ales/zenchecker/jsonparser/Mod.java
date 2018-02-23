package m3ales.zenchecker.jsonparser;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Mod {
    public Mod(String id)
    {
        identifier = Util.cleanupPackageNames(id);
        annotations = new ArrayList<>();
    }
    public Mod(List<Annotation> annotations)
    {
        this.annotations = annotations;
        this.identifier = null;
    }
    public String srcJar;
    @SerializedName("name")
    public String identifier;
    private List<Annotation> annotations;
    public List<Annotation> getAnnotations()
    {
        return annotations;
    }
    public void setAnnotations(List<Annotation> annotations)
    {
        this.annotations = annotations;
    }
}
