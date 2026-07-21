package dto;

import lombok.*;

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
}
