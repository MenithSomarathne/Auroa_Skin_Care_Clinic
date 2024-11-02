class Appointment {
    private String app_ID;
    private String app_Date;
    private String app_Time;
    private Patient patient;
    private Dermatologist dermatologist;

    public Appointment(String app_ID, String app_Date, String app_Time, Patient patient, Dermatologist dermatologist) {
        this.app_ID = app_ID;
        this.app_Date = app_Date;
        this.app_Time = app_Time;
        this.patient = patient;
        this.dermatologist = dermatologist;
    }

    public String getApp_ID() {
        return app_ID;
    }

    public String getApp_Date() {
        return app_Date;
    }

    public String getApp_Time() {
        return app_Time;
    }

    public Patient getPatient() {
        return patient;
    }

    public Dermatologist getDermatologist() {
        return dermatologist;
    }

    public void updateAppointment(String newDate, String newTime) {
        this.app_Date = newDate;
        this.app_Time = newTime;
        System.out.println("Appointment updated successfully.");
    }
}