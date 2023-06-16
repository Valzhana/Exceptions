package homework.sem3;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class finalTask {
    public static void main(String[] args) {
        checkDataInput();
    }
    public static String[] inputData() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Введите данные через пробел. Пример ввода: Иванов Иван Иванович 01.01.2001 89253456705 m");
            String data = scanner.nextLine();
            String[] arrayData = data.split(" ");
            if (arrayData.length == 6) {
                return arrayData;
            } else if (arrayData.length < 6) {
                System.out.println("Введены не все данные");
            } else System.out.println("Неверное количество полей");
        }
    }
    public static void checkDataInput() {
        String[] array = inputData();
        String gender = array[5];
        String lastName = array[0];
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            LocalDate birthDate = LocalDate.parse(array[3], formatter);
        } catch (DateTimeParseException e) {
            System.err.println("Неверный формат даты");
        }
        try {
            long phoneNumber = Long.parseLong(array[4]);
        } catch (NumberFormatException e) {
            System.err.println("Неверный формат телефона");
        }
        if (!"m".equals(gender) && !"f".equals(gender)) {
            System.err.println("Неверный формат гендера, введите 'm' или 'f'");
        }
        String fileName = lastName + ".txt";
        try (FileWriter fw = new FileWriter(fileName, true)) {
            for (int i = 0; i < array.length; i++) {
                fw.append(array[i]);
                fw.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
