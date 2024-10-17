public class Treatment {
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
        double tax = treatmentPrice * 0.025;  // 2.5% tax
        return tax;
    }
}
