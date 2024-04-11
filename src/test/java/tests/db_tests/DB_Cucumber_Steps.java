package tests.db_tests;

import io.cucumber.java.en.Then;

import java.sql.SQLException;

import services.JDBC;

public class DB_Cucumber_Steps {

    JDBC jdbc = new JDBC();

    @Then("Compare all rows in table 'food' after insert row and after delete row with parameters: {string}, {string}, {int}")
    public void sql_assert(String value1, String value2, int value3) throws SQLException {
        jdbc.sqlAssert(jdbc.addProduct(value1, value2, value3), jdbc.deleteProduct(value1, value2, value3));
    }
}
