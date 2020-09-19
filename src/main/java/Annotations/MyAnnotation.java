package Annotations;

public @interface MyAnnotation {
    //@Deprecated - устаревший метод
    //@SuppressWarnings() - не показывает предупреждения (?)
    String name() default "MyAnnotation";
    int dateOfBirth() default  2020;
}
