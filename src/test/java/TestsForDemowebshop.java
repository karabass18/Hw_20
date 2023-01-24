import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.is;

import static io.restassured.RestAssured.given;

public class TestsForDemowebshop {

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "https://demowebshop.tricentis.com";
    }

    @Test

    public void openGiftCardsPage(){

        String cookie01Value = "754c43f3fb666b4689fd8452291c08520e941e1737ad17b31babd87059cc27ae";
        String cookie02Value = "754c43f3fb666b4689fd8452291c08520e941e1737ad17b31babd87059cc27ae";
        String cookie03Value = "7d10b360-97ad-4cb2-985a-94a1f2708e79";
        given()
                .filter(new AllureRestAssured())
                .cookie("ARRAffinity", cookie01Value)
                .cookie("ARRAffinitySameSite", cookie02Value)
                .cookie("Nop.customer", cookie03Value)//
                .when()
                .get("gift-cards")
                .then()
                .log().all()
                .statusCode(200)
                .body("html.head.title", is("Demo Web Shop. Gift Cards"));

    }


    @Test
    public void sortGiftCardsByPrice() {


        String cookie01Value = "754c43f3fb666b4689fd8452291c08520e941e1737ad17b31babd87059cc27ae";
        String cookie02Value = "754c43f3fb666b4689fd8452291c08520e941e1737ad17b31babd87059cc27ae";
        String cookie03Value = "7d10b360-97ad-4cb2-985a-94a1f2708e79";

        given()
                .filter(new AllureRestAssured())
                .cookie("ARRAffinity", cookie01Value)
                .cookie("ARRAffinitySameSite", cookie02Value)
                .cookie("Nop.customer", cookie03Value)
                .when()
                .formParam("orderby", 11)
                .get("gift-cards")
                .then()
                .log().all()
                .statusCode(200)
                //Проверяем, что первый товар на странице самый дорогой (карта на $100)
                .body("**.find{it.@class=='product-grid'}.div[0].div[0].div[1].h2.a", is("$100 Physical Gift Card"));

    }
}
