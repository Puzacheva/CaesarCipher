import java.io.File;

public class Validator {
    public static boolean fileExists(String filePath)
    {
        File file = new File(filePath);
        return file.exists() && !file.isDirectory();
    }

    public static boolean isValidShift(int shift, String alphabet)
    {
        return shift >= 1 && shift < alphabet.length();
    }
}
