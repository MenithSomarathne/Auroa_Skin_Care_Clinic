import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // Create a patient
        Patient patient = new Patient("John Doe", "john@example.com", "0712345678", "987654321V");

        // Create a dermatologist
        Dermatologist dermatologist = new Dermatologist("Dr. Smith", "drsmith@example.com", "0712345679", "D123", Arrays.asList("Monday", "Wednesday", "Friday"), Arrays.asList("10:00am", "02:00pm"));

        // Make an appointment
        Appointment appointment = new Appointment("A1001", "2024-10-15", "10:15am", patient, dermatologist, "Skin Whitening");
        appointment.makeAppointment();

        // Create a treatment
        Treatment treatment = new Treatment("Skin Whitening", 7650.00);

        // Calculate total fee and tax
        double totalFee = treatment.calculateFinalFee();
        double tax = treatment.addTax();

        // Generate an invoice
        Invoice invoice = new Invoice("INV12345", appointment, totalFee, tax);
        invoice.generateInvoice();

        // Process payment
        Payment payment = new Payment("P987654", totalFee + tax);
        payment.acceptRegistrationFee();
        payment.processPayment();
    }
}
