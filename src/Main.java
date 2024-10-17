import javax.xml.validation.Validator;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true)
        {
            System.out.println("Выберите действие:");
            System.out.println("1. Шифрование текста");
            System.out.println("2. Расшифровка текста");
            System.out.println("3. Выход");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice)
            {
                case 1:
                    encryptText(scanner);
                    break;
                case 2:
                    descryptText(scanner);
                    break;
                case 3:
                    System.out.println("Выход из программы...");
                    System.exit(0);
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");

            }
        }
    }
}

    private static void encryptText(Scanner scanner)
    {
        try
        {
            System.out.println("Введите путь к исходному файлу:");
            String inputFilePath = scanner.nextLine();
            if (!Validator.fileExists(inputFilePath))
            {
                System.out.println("Файл не существует.");
                return;
            }

            System.out.println("Введите ключ сдвига (1-25):");
            int shift = scanner.nextInt();
            if(!Validator.isValidShift(shift))
            {
                System.out.println("Неверный ключ. Допустимы значения от 1 до 25.");
                return;
            }

            System.out.println("Введите путь для сохранения зашифрованного файла:");
            return;
        }
    }