package fr.mrqsdf.olympus.competence;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to mark classes related to competence mechanics.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface CompetenceAnnotation {

    /**
     * Gets the unique identifier for the competence.
     * @return A string representing the unique identifier for the competence.
     */
    String id();

    /**
     * Gets the groups that the competence belongs to.
     * @return An array of strings representing the groups that the competence belongs to.
     */
    String[] groups() default {};

}
