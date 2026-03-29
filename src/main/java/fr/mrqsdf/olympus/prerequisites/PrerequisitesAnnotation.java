package fr.mrqsdf.olympus.prerequisites;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to mark classes related to prerequisite mechanics.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface PrerequisitesAnnotation {

    /**
     * Gets the unique identifier for the prerequisite.
     * @return A string representing the unique identifier for the prerequisite.
     */
    String id();


}
