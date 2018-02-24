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
import java.util.stream.Collectors;

public class ZenMain {
    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 1; i++) {
            List<Mod> modList = JarExtractor.extractDirectory(new File("C:\\Mods"));
            List<ParsedAnnotation> annotationsList = new ArrayList<>();
            ParsedAnnotationFactory factory = new ParsedAnnotationFactory();
            for (Mod m : modList) {
                factory.setSourceMod(m);
                for (Annotation a : m.getAnnotations())
                    annotationsList.add(factory.setAnnotation(a).create());
            }
            List<ParsedAnnotation> methods = annotationsList.stream().filter(a -> a instanceof ZenMethodAnnotation).collect(Collectors.toList());
            for (ParsedAnnotation a : methods) {
                ZenMethodAnnotation z = (ZenMethodAnnotation) a;
                System.out.println(z.toZenMethod());
            }
        }
    }
}
