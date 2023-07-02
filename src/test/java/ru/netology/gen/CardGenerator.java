package ru.netology.gen;
import com.github.javafaker.Faker;
import lombok.Value;
import lombok.val;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class CardGenerator {
    private CardGenerator() {
    }

    static Faker faker = new Faker(new Locale("ru"));

    public static String generateDate(int addedDays) {
        LocalDate date = LocalDate.now();
        LocalDate newDate = date.plusDays(addedDays);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String dateAdd = newDate.format(dateTimeFormatter);
        return dateAdd;
    }

    public static String generateCity() {
        var cities = new String[] {"Москва", "Санкт-Петербург", "Тула", "Абакан", "Краснодар", "Смоленск",
                "Майкоп", "Калининград", "Благовещенск"};
        return cities[new Random().nextInt(cities.length)];
    }

    public static String generateName(String locale) {
        var faker = new Faker(new Locale(locale));
        return faker.name().lastName() + " " + faker.name().firstName();
    }

    public static String generatePhone(String locale) {
        var faker = new Faker(new Locale(locale));
        return faker.phoneNumber().phoneNumber();
    }

    public static class Registration {
        private Registration() {
        }

        public static UserInfo generateUser(String locale) {
           return new UserInfo(generateCity(), generateName(locale), generatePhone(locale));
        }
    }

    @Value
    public static class UserInfo {
        String city;
        String name;
        String phone;
    }
}