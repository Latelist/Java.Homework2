//Дана json-строка (можно сохранить в файл и читать из файла)
//        [{"фамилия":"Иванов","оценка":"5","предмет":"Математика"},{"фамилия":"Петрова","оценка":"4","предмет":"Информатика"},{"фамилия":"Краснов","оценка":"5","предмет":"Физика"}]
//        Написать метод(ы), который распарсит json и, используя StringBuilder, создаст строки вида: Студент [фамилия] получил [оценка] по предмету [предмет].
//        Пример вывода:
//        Студент Иванов получил 5 по предмету Математика.
//        Студент Петрова получил 4 по предмету Информатика.
//        Студент Краснов получил 5 по предмету Физика.


import java.io.*;
import java.util.Arrays;
import java.util.HashMap;

public class Ex3 {
    String str;
    Ex3() throws IOException {
        File file = new File("Ex3.txt");
        FileReader f = new FileReader(file);
        BufferedReader br = new BufferedReader(f);
        str = br.readLine();
    }

    public String cleaner() {
        str = str.replaceAll("\"", "");
        return str;
    }

    public HashMap<String, String> dict(char[] chars) {

        HashMap<String,String> diction = new HashMap<String, String>();
        int start = 0;
        int end;
        String key = null;
        String value;
        for (int i = 0; i< chars.length; ++i) {
            if (chars[i] == ':') {
                end = i;
                char[] newch = Arrays.copyOfRange(chars, start, end);
                start = ++i;
                key = new String(newch);
            }
            if (chars[i] == ',') {
                end = i;
                char[] newch = Arrays.copyOfRange(chars, start, end);
                start = i+1;
                value = new String(newch);
                diction.put(key, value);
            }
            if (i == chars.length - 1) {
                end = i;
                char[] newch = Arrays.copyOfRange(chars, start, end+1);
                start = i+2;
                value = new String(newch);
                diction.put(key, value);
            }
        }
        return (HashMap) diction;
    }

    public String str_generator(HashMap<String,String> dict){
        StringBuilder strb = new StringBuilder("Студент ");
        strb.append(dict.get("фамилия")).append(" получил ").append(dict.get("оценка")).append(" по предмету ").append(dict.get("предмет"));
        String str = strb.toString();
        return str;
    }

    public void divider() {
        char[] chars = cleaner().toCharArray();
        int start;
        int end;
        for (int i = 0; i < chars.length; ++i) {
            if (chars[i] == '{') {
                start = ++i;
                for (int j = i; j < chars.length; ++j) {
                    if (chars[j] == '}') {
                        end = j;
                        char[] ch1 = Arrays.copyOfRange(chars, start, end);
                        HashMap<String, String> dictionary = dict(ch1);
                        System.out.println(str_generator(dictionary));
                        break;
                    }
                }
            }
        }
    }
}
