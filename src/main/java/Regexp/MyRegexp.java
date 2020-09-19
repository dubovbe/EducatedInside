package Regexp;

public class MyRegexp {
    public static void main(String[] args) {
        /*
            \\d - одна цифра
            \\w - одна английская буква
            \\w = [a-zA-Z]
            + - один или более
            * - ноль или более
            ? - 0 или 1 символов до
            ^ - отрицание
            . - любой символ

            ( | | ) - или, один вариант из множества

            [a-zA-Z] - все возможные английские буквы
            [0-9] - все цифры
            [abc] = (a|b|c)

            {2} - два символа до (\\d{2})
            {2,} - от двух символов (\\d{2,})
            {2,4} - от двух до четырех символов (\\d{2,4})

            https://regexlib.com/

        */
        String a = "-100";
        String b = "100";
        String c = "+100";
        String d = "abc100";
        String e = "hello";
        String f = "123";
        String url1 = "http://www.google.com";
        String url2 = "http://www.yandex.com";
        String url3 = "hello_url";
//        System.out.println(a.matches("(-|\\+)?\\d*"));
//        System.out.println(b.matches("(-|\\+)?\\d*"));
//        System.out.println(c.matches("(-|\\+)?\\d*"));
//        System.out.println(d.matches("[a-zA-Z]+\\d*"));
//        System.out.println(e.matches("[^abc]*")); // все буквы, кроме abc
        System.out.println(url1.matches("http://www\\..+\\.(com|ru)"));
        System.out.println(url2.matches("http://www\\..+\\.(com|ru)"));
        System.out.println(url3.matches("http://www\\..+\\.(com|ru)"));
        System.out.println(f.matches("\\d{2,}"));

    }
}
