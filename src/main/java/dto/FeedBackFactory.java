package dto;

import com.github.javafaker.Faker;
import lombok.experimental.UtilityClass;

@UtilityClass
public class FeedBackFactory {
    public static Feedback getFeedback() {
        Faker faker = new Faker();
        return new Feedback(
                faker.phoneNumber().phoneNumber(),
                faker.address().fullAddress()
        );
    }
}
