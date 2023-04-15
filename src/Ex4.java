import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;
public class Ex4 {
        Float a;
        Float b;
        char op;
        File f;

        Ex4(){
            Scanner in = new Scanner(System.in);
            System.out.print("Введите первое число: ");
            a = in.nextFloat();
            System.out.print("Введите операцию: ");
            op = in.next().charAt(0);
            System.out.print("Введите второе число: ");
            b = in.nextFloat();
            in.close();
            f = new File("Ex4_logger.txt");
        }

        public boolean oper() {
            return (op == '/' || op == '*' || op == '+' || op == '-');
        }

        public String calc() throws IOException {
            String str = "";
            if (oper()){
                if (op == '+') {
                    str = String.format("%.1f + %.1f = %.1f\n", a, b, a+b);
                } else if (op == '-') {
                    str = String.format("%.1f - %.1f = %.1f\n", a, b, a-b);
                } else if (op == '*') {
                    str = String.format("%.1f * %.1f = %.1f\n", a, b, a*b);
                } else if (op == '/') {
                    str = String.format("%.1f / %.1f = %.1f\n", a, b, a/b);
                }
            }
            else {
                str = "mistake";
            }
            Files.write(f.toPath(), str.getBytes(), StandardOpenOption.APPEND);

            return str;
        }
    }

