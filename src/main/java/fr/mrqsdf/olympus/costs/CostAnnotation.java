package fr.mrqsdf.olympus.costs;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to mark classes related to cost mechanics in the system.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface CostAnnotation {

    /**
     * Gets the unique identifier for the cost.
     * @return A string representing the unique identifier for the cost.
     */
    String id();


}
