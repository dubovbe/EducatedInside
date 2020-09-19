package Regexp;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexpMethods {
    public static void main(String[] args) {
        String a = "Hello213213my3214324name5345345is3453453Boris";
        String[] words = a.split("\\d+");
        System.out.println(Arrays.toString(words));

        String b = "Hello2543423my23423423name2342342is2342342Boris";
        System.out.println(b.replaceAll("\\d+", " "));

        String text = "Boris Dubov dubovbe@yandex.ru Boris Dubov boris@gmail.com";

        Pattern emailPattern = Pattern.compile("(\\w{1,10}+)(@)(\\w{1,10}\\.\\w{1,5})");
        Matcher emailMatcher = emailPattern.matcher(text);

        while (emailMatcher.find()) {
            System.out.println(emailMatcher.group(3)); // получение совпадений,
                                                        // аргумент - номер группы у рег. выражения
        }
    }
}
