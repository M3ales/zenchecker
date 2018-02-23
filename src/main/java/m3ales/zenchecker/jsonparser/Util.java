package m3ales.zenchecker.jsonparser;

public class Util {
    public static String cleanupPackageNames(String str)
    {
        str = str.replaceAll("/",".");
        System.out.println("Cleaned: " + str);
        return str;
    }
}
