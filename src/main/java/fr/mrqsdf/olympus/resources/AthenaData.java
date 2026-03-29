package fr.mrqsdf.olympus.resources;

import fr.mrqsdf.olympus.competence.Competence;
import fr.mrqsdf.olympus.register.CompetenceRegistryEntry;
import fr.mrqsdf.olympus.register.CostRegistryEntry;
import fr.mrqsdf.olympus.register.PrerequisteRegistryEntry;

import java.util.*;

public class AthenaData {

    private Map<String, CostRegistryEntry> costRegistry;

    private Map<String, CompetenceRegistryEntry> competenceRegistry;

    private Map<String, PrerequisteRegistryEntry> prerequisteRegistry;

    private List<Competence> loadedCompetences;


    public AthenaData() {
        this.loadedCompetences = new ArrayList<>();
        this.costRegistry = new HashMap<>();
        this.competenceRegistry = new HashMap<>();
        this.prerequisteRegistry = new HashMap<>();
    }


    public void registerCost(CostRegistryEntry entry) {
        costRegistry.put(entry.id(), entry);
    }

    public List<Competence> getLoadedCompetences() {
        return loadedCompetences;
    }


    public Competence getLoadedCompetence(UUID uuid) {
        return loadedCompetences.stream()
                .filter(competence -> competence.currentUUID().equals(uuid))
                .findFirst()
                .orElse(null);
    }

    public void registerCompetence(CompetenceRegistryEntry entry) {
        competenceRegistry.put(entry.id(), entry);
    }

    public void registerPrerequiste(PrerequisteRegistryEntry entry) {
        prerequisteRegistry.put(entry.id(), entry);
    }
}
