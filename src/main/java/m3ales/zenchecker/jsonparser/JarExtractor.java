package m3ales.zenchecker.jsonparser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class JarExtractor {
    //Credit:
    //Source https://github.com/TechReborn/TechReborn/blob/1.12/build.gradle
    //TechReborn: https://github.com/TechReborn/
    public static List<Mod> extractDirectory(File directory)throws Exception
    {
        if(!directory.isDirectory())
            throw new Exception("Provided argument was not a directory");
        File[] jars = directory.listFiles(f -> f.getName().endsWith(".jar"));
        List<Mod> modList = new ArrayList<>();
        for(File jar : jars) {
            modList.addAll(extract(jar));
        }
        return modList;
    }
    public static List<Mod> extract(File jarFile) throws IOException
    {
        System.out.println("Parsing '" + jarFile + "'");
        ZipFile jar = new ZipFile(jarFile);
        ZipEntry annotation_cache = jar.getEntry("META-INF/fml_cache_annotation.json");
        String data = IOUtils.toString(jar.getInputStream(annotation_cache),"UTF-8");
        Gson gson = new GsonBuilder().create();
        JsonObject mods = gson.fromJson(data,JsonObject.class);
        JsonObject clazz = null;
        List<Mod> modList = new ArrayList<>();
        Mod currentMod = null;
        for (Map.Entry<String, JsonElement> entry : mods.entrySet()) {
            //each entry should be a mod package
            //the sub-entry should be list of annotations
            clazz = entry.getValue().getAsJsonObject();
            if(!clazz.has("annotations")) {
                System.out.println(String.format("Skipping, '%s' no valid annotations found",clazz.toString()));
                continue;
            }
            currentMod = gson.fromJson(clazz, Mod.class);
            currentMod.srcJar = jarFile.getName();
            if(currentMod != null) {
                modList.add(currentMod);
                currentMod = null;
            }
        }
        System.out.println(String.format("Jar '%s' parsed. (%s valid mods found)",jarFile.getName(),modList.size()));
        for(Mod m : modList) {
            if(m != null && m.getAnnotations() != null)
            for (Annotation a : m.getAnnotations()) {
                if (a != null)
                    a.cleanupPackageNames();
            }
        }
        return modList;
    }
}
