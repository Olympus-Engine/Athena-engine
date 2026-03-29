package fr.mrqsdf.olympus.register;

import fr.mrqsdf.olympus.Athena;
import fr.mrqsdf.olympus.competence.Competence;
import fr.mrqsdf.olympus.competence.CompetenceAnnotation;
import fr.mrqsdf.olympus.costs.CostAnnotation;
import fr.mrqsdf.olympus.costs.ICost;
import fr.mrqsdf.olympus.prerequisites.PrerequisitesAnnotation;
import fr.mrqsdf.olympus.prerequisites.Prerequiste;
import fr.mrqsdf.olympus.resources.AthenaData;
import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;
import io.github.classgraph.ScanResult;

import java.lang.reflect.Constructor;

/**
 * Utility class for automatically registering materials, factories, and process recipes
 * by scanning specified base packages for annotated classes.
 */
public final class AutoRegistrar {

    // Prevent instantiation
    private AutoRegistrar() {
    }

    //todo rework throwing exceptions, maybe create custom ones for better error handling and debugging

    /**
     * Registers components based on the specified type and base packages.
     *
     * @param type         The type of components to register. see {@link RegisterType}.
     * @param basePackages The base packages to scan for components.
     * @throws IllegalArgumentException if type is null or basePackages is null/empty.
     * @throws IllegalStateException    if any annotated class is invalid or cannot be instantiated.
     */
    public static void register(RegisterType type, String... basePackages) {
        if (type == null) throw new IllegalArgumentException("type cannot be null.");
        if (basePackages == null || basePackages.length == 0)
            throw new IllegalArgumentException("basePackages required.");

        AthenaData data = Athena.getData();

        try (ScanResult scan = new ClassGraph()
                .enableClassInfo()
                .enableAnnotationInfo()
                .acceptPackages(basePackages)
                .scan()) {

            if (type == RegisterType.ALL || type == RegisterType.COST) {

                for (ClassInfo ci : scan.getClassesWithAnnotation(CostAnnotation.class.getName())) {
                    Class<?> raw = ci.loadClass();
                    if (!ICost.class.isAssignableFrom(raw)) {
                        throw new IllegalStateException("@EntityAnnotation on non-Entity: " + raw.getName());
                    }
                    @SuppressWarnings("unchecked")
                    Class<? extends ICost> clazz = (Class<? extends ICost>) raw;

                    CostAnnotation ann = clazz.getAnnotation(CostAnnotation.class);

                    CostRegistryEntry entry = new CostRegistryEntry(
                            ann.id(),
                            () -> newInstance(clazz)
                    );
                    data.registerCost(entry);
                }
            }

            if (type == RegisterType.ALL || type == RegisterType.COMPETENCE) {
                for (ClassInfo ci : scan.getClassesWithAnnotation(CompetenceAnnotation.class.getName())) {
                    Class<?> raw = ci.loadClass();
                    if (!Competence.class.isAssignableFrom(raw)) {
                        throw new IllegalStateException("@EvolutionAnnotation on non-EvolutionCondition: " + raw.getName());
                    }
                    @SuppressWarnings("unchecked")
                    Class<? extends Competence> clazz = (Class<? extends Competence>) raw;

                    CompetenceAnnotation ann = clazz.getAnnotation(CompetenceAnnotation.class);

                    CompetenceRegistryEntry entry = new CompetenceRegistryEntry(
                            ann.id(),
                            () -> newInstance(clazz),
                            ann.groups()
                    );
                    data.registerCompetence(entry);
                }
            }

            if (type == RegisterType.ALL || type == RegisterType.PREREQUISITE) {

                for (ClassInfo ci : scan.getClassesWithAnnotation(PrerequisitesAnnotation.class.getName())) {
                    Class<?> raw = ci.loadClass();
                    if (!Prerequiste.class.isAssignableFrom(raw)) {
                        throw new IllegalStateException("@EntityAnnotation on non-Entity: " + raw.getName());
                    }
                    @SuppressWarnings("unchecked")
                    Class<? extends ICost> clazz = (Class<? extends ICost>) raw;

                    PrerequisitesAnnotation ann = clazz.getAnnotation(PrerequisitesAnnotation.class);

                    PrerequisteRegistryEntry entry = new PrerequisteRegistryEntry(
                            ann.id(),
                            () -> newInstance(clazz)
                    );
                    data.registerPrerequiste(entry);
                }
            }
        }
    }

    /**
     * Creates a new instance of the specified class using its no-argument constructor.
     *
     * @param clazz The class to instantiate.
     * @param <T>   The type of the class.
     * @return A new instance of the specified class.
     * @throws IllegalStateException if the class cannot be instantiated.
     */
    private static <T> T newInstance(Class<T> clazz) {
        try {
            Constructor<T> c = clazz.getDeclaredConstructor();
            c.setAccessible(true);
            return c.newInstance();
        } catch (NoSuchMethodException e) {
            throw new IllegalStateException("No-arg constructor required for auto-register: " + clazz.getName(), e);
        } catch (Exception e) {
            throw new IllegalStateException("Cannot instantiate: " + clazz.getName(), e);
        }
    }
}
