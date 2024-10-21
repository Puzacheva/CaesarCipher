public class Validator {

    public static boolean isValidShift(int shift, String alphabet)
    {
        return shift > 0 && shift < alphabet.length();
    }
}