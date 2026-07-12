package dto;

import com.github.javafaker.DateAndTime;
import lombok.*;

@Getter
@Setter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DailyVitals {
    private String date;
    private String steps;
    private String caloriesConsumed;
    private String weight;
    private String bodyFat;
    private String water;
    private String restingHR;
    private String hRVariability;
    private String sleepHours;
    private String healthNotes;

    public DailyVitals(DateAndTime date, int number, int number1, int number2, int number3, int number4, int number5, int i, int numberBetween, String sentence) {
    }
}
