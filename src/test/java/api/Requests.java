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

    private void installSpec() {
        Specs.installSpec(Specs.requestSpec(URI), Specs.responseSpec(200));
    }

    @Step("Get product list without cookies")
    public void getFood(){
        installSpec();

        List<FoodRes> products = given()
        .when()
            .get(endpointMap.get("food"))
        .then()
            .statusCode(statusCodeMap.get("OK"))
            .extract().response().jsonPath().getList(jsonPathMap.get("all"), FoodRes.class);

        Assertions.assertAll(
                () -> Assertions.assertNotNull(products, "Product list is null"),
                () -> Assertions.assertFalse(products.stream().allMatch(x -> x.getName() == null), "Field 'name' in product list is null"),
                () -> Assertions.assertFalse(products.stream().allMatch(x -> x.getType() == null), "Field 'type' in product list is null")
        );
    }

    @Step("Get product list with cookies")
    public int getFood(String cookies, String name, String type, Boolean exotic){
        installSpec();

        List<FoodRes> products = given()
            .cookie(cookies)
        .when()
            .get(endpointMap.get("food"))
        .then()
            .statusCode(statusCodeMap.get("OK"))
            .extract().response().jsonPath().getList(jsonPathMap.get("all"), FoodRes.class);

        int listSize = products.size();

        Assertions.assertAll(
                () -> Assertions.assertNotNull(products, "Product list is null"),
                () -> Assertions.assertFalse(products.stream().allMatch(x -> x.getName() == null), "Field 'name' in product list is null"),
                () -> Assertions.assertFalse(products.stream().allMatch(x -> x.getType() == null), "Field 'type' in product list is null"),
                () -> Assertions.assertEquals(products.size(), listSize, "Product has not been added"),
                () -> Assertions.assertEquals(products.get(listSize - 1).getName(), name, "The 'Name' value of the added product does not match the test data"),
                () -> Assertions.assertEquals(products.get(listSize - 1).getType(), type, "The 'Type' value of the added product does not match the test data"),
                () -> Assertions.assertEquals(products.get(listSize - 1).isExotic(), exotic, "The 'Exotic' value of the added product does not match the test data")
        );
        return listSize;
    }

    @Step("Add product")
    public String postFood(String name, String type, Boolean exotic){
        installSpec();

        Map<String, Object> product = new HashMap<>();
        product.put("name", name);
        product.put("type", type);
        product.put("exotic", exotic);

        String cookies = given()
        .when()
            .body(product)
            .post(endpointMap.get("food"))
        .then()
            .statusCode(statusCodeMap.get("OK"))
            .extract().response().getHeader("Set-Cookie");
        return cookies;
    }

    @Step("Clear data")
    public void dataReset() {
        installSpec();

        given()
        .when()
            .post(endpointMap.get("reset"))
        .then()
            .statusCode(statusCodeMap.get("OK"));
    }
}
