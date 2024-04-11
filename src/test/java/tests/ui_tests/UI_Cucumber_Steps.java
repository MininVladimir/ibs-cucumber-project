package tests.ui_tests;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.FoodPage;
import pages.MainPage;

public class UI_Cucumber_Steps {

    WebDriver driver = UI_Cucumber_Hooks.driver;
    MainPage mainPage = new MainPage(driver);
    FoodPage foodPage = new FoodPage(driver);

    @When("Open dropdown menu")
    public void dropdown_navigation_click(){
        mainPage.dropdownNavigationClick();
    }

    @Then("Go to food page")
    public void food_click() {
        mainPage.foodClick();
    }

    @When("Click 'Добавить' button")
    public void add_button_click(){
        foodPage.addButtonClick();
    }

    @And("Filling {string} to 'Наименование' field")
    public void name_field_filling(String value){
        foodPage.nameFieldFilling(value);
    }

    @And("Open dropdown menu of type product")
    public void dropdown_typeClick(){
        foodPage.dropdownTypeClick();
    }

    @And("Choose {string} product")
    public void type_product_choose(String type) {
        foodPage.typeProductChoose(type);
    }

    @And("Select checkbox according to the {string}")
    public void select_exotic_checkbox(String value) {
        foodPage.selectExoticCheckbox(value);
    }

    @And("Click 'Сохранить' button")
    public void save_button_click(){
        foodPage.saveButtonClick();
    }

    @Then("Multiple assert with parameters: {string}, {string}, {string}, {string}")
    public void multiple_assert(String value1,
                               String value2,
                               String value3,
                               String value4){
        foodPage.multipleAssert(value1, value2, value3, value4);
    }

}
