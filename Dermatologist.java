import java.util.List;

public class Dermatologist extends Person {
    private String dermatologistID;
    private List<String> availableDates;
    private List<String> availableTimes;

    public Dermatologist(String name, String email, String telephoneNumber, String dermatologistID, List<String> availableDates, List<String> availableTimes) {
        super(name, email, telephoneNumber);
        this.dermatologistID = dermatologistID;
        this.availableDates = availableDates;
        this.availableTimes = availableTimes;
    }

    public String getAvailability() {
        return "Available on: " + availableDates + " at times: " + availableTimes;
    }
}
