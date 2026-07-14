package dto;

import com.github.javafaker.Address;
import com.github.javafaker.PhoneNumber;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Feedback {
    private String phoneNumber;
    private String description;
}
