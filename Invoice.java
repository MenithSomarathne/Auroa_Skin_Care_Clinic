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

    public void generateInvoice() {
//        System.out.println("++++++++++++++++++++++++++++++++++++");
//        System.out.println("+ Invoice ID: " + invoiceID );
//        System.out.println("+ Appointment ID: " + appointment );
//        System.out.println("+ Total Fee: " + totalFee);
//        System.out.println("+ Tax: " + tax);
//        System.out.println("+ Final Amount: " + finalAmount);
//        System.out.println("++++++++++++++++++++++++++++++++++++");
    }
}

