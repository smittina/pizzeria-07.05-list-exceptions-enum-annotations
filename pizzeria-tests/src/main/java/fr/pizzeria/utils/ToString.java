package fr.pizzeria.utils;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ToString {

	boolean uppercase() default false;
	boolean price() default false;
	
}
