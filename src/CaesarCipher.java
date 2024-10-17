public class CaesarCipher {
    private static final String Alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String Russian_Alphabet = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";

    //Для англ алфавита
    public static String encrypt(String text, int shift)
    {
        for(int i = 0; i < text.length(); i++)
        {
            char ch = text.charAt(i);
            int index = Alphabet.indexOf(Character.toUpperCase(ch));
            if (index != -1)
            {
                int newIndex = (index + shift) % Alphabet.length();
                result.append(Alphabet.charAt(newIndex));
            }
            else
            {
                result.append(ch);
            }
        }
        return result.toString;
    }

    public static String decrypt(String text, int shift)
    {
        return encrypt(text, Alphabet.length() - shift);
    }
}

