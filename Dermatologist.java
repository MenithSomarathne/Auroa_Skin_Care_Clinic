import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class Dermatologist extends Person {
    private String dermatologistID;
    private Map<String, List<String>> availableTimes;

    public Dermatologist(String name, String email, String phoneNumber, String dermatologistID, Map<String, List<String>> availableTimes) {
        super(name, email, phoneNumber);
        this.dermatologistID = dermatologistID;
        this.availableTimes = availableTimes;
    }

    public Map<String, List<String>> getAvailableTimes() {
        return availableTimes;
    }

    public List<String> getAvailableDates() {
        return new ArrayList<>(availableTimes.keySet());
    }
}