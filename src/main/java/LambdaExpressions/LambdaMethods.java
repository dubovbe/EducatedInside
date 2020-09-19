package LambdaExpressions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LambdaMethods {
    public static void main(String[] args) {
        int[] arr = new int[10];
        List<Integer> list = new ArrayList();

        for (int i = 0; i < 10; i++) {
            arr[i] = i;
            list.add(i * 3);
        }

//        arr = Arrays.stream(arr).map(x -> x * 2).toArray();
//        list = list.stream().map(x -> x * 2).collect(Collectors.toList());

        System.out.println(Arrays.toString(arr));
        System.out.println(list);

        arr = Arrays.stream(arr).filter(a -> a % 2 == 0).toArray();
        list = list.stream().filter(a -> a % 2 != 0).collect(Collectors.toList());

        System.out.println(Arrays.toString(arr));
        System.out.println(list);

//        Arrays.stream(arr).forEach(System.out::println);
//        list.stream().forEach(System.out::println);

        int sumArr = Arrays.stream(arr).reduce((sum, x) -> sum+x).getAsInt();
        int productList = list.stream().reduce((product, x) -> product*x).get();
        System.out.println("Сумма элементов массива arr: " + sumArr);
        System.out.println("произведение элементов листа list: " + productList);
    }
}
