package m3ales.zenchecker.jsonparser;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Mod {
    public Mod(String id)
    {
        identifier = Util.cleanupPackageNames(id);
        Annotations = new ArrayList<>();
    }
    public Mod(List<Annotation> Annotations)
    {
        this.Annotations = Annotations;
        this.identifier = null;
    }
    @SerializedName("name")
    public String identifier;
    private List<Annotation> Annotations;
    public List<Annotation> getAnnotations()
    {
        return Annotations;
    }
    public void setAnnotations(List<Annotation> annotations)
    {
        this.Annotations = annotations;
    }
}
