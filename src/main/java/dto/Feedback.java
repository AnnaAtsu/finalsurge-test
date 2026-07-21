package dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Feedback {
    private String phoneNumber;
    private String description;
}
