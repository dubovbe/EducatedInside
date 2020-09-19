package Annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME) //политика удержания
public @interface MethodInfo {
    String author() default "Boris Dubov";
    int creationDate() default 2020;
    String purpose();
}
