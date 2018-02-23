package m3ales.zenchecker;

import m3ales.zenchecker.jsonparser.Annotation;
import m3ales.zenchecker.jsonparser.Mod;
import m3ales.zenchecker.jsonparser.JarExtractor;
import stanhebben.zenscript.compiler.IEnvironmentGlobal;
import stanhebben.zenscript.impl.GenericCompileEnvironment;
import stanhebben.zenscript.impl.GenericErrorLogger;
import stanhebben.zenscript.impl.GenericFunctions;
import stanhebben.zenscript.impl.GenericRegistry;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ZenMain {
    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 1; i++) {
            List<Mod> modList = JarExtractor.extractDirectory(new File("C:\\Mods"));
            List<ParsedAnnotation> annotationsList = new ArrayList<>();
            ParsedAnnotationFactory factory = new ParsedAnnotationFactory();
            for(Mod m : modList) {
                factory.setSourceMod(m);
                for(Annotation a : m.getAnnotations())
                    annotationsList.add(factory.setAnnotation(a).create());
            }
            System.out.println(((ModInfoAnnotation) annotationsList.stream().filter(a -> a instanceof ModInfoAnnotation).findFirst().get()).ToString());
        }

    }
    private static GenericErrorLogger logger;
    private static GenericCompileEnvironment environment;
    private static GenericRegistry registry;
    private static void setupEnvironment() {
        //Create a compile environment needed for the registry
        environment = new GenericCompileEnvironment();
        //Creates a logger needed for the registry
        logger = new GenericErrorLogger(System.out);
        //Creates the IZenRegistry, needed to store all the ZenClass' and Symbols
         registry = new GenericRegistry(environment, logger);
        //Registers A print function as a global method
        registry.registerGlobal("print", registry.getStaticFunction(GenericFunctions.class, "print", String.class));
        //Creates a IEnvironmentGlobal needed to compile the scripts
        Map<String, byte[]> classes = new HashMap<>();
        IEnvironmentGlobal environmentGlobal = registry.makeGlobalEnvironment(classes);
        //Loads the script file
        File file = new File("script.zs");
        String fileName = file.getName();
        /*FileReader reader = new FileReader(file);
        //Creates a ZenTokener and ZenParsedFile for the file
        ZenTokener parser = new ZenTokener(reader, registry.getCompileEnvironment(), fileName, false);
        ZenParsedFile zenParsedFile = new ZenParsedFile(fileName, fileName, parser, environmentGlobal);
        try {
            //Compiles script
            ZenModule.compileScripts(fileName, Collections.singletonList(zenParsedFile), environmentGlobal, false);
        } catch(Throwable ex) {
            registry.getErrorLogger().error("Error executing: " + fileName + ": " + ex.getMessage(), ex);
        }*/
    }
}
