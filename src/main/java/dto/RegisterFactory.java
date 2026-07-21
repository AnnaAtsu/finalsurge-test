package dto;

import com.github.javafaker.Faker;
import lombok.experimental.UtilityClass;

@UtilityClass
public class RegisterFactory {
    public static Register getRegister(String password) {
        Faker faker = new Faker();
        return new Register(
                faker.name().firstName(),
                faker.name().lastName(),
                faker.internet().emailAddress(),
                password
        );
    }
}
