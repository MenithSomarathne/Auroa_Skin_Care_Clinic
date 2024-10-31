class Appointment {
    private String appointmentID;
    private String appointmentDate;
    private String appointmentTime;
    private Patient patient;
    private Dermatologist dermatologist;

    public Appointment(String appointmentID, String appointmentDate, String appointmentTime, Patient patient, Dermatologist dermatologist) {
        this.appointmentID = appointmentID;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.patient = patient;
        this.dermatologist = dermatologist;
    }

    public String getAppointmentID() {
        return appointmentID;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public Patient getPatient() {
        return patient;
    }

    public void updateAppointment(String newDate, String newTime) {
        this.appointmentDate = newDate;
        this.appointmentTime = newTime;
        System.out.println("Appointment updated successfully.");
    }
}