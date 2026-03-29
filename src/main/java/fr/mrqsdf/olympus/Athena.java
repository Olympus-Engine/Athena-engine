package fr.mrqsdf.olympus;


import fr.mrqsdf.olympus.register.AutoRegistrar;
import fr.mrqsdf.olympus.register.RegisterType;
import fr.mrqsdf.olympus.resources.AthenaData;

import java.util.concurrent.atomic.AtomicReference;

public class Athena {

    /**
     * Singleton instance of Athena
     */
    private static final AtomicReference<Athena> INSTANCE = new AtomicReference<>();
    private AthenaData data;

    /**
     * Private constructor to prevent instantiation
     */
    private Athena() {
        this.data = new AthenaData();
    }

    /**
     * Get the singleton instance of Prometheus
     * @return Prometheus instance
     * @throws IllegalStateException if Prometheus is already initialized
     */
    public static Athena init(){
        Athena create = new Athena();
        if(!INSTANCE.compareAndSet(null, create)){
            throw new IllegalStateException("Prometheus is already initialized");
        }
        return create;
    }

    /**
     * Automatically register components by scanning the specified base packages for annotated classes.
     * @param type The type of components to register. see {@link RegisterType}.
     * @param basePackages The base packages to scan for components.
     */
    public static void autoRegister(RegisterType type, String... basePackages){
        AutoRegistrar.register(type, basePackages);
    }

    /**
     * Get the singleton instance of Prometheus
     * @return Prometheus instance
     * @throws IllegalStateException if Prometheus is not initialized
     */
    private static Athena getInstance(){
        Athena instance = INSTANCE.get();
        if(instance == null){
            throw new IllegalStateException("Prometheus is not initialized");
        }
        return instance;
    }

    /**
     * Get the PrometheusData instance
     * @return PrometheusData instance
     */
    public static AthenaData getData(){
        return getInstance().data;
    }

}