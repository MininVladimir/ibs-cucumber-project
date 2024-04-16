package tests.api_tests;

import api.Requests;
import io.cucumber.java.en.Then;

public class API_Cucumber_Steps {

    Requests requests = new Requests();

    @Then("Get product list")
    public void get_productList() {
        requests.getFood(4);
    }

    @Then("Add product with parameters: {string}, {string}, {string}")
    public void add_product(String value1, String value2, String value3) {
        requests.postFood(value1, value2, Boolean.valueOf(value3));
    }

    @Then("Clear data")
    public void clear_data() {
        requests.dataReset();
    }
}
