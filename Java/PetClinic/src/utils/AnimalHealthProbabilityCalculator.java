package utils;

import java.util.HashMap;
import java.util.Map;

public class AnimalHealthProbabilityCalculator {

    public double calculateIllnessProbability(String species, String breed, int age, boolean gender, String chronicCondition, String symptom, String medicine, String surgery) {
        double speciesRisk = getSpeciesRisk(species);
        double breedRisk = getBreedRisk(species, breed);
        double ageRisk = getAgeRisk(species, age);
        double genderRisk = getGenderRisk(gender);
        double chronicConditionRisk = getChronicConditionRisk(chronicCondition);
        double symptomRisk = getSymptomRisk(species, breed, symptom);
        double medicineRisk = getMedicineRisk(species, breed, medicine);
        double surgeryRisk = getSurgeryRisk(species, breed, surgery);

        // La probabilità totale è una combinazione dei singoli rischi
        double totalRisk = speciesRisk + breedRisk + ageRisk + genderRisk + chronicConditionRisk + symptomRisk + medicineRisk + surgeryRisk;
        
        // Normalizziamo la probabilità su una scala da 0 a 1
        return Math.min(totalRisk, 1.0); // Se la somma supera 1, la limitiamo a 1
    }

    public double getBreedRisk(String species, String breed) {
        Map<String, Map<String, Double>> speciesBreedRisk = new HashMap<>();
        
        Map<String, Double> dogBreeds = new HashMap<>();
        dogBreeds.put("labrador", 0.1);
        dogBreeds.put("bulldog", 0.2);
        dogBreeds.put("german shepherd", 0.15);
        
        Map<String, Double> catBreeds = new HashMap<>();
        catBreeds.put("siamese", 0.1);
        catBreeds.put("persian", 0.25);
        catBreeds.put("maine coon", 0.2);
        
        speciesBreedRisk.put("dog", dogBreeds);
        speciesBreedRisk.put("cat", catBreeds);
        
        if (speciesBreedRisk.containsKey(species)) {
            return speciesBreedRisk.get(species).getOrDefault(breed, 0.1); // Default risk for unknown breed
        }
        
        return 0.1; // Default value for unknown species
    }

    public double getAgeRisk(String species, int age) {
        Map<String, int[]> speciesAgeRanges = new HashMap<>();
        
        // Definisco fasce d'età per ciascuna specie
        speciesAgeRanges.put("dog", new int[]{2, 7});   // Giovane: <=2, Adulto: 2-7, Anziano: >7
        speciesAgeRanges.put("cat", new int[]{3, 10});  // Giovane: <=3, Adulto: 3-10, Anziano: >10
        
        int[] ageRanges = speciesAgeRanges.getOrDefault(species, new int[]{2, 7}); // Default ranges per specie sconosciute
        
        if (age <= ageRanges[0]) return 0.05; // Giovane
        if (age <= ageRanges[1]) return 0.15; // Adulto
        return 0.25; // Anziano
    }

    public double getSymptomRisk(String species, String breed, String symptom) {
        Map<String, Map<String, Double>> speciesSymptomRisk = new HashMap<>();
        
        // Definire sintomi per i cani
        Map<String, Double> dogSymptoms = new HashMap<>();
        dogSymptoms.put("none", 0.0);
        dogSymptoms.put("cough", 0.1);
        dogSymptoms.put("vomiting", 0.3);
        
        // Definire sintomi per i gatti
        Map<String, Double> catSymptoms = new HashMap<>();
        catSymptoms.put("none", 0.0);
        catSymptoms.put("sneezing", 0.1);
        catSymptoms.put("vomiting", 0.25);
        
        speciesSymptomRisk.put("dog", dogSymptoms);
        speciesSymptomRisk.put("cat", catSymptoms);
        
        if (speciesSymptomRisk.containsKey(species)) {
            return speciesSymptomRisk.get(species).getOrDefault(symptom.toLowerCase(), 0.2);
        }
        
        return 0.2; // Rischio predefinito per specie non specificate
    }

    public double getMedicineRisk(String species, String breed, String medicine) {
        Map<String, Map<String, Double>> speciesMedicineRisk = new HashMap<>();
        
        // Farmaci specifici per i cani
        Map<String, Double> dogMedicines = new HashMap<>();
        dogMedicines.put("none", 0.0);
        dogMedicines.put("antibiotics", 0.1);
        dogMedicines.put("steroids", 0.15);
        
        // Farmaci specifici per i gatti
        Map<String, Double> catMedicines = new HashMap<>();
        catMedicines.put("none", 0.0);
        catMedicines.put("painkillers", 0.1);
        catMedicines.put("antibiotics", 0.2);
        
        speciesMedicineRisk.put("dog", dogMedicines);
        speciesMedicineRisk.put("cat", catMedicines);
        
        if (speciesMedicineRisk.containsKey(species)) {
            return speciesMedicineRisk.get(species).getOrDefault(medicine.toLowerCase(), 0.05);
        }
        
        return 0.05; // Valore predefinito
    }

    public double getSurgeryRisk(String species, String breed, String surgery) {
        Map<String, Map<String, Double>> speciesSurgeryRisk = new HashMap<>();
        
        // Operazioni specifiche per i cani
        Map<String, Double> dogSurgeries = new HashMap<>();
        dogSurgeries.put("none", 0.0);
        dogSurgeries.put("spay", 0.1);
        dogSurgeries.put("hip replacement", 0.3);
        
        // Operazioni specifiche per i gatti
        Map<String, Double> catSurgeries = new HashMap<>();
        catSurgeries.put("none", 0.0);
        catSurgeries.put("neuter", 0.1);
        catSurgeries.put("dental surgery", 0.2);
        
        speciesSurgeryRisk.put("dog", dogSurgeries);
        speciesSurgeryRisk.put("cat", catSurgeries);
        
        if (speciesSurgeryRisk.containsKey(species)) {
            return speciesSurgeryRisk.get(species).getOrDefault(surgery.toLowerCase(), 0.15);
        }
        
        return 0.15; // Rischio predefinito
    }

    public double getGenderRisk(boolean gender) {
        // Gender potrebbe avere un effetto minore sul rischio di malattia
        return gender ? 0.1 : 0.05;
    }
    
    public double getSpeciesRisk(String species) {
        // Esempio di distribuzione pesi per specie
        return switch (species.toLowerCase()) {
            case "dog" -> 0.2;
            case "cat" -> 0.1;
            case "rabbit" -> 0.15;
            default -> 0.05;
        };
    }

    private double getChronicConditionRisk(String chronicCondition) {
        // Le malattie croniche aumentano il rischio di ammalarsi
        return chronicCondition.equalsIgnoreCase("none") ? 0.0 : 0.3;
    }
}
