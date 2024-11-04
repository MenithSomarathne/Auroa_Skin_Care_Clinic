import java.util.*;

class Main {
    private static List<Appointment> appointments = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        Map<String, List<String>> sharedAvailableTimes = new HashMap<>();
        sharedAvailableTimes.put("Monday", Arrays.asList("10:00 am", "10:15 am", "10:30 am", "10:45 am", "11:00 am", "11:15 am", "11:30 am", "11:45 am", "12:00 pm", "12:45 pm"));
        sharedAvailableTimes.put("Wednesday", Arrays.asList("02:00 pm", "02:15 pm", "02:30 pm", "02:45 pm", "03:00 pm", "03:15 am", "03:30 pm", "03:45 pm", "04:00 pm", "04:45 pm"));
        sharedAvailableTimes.put("Friday", Arrays.asList("04:00 pm", "04:15 pm", "04:30 pm", "04:45 pm", "05:00 pm", "05:15 pm", "05:30 pm", "05:45 pm", "06:00 pm", "06:15 pm", "06:30 pm", "06:45 pm", "07:00 pm", "07:15 pm", "07:30 pm", "07:45 pm"));
        sharedAvailableTimes.put("Saturday", Arrays.asList("09:00 am", "09:15 am", "09:30 am", "09:45 am", "10:00 am", "10:15 am", "10:30 am", "10:45 am", "11:00 am", "11:15 am", "11:30 am", "11:45 am", "12:00 pm", "12:45 pm"));

        // Creating Dermatologists
        Dermatologist dermatologist1 = new Dermatologist("Dr. Nayanathari", "thaari@aurora.com", "0123456789", "DR001", sharedAvailableTimes);
        Dermatologist dermatologist2 = new Dermatologist("Dr. Nawariyan", "nawariyan@aurora.com", "9876543210", "DR002", sharedAvailableTimes);

        // Treatment options and prices
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


        while (true) {
            System.out.println("\n++++++++++ Welcome to Aurora Skin Care! +++++++++++");
            System.out.println("=================================================");
            System.out.println("Select an option:");
            System.out.println("1. Make an appointment");
            System.out.println("2. Update appointment details");
            System.out.println("3. View appointment details by date");
            System.out.println("4. Search appointment by patient name or ID");
            System.out.println("5. Exit");
            System.out.println("=================================================");
            int choice = scanner.nextInt();
            scanner.nextLine();

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

    private static void makeAppointment(Dermatologist dermatologist1, Dermatologist dermatologist2, Map<Integer, String> treatmentOptions, Map<String, Double> treatmentPrices) {
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
        scanner.nextLine();
        Dermatologist selectedDermatologist = dermatologistChoice == 1 ? dermatologist1 : dermatologist2;

        System.out.println("Available dates: " + selectedDermatologist.getAvailableDates());
        System.out.print("Enter preferred date (e.g., Monday): ");
        String appointmentDate = scanner.nextLine();

 //time check
        if (selectedDermatologist.getAvailableTimes().containsKey(appointmentDate))
        {
            List<String> timeSlots = selectedDermatologist.getAvailableTimes().get(appointmentDate);
            System.out.println("Available times for " + appointmentDate + ":");
            timeSlots.forEach(System.out::println);

            System.out.print("Enter preferred time (e.g., 10:15 am): ");
            String appointmentTime = scanner.nextLine();

            // Validate available time slots
            if (timeSlots.contains(appointmentTime)) {
                // Check time slot is already booked
                boolean isTimeSlotBooked = appointments.stream()
                        .anyMatch(appt -> appt.getApp_Date().equals(appointmentDate) && appt.getApp_Time().equals(appointmentTime));

                if (isTimeSlotBooked) {
                    System.out.println("The selected time slot is already booked. Please choose a different time.");
                    return;
                }

                String appointmentID = "App" + (new Random().nextInt(1, 10000) + 1);
                Appointment appointment = new Appointment(appointmentID, appointmentDate, appointmentTime, patient, selectedDermatologist);
                appointments.add(appointment);


                Payment payment = new Payment(UUID.randomUUID().toString(), 500.00);
                payment.acceptRegistrationFee();

                System.out.println("Select treatment type:");
                for (Map.Entry<Integer, String> entry : treatmentOptions.entrySet()) {
                    System.out.println(entry.getKey() + ". " + entry.getValue() + ": LKR " + treatmentPrices.get(entry.getValue()));
                }

                int treatmentChoice = scanner.nextInt();
                scanner.nextLine();

                if (treatmentOptions.containsKey(treatmentChoice)) {
                    String selectedTreatment = treatmentOptions.get(treatmentChoice);
                    double treatmentPrice = treatmentPrices.get(selectedTreatment);
                    Treatment treatment = new Treatment(selectedTreatment, treatmentPrice);

                    double totalFee = treatment.calculateFinalFee();
                    double tax = treatment.addTax();
                    double finalAmount = totalFee + tax;

                    System.out.println("===================Invoice =====================");

                    String invoiceID = generateInvoiceID();
                    System.out.printf("## Invoice ID: %s%n", invoiceID);
                    System.out.printf("## Appointment ID: %s%n", appointmentID);
                    System.out.printf("## Total Fee: LKR %.2f%n", totalFee);
                    System.out.printf("## Tax: LKR %.2f%n", tax);
                    System.out.printf("## Final Amount: LKR %.2f%n", finalAmount);
                    System.out.println("## Status: Paid......");

                    System.out.println("\n................Thank you...............");

                    System.out.println("================================================");
                } else {
                    System.out.println("Invalid treatment option selected.");
                }
            } else {
                System.out.println("Invalid time. Please select a valid time for " + appointmentDate + ".");
            }
        } else {
            System.out.println("No available time slots for the selected date.");
        }
    }


    private static boolean isTimeSlotAvailable(String date, String time) {
        for (Appointment appointment : appointments) {
            if (appointment.getApp_Date().equals(date) && appointment.getApp_Time().equals(time)) {
                return false; // Time slot is already taken
            }
        }
        return true; // Time slot is available
    }



    private static int invoiceCounter = 0;

    private static String generateInvoiceID() {
        invoiceCounter++;
        return String.format("Inv%04d", invoiceCounter);
    }

    private static void updateAppointment() {
        System.out.print("Enter Appointment ID to update: ");
        String appointmentID = scanner.nextLine();
        Appointment appointment = findAppointmentByID(appointmentID);

        if (appointment != null) {
            Dermatologist selectedDermatologist = appointment.getDermatologist();

            System.out.println("Available dates: " + selectedDermatologist.getAvailableDates());
            System.out.print("Enter new date (e.g., Monday): ");
            String newDate = scanner.nextLine();

            // for selected date has available times
            if (selectedDermatologist.getAvailableTimes().containsKey(newDate)) {
                List<String> timeSlots = selectedDermatologist.getAvailableTimes().get(newDate);
                System.out.println("Available times for " + newDate + ":");
                timeSlots.forEach(System.out::println);

                System.out.print("Enter new time (e.g., 10:15 am): ");
                String newTime = scanner.nextLine();

                // Time availability
                if (timeSlots.contains(newTime)) {
                    // Check if the new time slot is already booked
                    boolean isTimeSlotTaken = appointments.stream()
                            .anyMatch(appt -> appt.getApp_Date().equals(newDate) && appt.getApp_Time().equals(newTime) && !appt.getApp_ID().equals(appointmentID));

                    if (isTimeSlotTaken) {
                        System.out.println("The selected time slot is already booked. Please choose a different time.");
                        return;
                    }

                    // Time availability update the appointment
                    appointment.updateAppointment(newDate, newTime);
                    System.out.println("Appointment updated successfully to " + newDate + " at " + newTime);
                } else {
                    System.out.println("Invalid time. Please select a valid time for " + newDate + ".");
                }
            } else {
                System.out.println("No available time slots for the selected date.");
            }
        } else {
            System.out.println("Appointment not found.");
        }
    }



    private static void viewAppointmentsByDate() {
        System.out.print("Enter the date to filter appointments: ");
        String date = scanner.nextLine();
        boolean found = false;

        for (Appointment appointment : appointments) {
            if (appointment.getApp_Date().equals(date)) {
                System.out.println("Appointment ID: " + appointment.getApp_ID() +
                        ", Patient: " + appointment.getPatient().getName() +
                        ", Time: " + appointment.getApp_Time());
                found = true;
            }
        }

        if (!found) {
            System.out.println("No appointments found for the given date.");
        }
    }

    private static void searchAppointment() {
        System.out.println("Search by (1) Patient Name or (2) Appointment ID?");

        if (scanner.hasNextInt()) {
            int searchOption = scanner.nextInt();
            scanner.nextLine();

            if (searchOption == 1) {
                System.out.print("Enter patient name: ");
                String patientName = scanner.nextLine();

                for (Appointment appointment : appointments) {
                    if (appointment.getPatient().getName().equalsIgnoreCase(patientName)) {
                        System.out.println("Appointment found: ID - " + appointment.getApp_ID() +
                                ", Date: " + appointment.getApp_Date() +
                                ", Time: " + appointment.getApp_Time());
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
                            ", Date: " + appointment.getApp_Date() +
                            ", Time: " + appointment.getApp_Time());
                } else {
                    System.out.println("No appointments found for the given appointment ID.");
                }
            } else {
                System.out.println("Invalid search option. Please enter 1 or 2.");
            }
        } else {
            System.out.println("Invalid input! Please enter a number (1 or 2).");
            scanner.nextLine(); // Clear invalid input
        }
    }


    private static Appointment findAppointmentByID(String appointmentID) {
        for (Appointment appointment : appointments) {
            String storedID = appointment.getApp_ID().trim();
            if (storedID.equals(appointmentID)) {
                return appointment;
            }
        }
        return null;
    }
}