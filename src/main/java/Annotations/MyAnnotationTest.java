package Annotations;

public class MyAnnotationTest {
    @MethodInfo(purpose = "Print Hello World")
    public void printHelloWorld() {
        System.out.println("Hello world");
    }
}
