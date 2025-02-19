package model;

public class MedicalRecord {
    private String hasChronic;
    private String currentSymptoms;
	private String regularMedications;
    private String pastSurgeries;    
	private boolean isEmergency;
    private boolean hasInsurance;
    private boolean isInstallment; 
    private String treatmentType;
    private int appointmentHistory; 
    private double insuranceCoveragePercentage; 
    private String severityLevel;     
    
    public MedicalRecord(String hasChronic, String currentSymptoms, String regularMedications, String pastSurgeries,
			boolean isEmergency, boolean hasInsurance, boolean isInstallment, String treatmentType,
			int appointmentHistory, double insuranceCoveragePercentage, String severityLevel) {
		super();
		this.hasChronic = hasChronic;
		this.currentSymptoms = currentSymptoms;
		this.regularMedications = regularMedications;
		this.pastSurgeries = pastSurgeries;
		this.isEmergency = isEmergency;
		this.hasInsurance = hasInsurance;
		this.isInstallment = isInstallment;
		this.treatmentType = treatmentType;
		this.appointmentHistory = appointmentHistory;
		this.insuranceCoveragePercentage = insuranceCoveragePercentage;
		this.severityLevel = severityLevel;
	}
    
    public String getCurrentSymptoms() {
		return currentSymptoms;
	}

	public void setCurrentSymptoms(String currentSymptoms) {
		this.currentSymptoms = currentSymptoms;
	}

	public String getRegularMedications() {
		return regularMedications;
	}

	public void setRegularMedications(String regularMedications) {
		this.regularMedications = regularMedications;
	}

	public String getPastSurgeries() {
		return pastSurgeries;
	}

	public void setPastSurgeries(String pastSurgeries) {
		this.pastSurgeries = pastSurgeries;
	}

	public void setHasInsurance(boolean hasInsurance) {
		this.hasInsurance = hasInsurance;
	}

	public void setChronic(String hasChronic) {
		this.hasChronic = hasChronic;
	}

	public void setEmergency(boolean isEmergency) {
		this.isEmergency = isEmergency;
	}

	public void setInstallment(boolean isInstallment) {
		this.isInstallment = isInstallment;
	}

	public void setTreatmentType(String treatmentType) {
		this.treatmentType = treatmentType;
	}

	public void setAppointmentHistory(int appointmentHistory) {
		this.appointmentHistory = appointmentHistory;
	}

	public void setInsuranceCoveragePercentage(double insuranceCoveragePercentage) {
		this.insuranceCoveragePercentage = insuranceCoveragePercentage;
	}

	public void setSeverityLevel(String severityLevel) {
		this.severityLevel = severityLevel;
	}

    public String hasChronic() {
        return hasChronic;
    }

    public boolean isEmergency() {
        return isEmergency;
    }

    public boolean hasInsurance() {
        return hasInsurance;
    }

    public boolean isInstallment() {
        return isInstallment;
    }

    public String getTreatmentType() {
        return treatmentType;
    }

    public int getAppointmentHistory() {
        return appointmentHistory;
    }

    public double getInsuranceCoveragePercentage() {
        return insuranceCoveragePercentage;
    }

    public String getSeverityLevel() {
        return severityLevel;
    }
}
