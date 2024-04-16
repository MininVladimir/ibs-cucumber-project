package api;

import io.qameta.allure.Step;
import model.FoodRes;
import model.Specs;
import org.junit.jupiter.api.Assertions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static properties.APIProperties.*;

public class Requests {

    @Step("Get product list")
    public void getFood(int size){
        Specs.installSpec(Specs.requestSpec(URI), Specs.responseSpec(200));

        List<FoodRes> products = given()
        .when()
            .get(endpointMap.get("food"))
        .then()
            .statusCode(statusCodeMap.get("OK"))
            .extract().response().jsonPath().getList(jsonPathMap.get("all"), FoodRes.class);

        Assertions.assertAll(
                () -> Assertions.assertNotNull(products),
                () -> Assertions.assertFalse(products.stream().allMatch(x -> x.getName() == null)),
                () -> Assertions.assertFalse(products.stream().allMatch(x -> x.getType() == null)),
                () -> Assertions.assertEquals(products.size(), size)
        );
    }

    @Step("Add product")
    public void postFood(String value1, String value2, Boolean value3){
        Specs.installSpec(Specs.requestSpec(URI), Specs.responseSpec(200));

        Map<String, Object> product = new HashMap<>();
        product.put("name", value1);
        product.put("type", value2);
        product.put("exotic", value3);

        given()
        .when()
            .body(product)
            .post(endpointMap.get("food"))
        .then()
            .statusCode(statusCodeMap.get("OK"));
    }

    @Step("Clear data")
    public void dataReset() {
        Specs.installSpec(Specs.requestSpec(URI), Specs.responseSpec(200));

        given()
        .when()
            .post(endpointMap.get("reset"))
        .then()
            .statusCode(statusCodeMap.get("OK"));
    }
}
