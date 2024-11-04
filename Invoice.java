public class Invoice {
    private String invoiceID;
    private Appointment appointment;
    private double totalFee;
    private double tax;
    private double finalAmount;

    public Invoice(String invoiceID, Appointment appointment, double totalFee, double tax) {
        this.invoiceID = invoiceID;
        this.appointment = appointment;
        this.totalFee = totalFee;
        this.tax = tax;
        this.finalAmount = totalFee + tax;
    }

}

