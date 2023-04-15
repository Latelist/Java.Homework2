//Реализуйте алгоритм сортировки пузырьком числового массива, результат после каждой итерации запишите в лог-файл.

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Ex2 {
    int[] numbers = new int[10];
    File f;
    Ex2() {
        Random random = new Random();
        for (int i = 0; i < numbers.length; ++i) {
            numbers[i] = random.nextInt(100);
        }
        for (int item: numbers) {
            System.out.print(item + " ");
        }
        f = new File("logger.txt");
    }

    public int[] sort() throws IOException {
        int temporary;
        FileWriter writer = new FileWriter("logger.txt", true);

        for (int i = 0; i< numbers.length - 1; ++i) {
            for (int j = 1; j < numbers.length; ++j) {
                if (numbers[j] < numbers[j-1]) {
                    temporary = numbers[j];
                    numbers[j] = numbers[j-1];
                    numbers[j-1] = temporary;
                }
            }
            StringBuilder strb = new StringBuilder();
            for (int j = 0; j < numbers.length; ++j) {
                strb.append(numbers[j] + " ");
            }
            String str = strb.toString();
            writer.write(str + "\n");
        }
        writer.append("\n");
        writer.flush();
        System.out.println();
        for (int item: numbers) {
            System.out.print(item + " ");
        }
        return numbers;
    }
}
