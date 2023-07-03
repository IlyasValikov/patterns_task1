package ru.netology.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.gen.CardGenerator;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

class CardDeliveryTest {

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    @Test
    @DisplayName("Should successful plan and replain meeting")
    void shouldSuccessfulPlanAndReplanMeeting() {
        CardGenerator.UserInfo validUser = CardGenerator.Registration.generateUser("ru");
        int daysToAddInFirstMeeting = 10;
        String firstMeetingDate = CardGenerator.generateDate(daysToAddInFirstMeeting);
        int daysToAddInReplain = 13;
        String replainDate = CardGenerator.generateDate(daysToAddInReplain);
        $("[data-test-id=city] input").setValue(validUser.getCity());
        $(".calendar-input__custom-control input").doubleClick().sendKeys(firstMeetingDate);
        $("[data-test-id=name] input").setValue(validUser.getName());
        $("[data-test-id=phone] input").setValue(validUser.getPhone());
        $("[data-test-id=agreement]").click();
        $(byText("Запланировать")).click();
        $(byText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
        $("[data-test-id=success-notification] .notification__content").shouldHave(text("Встреча успешно запланирована на " + firstMeetingDate)).shouldBe(visible);
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(String.valueOf(replainDate));
        $(byText("Запланировать")).click();
        $("[data-test-id='replan-notification'] .notification__content ").shouldHave(text("У вас уже запланирована встреча на другую дату. Перепланировать?")).shouldBe(visible);
        $("[data-test-id=replan-notification] button").click();
        $("[data-test-id=success-notification] .notification__content").shouldHave((exactText("Встреча успешно запланирована на " + replainDate)))
                .shouldBe(visible);

    }
}