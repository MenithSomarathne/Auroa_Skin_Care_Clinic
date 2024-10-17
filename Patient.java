public class Patient extends Person {
    private String NIC;

    public Patient(String name, String email, String telephoneNumber, String NIC) {
        super(name, email, telephoneNumber);
        this.NIC = NIC;
    }

    public String getNIC() {
        return NIC;
    }

    public void setNIC(String NIC) {
        this.NIC = NIC;
    }

    public void registerForAppointment() {
        System.out.println("Patient " + getName() + " has registered for an appointment.");
    }
}
