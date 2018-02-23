package m3ales.zenchecker.jsonparser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import m3ales.zenchecker.jsonparser.Mod;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
            System.out.println("Mod Name :: " + entry.getKey());
            clazz = entry.getValue().getAsJsonObject();
            if(!clazz.has("annotations")) {
                System.out.println("Skipping, no valid annotations found");
                continue;
            }
                currentMod = new Mod(entry.getKey());
                currentMod.setAnnotations(gson.fromJson(clazz, Mod.class).getAnnotations());
                //currentMod.addAnnotation());
            if(currentMod != null) {
                modList.add(currentMod);
                currentMod = null;
            }
        }
        System.out.println(String.format("Jar '%s' parsed. (%s valid mods found)",jarFile.getName(),modList.size()));
        modList.forEach(x->x.getAnnotations().forEach(a -> a.cleanupPackageNames()));
        return modList;
    }
    /*
    import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import org.apache.commons.io.IOUtils

import java.util.zip.ZipFile;

    //Gets the zenMethod class name from the class json object
    String getZenMetName(JsonObject jsonObject){
        def annoations = jsonObject.get("annotations").asJsonArray
        for(annotation in annoations.toList()){
            if(annotation.asJsonObject.get("type").asString.equals("CLASS") && annotation.asJsonObject.get("name").asString.equals("Lstanhebben/zenscript/annotations/ZenClass;")){
                return annotation.asJsonObject.get("value").asJsonObject.get("value").asString
            }
        }
    }

    String sanitsiseMethodName(String methodSig){
        def builder = new StringBuilder()
        def name = methodSig.split("\\(")[0]
        builder.append(name)
        builder.append("(")

        def methodArgs = methodSig.split("\\(")[1].split("\\)")[0].split(";")
        for(arg in methodArgs){
            def argSlit = arg.split("/")
            def argStr = argSlit[argSlit.length -1]
            //If a class is not in a package I assume its a primitive //TODO any suggestions for a better way to do this?
            if(!arg.contains("/") && !arg.isEmpty()){
                argStr = humanizeArg(argStr)
            }
            builder.append(argStr)
            //Dont add the comma to the last arg
            if(arg != methodArgs[methodArgs.length - 1]){
                builder.append(",")
            }

        }
        builder.append(")")

        return builder.toString()
    }

    //Argumets that are java primitives do not use a freindly name, this method replaces them with something most people will understand
    String humanizeArg(String arg){
        def primitiveMap =  [
        Z: "Boolean",
                B: "Byte",
                C: "Char",
                D: "Double",
                F: "Float",
                I: "Integer",
                J: "Long",
                L: "Object",
                S: "Short"
	]

        def builder = new StringBuilder()
        for(cha in arg.toCharArray()){
            builder.append(primitiveMap.get(cha.toString().toUpperCase()))
            builder.append(",")
        }
        //Removes the last ,
        return builder.toString().substring(0, builder.toString().length() - 1)
    }*/
}
