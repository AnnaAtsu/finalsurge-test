package dto;

import com.github.javafaker.DateAndTime;
import lombok.*;

@Getter
@Setter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkOuts {
    private String date;
    private String name;
    private String description;
    private String distance;
    private String pace;
    private String notes;


    public WorkOuts(DateAndTime date, String name, String description
            , String distance, String pace, String notes) {
    }
}
