class Treatment {
    private String treatmentType;
    private double treatmentPrice;

    public Treatment(String treatmentType, double treatmentPrice) {
        this.treatmentType = treatmentType;
        this.treatmentPrice = treatmentPrice;
    }

    public double calculateFinalFee() {
        return treatmentPrice;
    }

    public double addTax() {
        return treatmentPrice * 0.15; // Assuming 15% tax
    }
}