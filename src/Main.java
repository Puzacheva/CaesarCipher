import javax.xml.validation.Validator;
import java.io.File;
import java.lang.classfile.attribute.SyntheticAttribute;
import java.util.Locale;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Выберите язык (english/русский):");
        String language = scanner.nextLine().toLowerCase();

        String alphabet;
        if (language.equals("english"))
        {
            alphabet = CaesarCipher.English_alphabet;
        }
        else if (language.equals("русский"))
        {
            alphabet = CaesarCipher.Russian_alphabet;
        }
        else
        {
            System.out.println("Неверный выбор языка.");
            return;
        }

        CaesarCipher caesarCipher = new CaesarCipher(alphabet);

        System.out.println("Выберите действие (1 -шифрование текста, 2 - расшифровка текста):");
        /*System.out.println("1. Шифрование текста");
        System.out.println("2. Расшифровка текста");
        System.out.println("3. Выход");*/
        int mode = scanner.nextInt();

        System.out.println("Введите путь к исходному файлу:");
        scanner.nextLine();
        String inputFilePath = scanner.nextLine();

            //int choice = scanner.nextInt();
            //scanner.nextLine();

        if (!new File(inputFilePath).exists()) {
            System.out.println("Файл не существует.");
            return;
        }





        switch (mode) {
                case 1:
                    System.out.println("Введите ключ сдвига для шифрования текста:");
                    int encryptShift = scanner.nextInt();

                    if (!Validator.isValidShift(encryptShift, alphabet))
                    {
                        System.out.println("Неверный ключ сдвига. Ключ должен быть от 1 до " + (alphabet.length() - 1));
                        return;
                    }

                    scanner.nextLine();

                    System.out.println("");
                    break;
                case 2:
                    descryptText(scanner, caesarCipher);
                    break;
                case 3:
                    System.out.println("Выход из программы...");
                    System.exit(0);
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");

            }
        }
    }

    private static void encryptText(Scanner scanner, CaesarCipher caesarCipher) {
        try {
            System.out.println("Введите путь к исходному файлу:");
            String inputFilePath = scanner.nextLine();
            if (!Validator.fileExists(inputFilePath)) {
                System.out.println("Файл не существует.");
                return;
            }

            System.out.println("Введите ключ сдвига (1-25):");
            int shift = scanner.nextInt();
            if (!Validator.isValidShift(shift)) {
                System.out.println("Неверный ключ. Допустимы значения от 1 до 25.");
                return;
            }

            System.out.println("Введите путь для сохранения зашифрованного файла:");
            String outputFilePath = scanner.nextLine();

            String content = FileHandler.readFile(inputFilePath);
            String encryptedText = caesarCipher.encrypt(content, shift);
            FileHandler.writeFile(outputFilePath, encryptedText);

            System.out.println("Текст успешно зашифрован и сохранен в файл:" + outputFilePath);
        } catch (IOException e) {
            System.out.println("Произошла ошибка при работе с файлами.");
        }

        private static void descryptText (Scanner scanner, CaesarCipher caesarCipher)
        {
            try {
                System.out.println("Введите путь к зашифрованному файлу:");
                String inputFilePath = scanner.nextLine();
                if (!Validator.fileExists(inputFilePath)) {
                    System.out.println("Файл не существует.");
                    return;
                }

                System.out.println("Введите ключ сдвига (1-25):");
                int shift = scanner.nextInt();
                if (!Validator.isValidShift(shift)) {
                    System.out.println("Неверный ключ. Допустимы значения от 1 до 25.");
                    return;
                }

                System.out.println("Введите путь для сохранения расшифрованного файла:");
                String outputFilePath = scanner.nextLine();

                if (!caesarCipher.isValidShift(shift)) {
                    System.out.println("Неверный ключ. Допустимы значения от 1 до 25.");
                    return;
                }
                String content = FileHandler.readFile(inputFilePath);
                String decryptedText = caesarCipher.decrypt(content, shift);
                FileHandler.writeFile(outputFilePath, decryptedText);

                System.out.println("Текст успешно расшифрован и сохранен в файл:" + outputFilePath);
            } catch (IOException e) {
                System.out.println("Произошла ошибка при работе с файлами.");
            }
        }
    }
}
