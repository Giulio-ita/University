package service;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import utils.AnimalHealthProbabilityCalculator;

public class AnimalHealthService {

    private final AnimalHealthProbabilityCalculator calculator;

    public AnimalHealthService() {
        this.calculator = new AnimalHealthProbabilityCalculator();
    }

    public double calculateHealthRisk(String species, String breed, int age, boolean gender, String chronicCondition, String symptom, String medicine, String surgery) {
        // Validazione dei parametri
        validateInputs(species, breed, age, chronicCondition, symptom, medicine, surgery);
        
        // Calcolo della probabilità di malattia
        return calculator.calculateIllnessProbability(species, breed, age, gender, chronicCondition, symptom, medicine, surgery);
    }

    private void validateInputs(String species, String breed, int age, String chronicCondition, String symptom, String medicine, String surgery) {
        if (species == null || species.isEmpty()) {
            throw new IllegalArgumentException("La specie non può essere vuota.");
        }
        if (age < 0) {
            throw new IllegalArgumentException("L'età non può essere negativa.");
        }
        // Aggiungere ulteriori validazioni per breed, chronicCondition, symptom, medicine, surgery se necessario
    }

    public String getHealthRiskDescription(double risk) {
        if (risk < 0.4) {
            return "Basso rischio di malattia.";
        } else if (risk < 0.7) {
            return "Rischio moderato di malattia.";
        } else {
            return "Alto rischio di malattia.";
        }
    }
    public List<String> getCommonSymptoms(String species, String breed) {
        Map<String, Map<String, List<String>>> speciesSymptoms = new HashMap<>();
        
        Map<String, List<String>> dogSymptoms = new HashMap<>();
        dogSymptoms.put("labrador", Arrays.asList("cough", "vomiting", "none"));
        dogSymptoms.put("bulldog", Arrays.asList("cough", "sneezing", "none"));
        
        Map<String, List<String>> catSymptoms = new HashMap<>();
        catSymptoms.put("siamese", Arrays.asList("sneezing", "vomiting", "none"));
        catSymptoms.put("persian", Arrays.asList("sneezing", "vomiting", "none"));

        speciesSymptoms.put("dog", dogSymptoms);
        speciesSymptoms.put("cat", catSymptoms);

        return speciesSymptoms.getOrDefault(species.toLowerCase(), Collections.emptyMap()).getOrDefault(breed.toLowerCase(), Collections.emptyList());
    }
}

