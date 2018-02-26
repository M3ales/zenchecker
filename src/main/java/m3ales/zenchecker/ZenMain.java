package m3ales.zenchecker;

import m3ales.zenchecker.jsonparser.Annotation;
import m3ales.zenchecker.jsonparser.Mod;
import m3ales.zenchecker.jsonparser.JarExtractor;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
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
            List<ParsedAnnotation> filtered = ParsedAnnotation.filterByClassName(annotationsList, "ITICMaterial");
            for (ParsedAnnotation a : filtered) {
                System.out.println(a.toZenString());
            }
        }
    }

}
