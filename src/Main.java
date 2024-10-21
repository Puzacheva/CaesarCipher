import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Выберите язык (english/русский):");
        String language = scanner.nextLine().toLowerCase();

        String alphabet;
        if (language.equals("english")) {
            alphabet = CaesarCipher.English_Alphabet;
        } else if (language.equals("русский")) {
            alphabet = CaesarCipher.Russian_Alphabet;
        } else {
            System.out.println("Неверный выбор языка.");
            return;
        }

        CaesarCipher caesarCipher = new CaesarCipher(alphabet);

        int mode = 0;
        while (true) {
            System.out.println("Выберите режим работы (1 -шифрование текста, 2 - расшифровка текста, 3 - расшифровка методом brute force):");
            try {
                mode = scanner.nextInt();
                if (mode != 1 && mode != 2 && mode != 3) {
                    System.out.println("Неверный выбор режима. Введите '1', '2'или '3'.");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Ошибка: необходимо ввести число '1', '2' или '3'.");
                scanner.next();
            }
        }

        System.out.println("Введите путь к исходному файлу:");
        scanner.nextLine();
        String inputFilePath = scanner.nextLine();

        if (!new File(inputFilePath).exists()) {
            System.out.println("Файл не существует.");
            return;
        }

        System.out.println("Введите путь для сохранения результата:");
        String outputFilePath = scanner.nextLine();

        String resultText;
        switch (mode) {
            // Режим шифрования
            case 1:
                System.out.println("Вы выбрали шифрование. Введите ключ сдвига:");
                int encryptShift = scanner.nextInt();

                if (!Validator.isValidShift(encryptShift, alphabet)) {
                    System.out.println("Неверный ключ сдвига. Ключ должен быть от 1 до " + (alphabet.length() - 1));
                    return;
                }

                String textToEncrypt = readFile(inputFilePath);
                if (textToEncrypt == null) {
                    return;
                }

                resultText = caesarCipher.encrypt(textToEncrypt, encryptShift);

                writeFile(outputFilePath, resultText);
                System.out.println("Текст успешно зашифрован и сохранен в файл: " + outputFilePath);
                break;

            // Режим расшифровки
            case 2:
                System.out.println("Вы выбрали расшифровку. Введите ключ сдвига:");
                int decryptShift = scanner.nextInt();

                if (!Validator.isValidShift(decryptShift, alphabet))
                {
                    System.out.println("Неверный ключ сдвига. Ключ должен быть от 1 до " + (alphabet.length() - 1));
                    return;
                }

                String textToDecrypt = readFile(inputFilePath);
                if (textToDecrypt == null) {
                    return;
                }

                resultText = caesarCipher.decrypt(textToDecrypt, decryptShift);

                writeFile(outputFilePath, resultText);
                System.out.println("Текст успешно расшифрован и сохранен в файл: " + outputFilePath);
                break;

            case 3:
                String textToBruteForce = readFile(inputFilePath);
                if (textToBruteForce == null) {
                    return;
                }

                resultText = caesarCipher.bruteForceDecrypt(textToBruteForce);
                writeFile(outputFilePath, resultText);
                System.out.println("Результат расшифровки сохранен в файл: " + outputFilePath);
                break;
        }
    }

    private static String readFile(String filePath)
    {
        try
        {
            return new String(Files.readAllBytes(Paths.get(filePath)));
        }
        catch (IOException e)
        {
            System.out.println("Ошибка чтения файла: " + e.getMessage());
            return null;
        }
    }

    private static void writeFile(String filePath, String content)
    {
        try
        {
            Files.write(Paths.get(filePath), content.getBytes());
        }
        catch (IOException e)
        {
            System.out.println("Ошибка записи в файл: " + e.getMessage());
        }
    }
}


