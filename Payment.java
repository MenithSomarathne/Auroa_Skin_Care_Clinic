public class Payment {
    private String paymentID;
    private double registrationFee = 500.00;
    private double finalAmount;
    private String paymentStatus;

    public Payment(String paymentID, double finalAmount) {
        this.paymentID = paymentID;
        this.finalAmount = finalAmount;
        this.paymentStatus = "Pending";
    }

    public void acceptRegistrationFee() {
        System.out.println("Registration fee of LKR 500 paid.");
    }

    public void processPayment() {
        this.paymentStatus = "Paid";
        System.out.println("Payment of LKR " + finalAmount + " processed. Status: " + paymentStatus);
    }
}
