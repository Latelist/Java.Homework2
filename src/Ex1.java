// Дана строка sql-запроса "select * from students where ".
// Сформируйте часть WHERE этого запроса, используя StringBuilder.
// Данные для фильтрации приведены ниже в виде json-строки.
//        Если значение null, то параметр не должен попадать в запрос.
//        Параметры для фильтрации: {"name":"Ivanov", "country":"Russia", "city":"Moscow", "age":"null"}

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Ex1 {
    String str;
    Ex1() throws IOException {
        File file = new File("Ex1.txt");
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        str = br.readLine();
    }
    public void printer(String cleaner) {
        System.out.println(str);
    }

    public String cleaner() {
        str = str.replaceAll("\"", "");
        str = str.replace("{", "");
        str = str.replace("}", "");
        return str;
    }

    public HashMap<String, String> dict() {
        str = cleaner();
        HashMap<String,String> diction = new HashMap<String, String>();
        char[] chars = str.toCharArray();
        int start = 0;
        int end;
        String key = null;
        String value;
        for (int i = 0; i< chars.length; ++i) {
            if (chars[i] == ':') {
                end = i;
                char[] newch = Arrays.copyOfRange(chars, start, end);
                System.out.println();
                start = ++i;
                key = new String(newch);
            }
            if (chars[i] == ',') {
                end = i;
                char[] newch = Arrays.copyOfRange(chars, start, end);
                start = i+2;
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

    public String result() {
        HashMap<String, String> d = dict();
        StringBuilder strb = new StringBuilder();
        for (String key: d.keySet()) {
            if (!d.get(key).equals("null")) {
                if (strb.length() == 0){
                    strb.append(key + " = '" + d.get(key) + "'");
                } else {
                    strb.append(" and " + key + " = '" + d.get(key) + "'");
                }
            }
        }
        String result = strb.toString();
        return result;
    }
}
