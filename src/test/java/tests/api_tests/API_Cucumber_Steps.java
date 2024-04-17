package tests.api_tests;

import api.Requests;
import io.cucumber.java.en.Then;

public class API_Cucumber_Steps {

    Requests requests = new Requests();

    @Then("Get product list")
    public void get_productList() {
        requests.getFood();
    }

    @Then("Add product with parameters: {string}, {string}, {string}")
    public void add_product(String name, String type, String exotic) {
        requests.getFood(requests.postFood(name, type, Boolean.valueOf(exotic)),
                name,
                type,
                Boolean.valueOf(exotic)
        );
    }

    @Then("Clear data")
    public void clear_data() {
        requests.dataReset();
    }
}
