class Patient extends Person {
    private String NIC;

    public Patient(String name, String email, String phoneNumber, String NIC) {
        super(name, email, phoneNumber);
        this.NIC = NIC;
    }

    public String getNIC() {
        return NIC;
    }
}