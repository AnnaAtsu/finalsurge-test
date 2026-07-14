package dto;

import lombok.*;

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
}
