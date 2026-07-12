package dto;

import com.github.javafaker.Faker;
import lombok.experimental.UtilityClass;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;

@UtilityClass
public class WorkOutsFactory {

    private static final Faker faker = new Faker();
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

    public static WorkOuts getWorkOuts() {

        LocalDate now = LocalDate.now();
        LocalDate firstDay = now.withDayOfMonth(1);
        LocalDate lastDay = now.withDayOfMonth(now.lengthOfMonth());
        long randomDay = ThreadLocalRandom.current()
                .nextLong(firstDay.toEpochDay(), lastDay.plusDays(1).toEpochDay());
        String randomDate = LocalDate.ofEpochDay(randomDay)
                .format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        return WorkOuts.builder()
                .date(randomDate)
                .name(faker.funnyName().name())
                .description(faker.harryPotter().house())
                .pace(String.valueOf(faker.number().numberBetween(2, 10)))
                .distance(String.valueOf(faker.number().numberBetween(1, 20)))
                .notes(faker.lorem().sentence())
                .build();
    }
}
