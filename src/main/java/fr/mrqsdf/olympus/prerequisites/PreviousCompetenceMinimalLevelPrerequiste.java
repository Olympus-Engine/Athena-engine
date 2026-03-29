package fr.mrqsdf.olympus.prerequisites;

import fr.mrqsdf.olympus.competence.Competence;
import fr.mrqsdf.olympus.resources.AthenaData;

import java.util.UUID;

public class PreviousCompetenceMinimalLevelPrerequiste implements Prerequiste {

    private int minimalLevel;
    private UUID competenceUUID;

    public PreviousCompetenceMinimalLevelPrerequiste(int minimalLevel, UUID competenceUUID) {
        this.minimalLevel = minimalLevel;
        this.competenceUUID = competenceUUID;
    }

    /**
     * Checks if the prerequisite is valid based on the provided objects.
     * @param objects The objects to check against the prerequisite. The first object is expected to be an instance of {@link AthenaData}.
     * @return true if the prerequisite is valid, false otherwise.
     */
    @Override
    public boolean isValid(Object... objects) {
        if (objects.length == 0 ) {
            return false;
        }

        Object object1 = objects[0];
        if (object1 instanceof AthenaData data){
            Competence competence = data.getLoadedCompetence(competenceUUID);
            if (competence == null) {
                return false;
            }
            return competence.getCurrentLevel() >= minimalLevel;
        }

        return false;
    }

    public int getMinimalLevel() {
        return minimalLevel;
    }
}
