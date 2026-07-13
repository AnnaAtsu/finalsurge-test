package dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Register {

    private String firstName;
    private String lastName;
    private String email;
    private String password = "texts";
}
