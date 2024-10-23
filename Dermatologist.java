import java.util.List;

public class Dermatologist extends Person {
    private String dermatologistID;
    private List<String> availableDates;
    private List<String> availableTimes;  // Times can represent the start-end times for each available date.

    public Dermatologist(String name, String email, String telephoneNumber, String dermatologistID, List<String> availableDates, List<String> availableTimes) {
        super(name, email, telephoneNumber);
        this.dermatologistID = dermatologistID;
        this.availableDates = availableDates;
        this.availableTimes = availableTimes;
    }

    public String getDermatologistID() {
        return dermatologistID;
    }

    public void setDermatologistID(String dermatologistID) {
        this.dermatologistID = dermatologistID;
    }

    public List<String> getAvailableDates() {
        return availableDates;
    }

    public void setAvailableDates(List<String> availableDates) {
        this.availableDates = availableDates;
    }

    public List<String> getAvailableTimes() {
        return availableTimes;
    }

    public void setAvailableTimes(List<String> availableTimes) {
        this.availableTimes = availableTimes;
    }

    public String getAvailability() {
        StringBuilder availability = new StringBuilder();
        for (int i = 0; i < availableDates.size(); i++) {
            availability.append(availableDates.get(i))
                    .append(": ")
                    .append(availableTimes.get(i))
                    .append("\n");
        }
        return availability.toString();
    }
}
