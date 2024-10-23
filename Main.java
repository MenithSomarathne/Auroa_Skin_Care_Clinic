import java.util.*;

public class Main {
    private static List<Appointment> appointments = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static int appointmentCounter = 1;

    public static void main(String[] args) {
        // Sample data for Dermatologists' available dates and times
        List<String> availableDates = Arrays.asList("Monday", "Wednesday", "Friday", "Saturday");
        List<String> availableTimesMonday = Arrays.asList("10:00am - 01:00pm");
        List<String> availableTimesFriday = Arrays.asList("04:00pm - 08:00pm");

        Dermatologist dermatologist1 = new Dermatologist("Dr. Nayanathari", "thaari@aurora.com", "0123456789", "DR001", availableDates, availableTimesMonday);
        Dermatologist dermatologist2 = new Dermatologist("Dr. Nawariyan", "nawariyan@aurora.com", "9876543210", "DR002", availableDates, availableTimesFriday);

        Map<Integer, String> treatmentOptions = new HashMap<>();
        treatmentOptions.put(1, "Acne Treatment");
        treatmentOptions.put(2, "Skin Whitening");
        treatmentOptions.put(3, "Mole Removal");
        treatmentOptions.put(4, "Laser Treatment");

        Map<String, Double> treatmentPrices = new HashMap<>();
        treatmentPrices.put("Acne Treatment", 2750.00);
        treatmentPrices.put("Skin Whitening", 7650.00);
        treatmentPrices.put("Mole Removal", 3850.00);
        treatmentPrices.put("Laser Treatment", 12500.00);

        System.out.println("\n++++++++++Welcome to Aurora Skin Care!+++++++++++");

        while (true) {
            System.out.println("=================================================");
            System.out.println("Select an option:");
            System.out.println("1. Make an appointment");
            System.out.println("2. Update appointment details");
            System.out.println("3. View appointment details by date");
            System.out.println("4. Search appointment by patient name or ID");
            System.out.println("5. Exit");
            System.out.println("=================================================");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear buffer

            switch (choice) {
                case 1:
                    makeAppointment(dermatologist1, dermatologist2, treatmentOptions, treatmentPrices);
                    break;
                case 2:
                    updateAppointment();
                    break;
                case 3:
                    viewAppointmentsByDate();
                    break;
                case 4:
                    searchAppointment();
                    break;
                case 5:
                    System.out.println("Exiting... Thank you for using Aurora Skin Care!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    // 1. Make an appointment
    private static void makeAppointment(Dermatologist dermatologist1, Dermatologist dermatologist2, Map<Integer, String> treatmentOptions, Map<String, Double> treatmentPrices) {
        System.out.println("Enter patient details:");
        System.out.print("Name: ");
        String patientName = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Phone Number: ");
        String phoneNumber = scanner.nextLine();
        System.out.print("NIC: ");
        String NIC = scanner.nextLine();
        Patient patient = new Patient(patientName, email, phoneNumber, NIC);

        System.out.println("Choose dermatologist (1 for Dr. Nayanathari, 2 for Dr. Nawariyan): ");
        int dermatologistChoice = scanner.nextInt();
        Dermatologist selectedDermatologist = dermatologistChoice == 1 ? dermatologist1 : dermatologist2;

        System.out.println("Available dates: " + selectedDermatologist.getAvailability());
        System.out.print("Enter preferred date (e.g., Monday): ");
        String appointmentDate = scanner.next();
        System.out.print("Enter preferred time (e.g., 10:00am - 01:00pm): ");
        String appointmentTime = scanner.next();

        String appointmentID = generateAppointmentID();
        Appointment appointment = new Appointment(appointmentID, appointmentDate, appointmentTime, patient, selectedDermatologist, "");
        appointment.makeAppointment();
        appointments.add(appointment);

        Payment payment = new Payment(UUID.randomUUID().toString(), 500.00);
        payment.acceptRegistrationFee();

        System.out.println("Select treatment type:");
        for (Map.Entry<Integer, String> entry : treatmentOptions.entrySet()) {
            System.out.println(entry.getKey() + ". " + entry.getValue() + ": LKR " + treatmentPrices.get(entry.getValue()));
        }

        int treatmentChoice = scanner.nextInt();
        scanner.nextLine(); // Clear buffer

        if (treatmentOptions.containsKey(treatmentChoice)) {
            String selectedTreatment = treatmentOptions.get(treatmentChoice);
            double treatmentPrice = treatmentPrices.get(selectedTreatment);
            Treatment treatment = new Treatment(selectedTreatment, treatmentPrice);

            double totalFee = treatment.calculateFinalFee();
            double tax = treatment.addTax();
            double finalAmount = totalFee + tax;
            System.out.printf("Total Fee: LKR %.2f, Tax: LKR %.2f, Final Amount: LKR %.2f%n", totalFee, tax, finalAmount);

            Invoice invoice = new Invoice(UUID.randomUUID().toString(), appointment, totalFee, tax);
            invoice.generateInvoice();

            payment = new Payment(UUID.randomUUID().toString(), finalAmount);
            payment.processPayment();
        } else {
            System.out.println("Invalid treatment option selected.");
        }
    }

    // 2. Update appointment details
    private static void updateAppointment() {
        System.out.print("Enter Appointment ID to update: ");
        String appointmentID = scanner.nextLine();
        Appointment appointment = findAppointmentByID(appointmentID);

        if (appointment != null) {
            System.out.print("Enter new date: ");
            String newDate = scanner.nextLine();
            System.out.print("Enter new time: ");
            String newTime = scanner.nextLine();
            appointment.updateAppointment(newDate, newTime);
            System.out.println("Appointment updated successfully.");
        } else {
            System.out.println("Appointment not found.");
        }
    }

    // 3. View appointment details filtered by date
    private static void viewAppointmentsByDate() {
        System.out.print("Enter the date to filter appointments (e.g., Monday): ");
        String date = scanner.nextLine();
        boolean found = false;

        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentDate().equalsIgnoreCase(date)) {
                System.out.println("Appointment ID: " + appointment.getAppointmentID() +
                        ", Patient: " + appointment.getPatient().getName() +
                        ", Time: " + appointment.getAppointmentTime());
                found = true;
            }
        }

        if (!found) {
            System.out.println("No appointments found for the given date.");
        }
    }

    // 4. Search for an appointment using patient name or appointment ID
    private static void searchAppointment() {
        System.out.println("Search by (1) Patient Name or (2) Appointment ID?");
        int searchOption = scanner.nextInt();
        scanner.nextLine();  // Clear buffer

        if (searchOption == 1) {
            System.out.print("Enter patient name: ");
            String patientName = scanner.nextLine();

            for (Appointment appointment : appointments) {
                if (appointment.getPatient().getName().equalsIgnoreCase(patientName)) {
                    System.out.println("Appointment found: ID - " + appointment.getAppointmentID() +
                            ", Date: " + appointment.getAppointmentDate() +
                            ", Time: " + appointment.getAppointmentTime());
                    return;
                }
            }
            System.out.println("No appointments found for the given patient name.");

        } else if (searchOption == 2) {
            System.out.print("Enter appointment ID: ");
            String appointmentID = scanner.nextLine();
            Appointment appointment = findAppointmentByID(appointmentID);

            if (appointment != null) {
                System.out.println("Appointment found: Patient - " + appointment.getPatient().getName() +
                        ", Date: " + appointment.getAppointmentDate() +
                        ", Time: " + appointment.getAppointmentTime());
            } else {
                System.out.println("No appointments found for the given appointment ID.");
            }
        } else {
            System.out.println("Invalid search option.");
        }
    }

    // Helper method to generate appointment IDs (e.g., A001, A002)
    private static String generateAppointmentID() {
        return "A" + String.format("%03d", appointmentCounter++);
    }

    // Helper method to find appointment by ID
    private static Appointment findAppointmentByID(String appointmentID) {
        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentID().equalsIgnoreCase(appointmentID)) {
                return appointment;
            }
        }
        return null;
    }
}
