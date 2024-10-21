public class CaesarCipher {

    public static final String English_Alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String Russian_Alphabet = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";

    private final String alphabet;

    public CaesarCipher(String alphabet)
    {
        this.alphabet = alphabet;
    }

    // Метод шифрования
    public String encrypt(String text, int shift)
    {
       return processText(text, shift);
    }

    // Метод расшифровки
    public String decrypt(String text, int shift)
    {
        return processText(text, -shift);
    }

    private String processText(String text, int shift)
    {
        StringBuilder result = new StringBuilder();
        shift = shift % alphabet.length();
        text = text.toUpperCase();

        for (char character : text.toCharArray())
        {
            int index = alphabet.indexOf(character);
            if (index != -1)
            {
                int newIndex = (index + shift + alphabet.length()) % alphabet.length();
                result.append(alphabet.charAt(newIndex));
            }
            else
            {
                result.append(character);
            }
        }
         return  result.toString();
    }

    public String bruteForceDecrypt(String encryptedText)
    {
        StringBuilder allResults = new StringBuilder();
        for (int shift = 1; shift < alphabet.length(); shift++)
        {
            String decryptedText = decrypt(encryptedText, shift);
            allResults.append("Сдвиг ").append(shift).append(": ").append(decryptedText).append("\n");
        }
        return allResults.toString();
    }
}

