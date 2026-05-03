import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class HealthService {
private List<HealthRecord> records = new ArrayList<>();

    public void addRecord(HealthRecord record) {
        records.add(record);
    }

    public List<HealthRecord> getRecords() {
        return records;
    }

    public int getRecordCount() {
        return records.size();
    }

    public String getDailyTip() {
        String[] tips = {
            "Drink more water",
            "Exercise daily",
            "Eat vegetables",
            "Sleep 7-8 hours",
            "Avoid junk food"
        };

        Random rand = new Random();
        return tips[rand.nextInt(tips.length)];
    }
}

