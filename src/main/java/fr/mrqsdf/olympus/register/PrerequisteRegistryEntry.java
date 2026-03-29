package fr.mrqsdf.olympus.register;

import fr.mrqsdf.olympus.costs.ICost;

import java.util.function.Supplier;

public record PrerequisteRegistryEntry(String id, Supplier<? extends ICost> supplier) {


    public ICost createInstance() {
        return supplier.get();
    }

}
