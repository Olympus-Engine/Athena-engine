package fr.mrqsdf.olympus.register;

import fr.mrqsdf.olympus.competence.Competence;
import fr.mrqsdf.olympus.costs.ICost;

import java.util.function.Supplier;

public record CompetenceRegistryEntry(String id, Supplier<? extends Competence> supplier, String[] groups) {


    public Competence createInstance() {
        return supplier.get();
    }


    public boolean hasGroup(String group) {
        for (String g : groups) {
            if (g.equals(group)) return true;
        }
        return false;
    }

    public boolean hasGroups(String... groups) {
        for (String group : groups) {
            if (!hasGroup(group)) return false;
        }
        return true;
    }



}
