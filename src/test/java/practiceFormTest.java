import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class practiceFormTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void fillTotalFormTest() {
        open("/automation-practice-form");
        $("#firstName").setValue("Ivan");
        $("#lastName").setValue("Petrov");
        $("#userEmail").setValue("ivan_petrov1978@mail.ru");
        $("#genterWrapper").$(byText("Male")).click();
        $("#userNumber").setValue("0123456789");

        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").selectOptionByValue("1978");
        $(".react-datepicker__month-select").selectOptionByValue("3");
        $(".react-datepicker__day--013").click();

        $("#subjectsInput").setValue("Biology").pressEnter();
        $("#hobbiesWrapper").$(byText("Reading")).click();

        $("#uploadPicture").uploadFile(new File("src/data/bug.jpg"));
        $("#currentAddress").setValue("Some address");
        $("#submit").scrollIntoView(false);
        $("#state").click();
        $(byText("NCR")).click();
        $("#city").click();
        $(byText("Delhi")).click();
        $("#submit").click();


        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".table").shouldHave(text("Student Name")).shouldHave(text("Ivan Petrov"));
        $(".table").shouldHave(text("Student Email")).shouldHave(text("ivan_petrov1978@mail.ru"));
        $(".table").shouldHave(text("Gender")).shouldHave(text("Male"));
        $(".table").shouldHave(text("Mobile")).shouldHave(text("0123456789"));
        $(".table").shouldHave(text("Date of Birth")).shouldHave(text("13 April,1978"));
        $(".table").shouldHave(text("Subjects")).shouldHave(text("Biology"));
        $(".table").shouldHave(text("Hobbies")).shouldHave(text("Reading"));
        $(".table").shouldHave(text("Picture")).shouldHave(text("bug.jpg"));
        $(".table").shouldHave(text("Address")).shouldHave(text("Some address"));
        $(".table").shouldHave(text("State and City")).shouldHave(text("NCR Delhi"));

    }
}
