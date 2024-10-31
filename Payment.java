class Payment {
    private String paymentID;
    private double registrationFee;

    public Payment(String paymentID, double registrationFee) {
        this.paymentID = paymentID;
        this.registrationFee = registrationFee;
    }

    public void acceptRegistrationFee() {
        System.out.println("Registration fee of LKR " + registrationFee + " accepted.");
    }
}