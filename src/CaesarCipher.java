public class CaesarCipher {
    private static final String English_Alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String Russian_Alphabet = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";

    private String alphabet;

    public CaesarCipher(String language)
    {
        if (language.equalsIgnoreCase("english"))
        {
            this.alphabet = English_Alphabet;
        }
        else if (language.equalsIgnoreCase("русский"))
        {
            this.alphabet = Russian_Alphabet;
        }
        else
        {
            throw new IllegalArgumentException("Неподдерживаемый язык");
        }
    }

    //Для англ алфавита
    public String encrypt(String text, int shift)
    {
        StringBuilder result = new StringBuilder();

        for(int i = 0; i < text.length(); i++)
        {
            char ch = text.charAt(i);
            int index = alphabet.indexOf(Character.toUpperCase(ch));
            if (index != -1) {
                int newIndex = (index + shift) % alphabet.length();
                result.append(Character.isUpperCase(ch)) ?
                        aphabet.charAt(mewIndex);
                Character.toLowerCase(alphabet.charAt(newIndex));
            }
            else
            {
                result.append(ch);
            }
        }
        return result.toString;
    }

    public String decrypt(String text, int shift)
    {
        return encrypt(text, alphabet.length() - shift);
    }

    public boolean isValidShift(int shift)
    {
        return shift >= 1 && shift <= alphabet.length() - 1;
    }
}

