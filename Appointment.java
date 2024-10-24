public class Appointment {
    private String appointmentID;
    private String appointmentDate;
    private String appointmentTime;
    private Patient patient;
    private Dermatologist dermatologist;
    private String treatmentType;
    private double registrationFee = 500.00;

    public Appointment(String appointmentID, String appointmentDate, String appointmentTime, Patient patient, Dermatologist dermatologist, String treatmentType) {
        this.appointmentID = appointmentID;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.patient = patient;
        this.dermatologist = dermatologist;
        this.treatmentType = treatmentType;
    }

    public void makeAppointment() {
        System.out.println("Appointment booked for " + patient.getName() + " on " + appointmentDate + " at " + appointmentTime);
    }

    public void updateAppointment(String newDate, String newTime) {
        this.appointmentDate = newDate;
        this.appointmentTime = newTime;
        System.out.println("Appointment updated to " + appointmentDate + " at " + appointmentTime);
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


    public String getAppointmentID() {
        return appointmentID;
    }
}

