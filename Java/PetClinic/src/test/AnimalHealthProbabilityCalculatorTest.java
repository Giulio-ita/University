package test;


import org.junit.Test;

import utils.AnimalHealthProbabilityCalculator;

import static org.junit.Assert.*;

public class AnimalHealthProbabilityCalculatorTest {

    private final AnimalHealthProbabilityCalculator calculator = new AnimalHealthProbabilityCalculator();

    @Test
    public void testCalculateIllnessProbability() {
        double probability = calculator.calculateIllnessProbability("dog", "labrador", 5, true, "none", "cough", "antibiotics", "spay");
        assertTrue(probability > 0 && probability <= 1.0);
    }

    @Test
    public void testBreedRisk() {
        double breedRisk = calculator.getBreedRisk("dog", "labrador");
        assertEquals(0.1, breedRisk, 0.001);
        
        breedRisk = calculator.getBreedRisk("dog", "unknown");
        assertEquals(0.1, breedRisk, 0.001); 
    }

    @Test
    public void testAgeRisk() {
        double ageRisk = calculator.getAgeRisk("dog", 1); // Giovane
        assertEquals(0.05, ageRisk, 0.001);
        
        ageRisk = calculator.getAgeRisk("dog", 5); // Adulto
        assertEquals(0.15, ageRisk, 0.001);
        
        ageRisk = calculator.getAgeRisk("dog", 10); // Anziano
        assertEquals(0.25, ageRisk, 0.001);
    }

    @Test
    public void testSymptomRisk() {
        double symptomRisk = calculator.getSymptomRisk("cat", "maine coon", "sneezing");
        assertEquals(0.1, symptomRisk, 0.001);
        
        symptomRisk = calculator.getSymptomRisk("cat", "maine coon", "unknown symptom");
        assertEquals(0.2, symptomRisk, 0.001); 
    }

    @Test
    public void testMedicineRisk() {
        double medicineRisk = calculator.getMedicineRisk("dog", "bulldog", "antibiotics");
        assertEquals(0.1, medicineRisk, 0.001);
        
        medicineRisk = calculator.getMedicineRisk("dog", "bulldog", "unknown medicine");
        assertEquals(0.05, medicineRisk, 0.001); 
    }

    @Test
    public void testSurgeryRisk() {
        double surgeryRisk = calculator.getSurgeryRisk("cat", "persian", "dental surgery");
        assertEquals(0.2, surgeryRisk, 0.001);
        
        surgeryRisk = calculator.getSurgeryRisk("cat", "persian", "unknown surgery");
        assertEquals(0.15, surgeryRisk, 0.001); 
    }

    @Test
    public void testGenderRisk() {
        double maleRisk = calculator.getGenderRisk(true);
        assertEquals(0.1, maleRisk, 0.001);
        
        double femaleRisk = calculator.getGenderRisk(false);
        assertEquals(0.05, femaleRisk, 0.001);
    }
}
