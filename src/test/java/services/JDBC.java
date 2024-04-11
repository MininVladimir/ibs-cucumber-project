package services;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import tests.db_tests.DB_Cucumber_Hooks;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class JDBC extends DB_Cucumber_Hooks {

    public static final Map<String, String> sqlRequestMap;

    static {
        sqlRequestMap = new HashMap<>();
        sqlRequestMap.put("Insert into food", "INSERT INTO food (food_name, food_type, food_exotic)\n" +
                                                    "VALUES (?, ?, ?);");
        sqlRequestMap.put("Delete from food", "DELETE FROM food \n" +
                                            "WHERE food_name = ? \n" +
                                            "AND food_type = ? \n" +
                                            "AND food_exotic = ?");
        sqlRequestMap.put("Select all from food", "SELECT * FROM food;");
        sqlRequestMap.put("Truncate table food", "TRUNCATE TABLE food;");
    }

//    Batch method for insert and delete a record in the table "food"(accepts a request from sqlRequestMap as input)
    public void batchMethod(String sqlRequest,
                            Object value1,
                            Object value2,
                            Object value3) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sqlRequest);
        preparedStatement.setObject(1, value1);
        preparedStatement.setObject(2, value2);
        preparedStatement.setObject(3, value3);
        preparedStatement.addBatch();
        preparedStatement.executeBatch();
    }

//    Method for adding a product (uses the Batch method and is parameterized in the test class)
    @Step("Insert row in table 'food' with parameters")
    public ArrayList<ArrayList> addProduct(Object value1,
                                           Object value2,
                                           Object value3) throws SQLException {
        batchMethod(sqlRequestMap.get("Insert into food"), value1, value2, value3);

        return selectAllProducts();
    }

//    Method for delete a product (uses the Butch method and is parameterized in the test class)
    @Step("Delete row from table 'food' with parameters")
    public ArrayList<ArrayList> deleteProduct(Object value1,
                                              Object value2,
                                              Object value3) throws SQLException {
        batchMethod(sqlRequestMap.get("Delete from food"),value1, value2, value3);

        return selectAllProducts();
    }

//    Method for select all products(returns arraylist of arraylists)
    @Step("Select all from table 'food'")
    public ArrayList<ArrayList> selectAllProducts() throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sqlRequestMap.get("Select all from food"));
        ResultSet resultSet = preparedStatement.executeQuery();

        ArrayList<ArrayList> resultArray = new ArrayList<>();
        while(resultSet.next()) {
            ArrayList<Object> row = new ArrayList<>();
            for(int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                Object value = resultSet.getObject(i);
                row.add(value);
            }
            resultArray.add(row);
        }
        System.out.println(resultArray);
        return resultArray;
    }

//    Method-assert(compares arraylists of products after add product and after delete an added product from table "food")
    @Step("Compare all rows in table 'food' after insert row and after delete row")
    public void sqlAssert(Object value1,
                          Object value2){
        Assertions.assertFalse(value1.equals(value2));
    }
}
