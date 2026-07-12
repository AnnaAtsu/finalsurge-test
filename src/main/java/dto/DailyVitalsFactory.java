package dto;

import com.github.javafaker.Faker;
import lombok.experimental.UtilityClass;

import java.text.SimpleDateFormat;

@UtilityClass
public class DailyVitalsFactory {
    private static final Faker faker = new Faker();
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

    public static DailyVitals getDailyVitals() {
        return DailyVitals.builder()
                .date(dateFormat.format(faker.date().birthday()))
                .steps(String.valueOf(faker.number().numberBetween(3000, 4000)))
                .caloriesConsumed(String.valueOf(faker.number().numberBetween(1200, 2500)))
                .weight(String.valueOf(faker.number().numberBetween(60, 80)))
                .bodyFat(String.valueOf(faker.number().numberBetween(15, 25)))
                .water(String.valueOf(faker.number().numberBetween(1000, 2000)))
                .restingHR(String.valueOf(faker.number().numberBetween(90, 100)))
                .hRVariability(String.valueOf(faker.number().numberBetween(120, 150)))
                .sleepHours(String.valueOf(faker.number().numberBetween(3, 8)))
                .healthNotes(faker.lorem().sentence())
                .build();
    }
}
