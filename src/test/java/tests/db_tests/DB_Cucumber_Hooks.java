package tests.db_tests;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import static properties.DatabaseProperties.*;
import static services.JDBC.sqlRequestMap;

public class DB_Cucumber_Hooks {

    public static Connection connection;

    private final Logger logger = LoggerFactory.getLogger(DB_Cucumber_Hooks.class);
    @Before("@db")
    @Step("Setup connection and clear table 'food'")
    public void setupConnection() {
        try {
            connection = DriverManager.getConnection(db, userName, password);
            PreparedStatement preparedStatement = connection.prepareStatement(sqlRequestMap.get("Truncate table food"));
            preparedStatement.execute();
        } catch (Exception e) {
            logger.error("\u001B[31m" + "@Before error: " + e.getMessage() + "\u001B[0m");
        }
    }

    @After("@db")
    @Step("Close connection")
    public void closeConnection(){
        try {
            connection.close();
        }catch (Exception e) {
            logger.error("\u001B[31m" + "@After error: " + e.getMessage() + "\u001B[0m");
        }
    }
}
