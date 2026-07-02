package dto;

import com.github.javafaker.Address;
import com.github.javafaker.PhoneNumber;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Feedback {
    private String phoneNumber;
    private String description;

    public Feedback(PhoneNumber phoneNumber, Address address) {
    }
}
