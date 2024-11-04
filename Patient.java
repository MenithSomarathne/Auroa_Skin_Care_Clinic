import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Patient extends Person {
    private String NIC;
    private static final Set<String> uniqueNICs = new HashSet<>();

    public Patient(String name, String email, String phoneNumber, String NIC) {
        super(name, validateEmail(email), validatePhoneNumber(phoneNumber));
        this.NIC = validateNIC(NIC);
    }

    public String getNIC() {
        return NIC;
    }

    private static String validateEmail(String email) {
        Scanner scanner = new Scanner(System.in);
        while (email == null || !email.matches("^[\\w-\\.]+@[\\w-]+\\.[a-z]{2,3}$")) {
            System.out.println("Invalid email format. Please enter a valid email:");
            email = scanner.nextLine();
        }
        return email;
    }

    private static String validatePhoneNumber(String phoneNumber) {
        Scanner scanner = new Scanner(System.in);
        while (phoneNumber == null || !phoneNumber.matches("^(?:0|94)?[1-9]\\d{8}$")) {
            System.out.println("Invalid phone number format. Please enter a valid phone number:");
            phoneNumber = scanner.nextLine();
        }
        return phoneNumber;
    }

    private static String validateNIC(String NIC) {
        Scanner scanner = new Scanner(System.in);
        while (NIC == null || !NIC.matches("^\\d{9}[Vv]$|^\\d{12}$") || !isUniqueNIC(NIC)) {
            System.out.println("Invalid or duplicate NIC. Please enter a unique and valid NIC:");
            NIC = scanner.nextLine();
        }
        uniqueNICs.add(NIC); // check duplicate nic number
        return NIC;
    }

    private static boolean isUniqueNIC(String NIC) {
        return !uniqueNICs.contains(NIC);
    }
}
