package fr.mrqsdf.olympus.competence;

import java.util.UUID;

public abstract class Competence {

    protected String registryId;
    protected String name;

    protected UUID currentUUID;
    protected String description;
    protected int currentLevel;
    protected int maxLevel;

    protected String[] groups;




    public final String getRegistryId() {
        return registryId;
    }

    public final void setRegistryMeta(String registryId, String[] groups) {
        if (registryId == null || registryId.isBlank())
            throw new IllegalArgumentException("Registry ID cannot be null or blank");
        this.registryId = registryId;
        this.currentUUID = UUID.randomUUID();
        this.groups = groups;
    }

    public final UUID currentUUID() {
        return currentUUID;
    }

    public final String getName() {
        return name;
    }

    public final String[] entityGroup() {
        return groups;
    }


    public final boolean hasGroup(String group) {
        if (groups == null) return false;
        for (String g : groups) {
            if (g.equals(group)) return true;
        }
        return false;
    }

    public final boolean hasGroups(String[] groups) {
        if (groups == null) return false;
        for (String group : groups) {
            if (!hasGroup(group)) return false;
        }
        return true;
    }

    public final String getDescription() {
        return description;
    }

    public final int getCurrentLevel() {
        return currentLevel;
    }

    public final int getMaxLevel() {
        return maxLevel;
    }

}
