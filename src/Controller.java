// package sample;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;


public class Controller implements Initializable {

    @FXML private ImageView ivPizza;
    @FXML private Label lblType;
    @FXML private RadioButton rbVege;
    @FXML private RadioButton rbHawaiian;
    @FXML private RadioButton rbSeafood;
    @FXML private Label lblSize;
    @FXML
    // pizza size
    private ChoiceBox<String> cbSize;
    private final String[] sizeItems = {"Small", "Medium", "Large"};
    private final ObservableList<String> sizeList = FXCollections.observableArrayList(sizeItems);
    @FXML private Label lblQuantity;
    //    private TextField tfQuantity;
    @FXML private Button btnOrder;
    @FXML private Button btnReset;
    @FXML
    private TextArea taSummary;
    @FXML
    private ToggleGroup tgType;
    @FXML
    private Button btnCalculateSales;
    @FXML
    private TextArea taSalesSummary;
    @FXML
    private Label lblCoupon;
    @FXML
    private CheckBox chkOne;
    @FXML
    private CheckBox chkTwo;
    @FXML
    private Button btnAddQty;
    // pizza object
    Pizza pizza = new Pizza();
    // drink object
    Drink drink = new Drink();
    // Fries
    Fries fries = new Fries();
    @FXML
    private Button btnMinusQty;
    @FXML
    private Button btnSalesReset;
    @FXML
    private CheckBox chkNone;
    @FXML
    private ImageView ivPizza1;
    @FXML
    private Label lblType1;
    @FXML
    private RadioButton rbCoke;
    @FXML
    private ToggleGroup tgType1;
    @FXML
    private RadioButton rbSprite;
    @FXML
    private Label lblDrinkSize;
    @FXML
    // drink size
    private ChoiceBox<String> cbDrinkSize;
    private final String[] sizeDrinkItems = {"500 ml", "1.25 Ltr."};
    private final ObservableList<String> sizeDrinkList = FXCollections.observableArrayList(sizeDrinkItems);
    @FXML
    private Button btnAddDrinkQty;
    @FXML
    private Button btnMinusDrinkQty;
    @FXML
    private Label lblDrinkQuantity;
    @FXML
    private Button btnCommit;
    @FXML
    private Button btnRollback;

//    Fries

    @FXML
    private ToggleGroup tgfries;

    @FXML
    private Button btnAddFriesQty;

    @FXML
    private Button btnMinusFriesQty;

    @FXML
    private Label lblFriesQuantity;
    @FXML private RadioButton rbsmall;
    @FXML private RadioButton rbmedium;
    @FXML private RadioButton rblarge;


    double totalCost=0;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        // pizza
        cbSize.setItems(sizeList);
        pizza.setQuantity(0); // default value to 0
        pizza.quantityProperty().addListener(new ChangeListener<Object>() {
            @Override
            public void changed(ObservableValue<? extends Object> observable, Object oldValue, Object newValue) {
                lblQuantity.setText(String.valueOf(pizza.getQuantity()));
            }
        });
        // drink
        cbDrinkSize.setItems(sizeDrinkList);
        drink.setQuantity(0); // default value to 0
        drink.quantityProperty().addListener(new ChangeListener<Object>() {
            @Override
            public void changed(ObservableValue<? extends Object> observable, Object oldValue, Object newValue) {
                lblDrinkQuantity.setText(String.valueOf(drink.getQuantity()));
            }
        });
        //Fries
        fries.setQuantity(0);
        fries.quantityProperty().addListener(new ChangeListener<Object>() {
            @Override
            public void changed(ObservableValue<? extends Object> observable, Object oldValue, Object newValue) {
                lblFriesQuantity.setText(String.valueOf(fries.getQuantity()));
            }
        });

    }

    @FXML
    private void handleOrderButtonAction(ActionEvent event) {
        double couponAmount = 0.0;
        DecimalFormat currency = new DecimalFormat("$,###.00");

        // Pizza type, size, price and quantity
        if (rbHawaiian.isSelected()) {
            pizza.setType(1); // 1 for Hawaiian, 2 for Seafood, 3 for Vege
            pizza.setSize(cbSize.getSelectionModel().getSelectedIndex()); // 0 for Small, 1 for Medium, 2 for Large
            pizza.setPrice(5.59);
            pizza.setQuantity(Integer.parseInt(lblQuantity.getText()));
            if (pizza.getQuantity()<=0){
                taSummary.appendText("Please select valid Pizza quantity\n");
            }
            if (pizza.getSize()==0){
                taSummary.appendText(rbHawaiian.getText() + "Small size cost is = "
                        + currency.format(pizza.getPrice()) + "\n");

                totalCost+= pizza.getQuantity()*pizza.getPrice();
            }
            else if (pizza.getSize()==1){
                taSummary.appendText(rbHawaiian.getText() + "Medium size cost is = "
                        + currency.format(pizza.getPrice()+3.4) + "\n");
                totalCost+=pizza.getQuantity()*(pizza.getPrice()+3.4);
            }
            else if (pizza.getSize()==2){
                taSummary.appendText(rbHawaiian.getText() + "large size cost is = "
                        + currency.format(pizza.getPrice()+5.4) + "\n");
                totalCost+= pizza.getQuantity()*(pizza.getPrice()+5.4);
            }
            else{
                taSummary.appendText("Please select pizza size\n\n");
            }

        } else if (rbSeafood.isSelected()) {
            pizza.setType(2);
            pizza.setSize(cbSize.getSelectionModel().getSelectedIndex());
            pizza.setPrice(5.59);
            pizza.setQuantity(Integer.parseInt(lblQuantity.getText()));
            if (pizza.getQuantity()<=0){
                taSummary.appendText("Please select valid Pizza quantity\n");
            }
            if (pizza.getSize()==0){
                taSummary.appendText(rbSeafood.getText() + "Small size cost is = "
                        + currency.format(pizza.getPrice()) + "\n");
                totalCost+= (pizza.getQuantity()*pizza.getPrice());
            }
            else if (pizza.getSize()==1){
                taSummary.appendText(rbSeafood.getText() + "Medium size cost is = "
                        + currency.format(pizza.getPrice()+3.4) + "\n");
                totalCost+=pizza.getQuantity()*(pizza.getPrice()+3.4);
            }
            else if (pizza.getSize()==2){
                taSummary.appendText(rbSeafood.getText() + "large size cost is = "
                        + currency.format(pizza.getPrice()+5.4) + "\n");
                totalCost+= pizza.getQuantity()*(pizza.getPrice()+5.4);
            }
            else{
                taSummary.appendText("Please select pizza size\n\n");
            }

        } else if (rbVege.isSelected()) {
            pizza.setType(3);
            pizza.setSize(cbSize.getSelectionModel().getSelectedIndex());
            pizza.setPrice(4.59);
            pizza.setQuantity(Integer.parseInt(lblQuantity.getText()));
            if (pizza.getQuantity()<=0){
                taSummary.appendText("Please select valid Pizza quantity\n");
            }
            if (pizza.getSize()==0){
                taSummary.appendText(rbVege.getText() + "Small size cost is = "
                        + currency.format(pizza.getPrice()) + "\n");

                totalCost+= pizza.getQuantity()*pizza.getPrice();
            }
            else if (pizza.getSize()==1){
                taSummary.appendText(rbVege.getText() + "Medium size cost is = "
                        + currency.format(pizza.getPrice()+3.4) + "\n");
                totalCost+=pizza.getQuantity()*(pizza.getPrice()+3.4);
            }
            else if (pizza.getSize()==2){
                taSummary.appendText(rbVege.getText() + "large size cost is = "
                        + currency.format(pizza.getPrice()+5.4) + "\n");
                totalCost+= pizza.getQuantity()*(pizza.getPrice()+5.4);
            }
            else{
                taSummary.appendText("Please select pizza size\n\n");
            }


        } else {
            taSummary.appendText("Please select a Pizza type!\n");
        }

        // Drink type, size, price and quantity
        if (rbCoke.isSelected()) {
            drink.setType(1); // 1 for Coke, 2 for Sprite
            drink.setSize(cbDrinkSize.getSelectionModel().getSelectedIndex()); // 0 for 500 ml, 1 for 1.25 Ltr.
            if (drink.getQuantity()<=0){
                taSummary.appendText("Please select valid Drink quantity\n");
            }
            if (drink.getSize()==0){
                drink.setPrice(3.59);
                taSummary.appendText(rbCoke.getText() + " "+ " cost is = "
                        + currency.format(drink.getPrice()) + " "
                        + "\n");
                totalCost+= drink.getQuantity()* drink.getPrice();
            }
            else if (drink.getSize()==1){
                drink.setPrice(4.4);
                taSummary.appendText(rbCoke.getText() + " "+" cost is = "
                        + currency.format(drink.getPrice()) + " "
                        + "\n");
                totalCost+= drink.getQuantity()* drink.getPrice();
            }

        } else if (rbSprite.isSelected()) {
            drink.setType(2);
//            drink.setSize(cbDrinkSize.getSelectionModel().getSelectedIndex());
            if (drink.getQuantity()<=0){
                taSummary.appendText("Please select valid Drink quantity\n");
            }
            if (drink.getSize()==0){
                drink.setPrice(3.59);
                taSummary.appendText(rbCoke.getText() + " "+" cost is = "
                        + currency.format(drink.getPrice()) + " "
                        + "\n");
                totalCost+= drink.getQuantity()* drink.getPrice();
            }
            else if (drink.getSize()==1){
                drink.setPrice(4.4);
                taSummary.appendText(rbCoke.getText() + " "+" cost is = "
                        + currency.format(drink.getPrice()) + " "
                        + "\n");
                totalCost+= drink.getQuantity()* drink.getPrice();
            }

        } else {
            taSummary.appendText("Please select a Drink type!\n");
        }

//        Fries
        if(rbsmall.isSelected()){
            fries.setType(1);
            fries.setPrice(2.5);
            if (fries.getQuantity()<=0){
                taSummary.appendText("Please select valid Fries quantity\n");
            }
            fries.setQuantity(Integer.parseInt(lblFriesQuantity.getText()));
            taSummary.appendText( " Small size Fries cost = "
            + currency.format(fries.getPrice())+"\n ");
            totalCost+= fries.getQuantity()* fries.getPrice();
        }
        else if(rbmedium.isSelected()){
            fries.setType(2);
            fries.setPrice(3.5);
            fries.setQuantity(Integer.parseInt(lblFriesQuantity.getText()));
            if (fries.getQuantity()<=0){
                taSummary.appendText("Please select valid Fries quantity\n");
            }
            taSummary.appendText("Medium size Fries cost =  "
                    + currency.format(fries.getPrice())+"\n ");
            totalCost+= fries.getQuantity()* fries.getPrice();

        }
        else  if(rblarge.isSelected()){
            fries.setType(3);
            fries.setPrice(4.5);
            fries.setQuantity(Integer.parseInt(lblFriesQuantity.getText()));
            if (fries.getQuantity()<=0){
                taSummary.appendText("Please select valid Fries quantity\n");
            }
            taSummary.appendText("Large size Fries cost =  "
                    + currency.format(fries.getPrice())+"\n ");
            totalCost+= fries.getQuantity()* fries.getPrice();
        }

writeRecord(pizza, drink, fries);

        taSummary.appendText("\n\nTotal Cost = "+currency.format(totalCost)+"\n");
    }


    @FXML
    private void handleResetButtonAction(ActionEvent event) {
        // pizza
        rbHawaiian.setSelected(false);
        rbVege.setSelected(false);
        rbSeafood.setSelected(false);
        lblQuantity.setText(null);
        cbSize.setValue(null);
        // drink
        rbCoke.setSelected(false);
        rbSprite.setSelected(false);
        lblDrinkQuantity.setText(null);
        cbDrinkSize.setValue(null);

        pizza.setQuantity(0);
        drink.setQuantity(0);
        fries.setQuantity(0);
        rbsmall.setSelected(false);
        rbmedium.setSelected(false);
        rblarge.setSelected(false);
        lblFriesQuantity.setText(null);
        totalCost=0;
        taSummary.setText(null);
    }

    @FXML
    private void handleCalculateSalesButtonAction(ActionEvent event) {
        // Read sales data/records, and then calculate its sales with subtotal and total sales
        readRecord();
    }

    public void readRecord() {

        try {
            // Load the JDBC Driver
            Class.forName("org.mariadb.jdbc.Driver");

            // Establish a connection
            Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/test", "user1", "pass1");

            // Create a statement
            Statement stmt = conn.createStatement();

            // Execute a SQL statement
            String sqlText = "SELECT * FROM order_line"; // order_line has ten columns
            ResultSet rs = stmt.executeQuery(sqlText);

            // Calculate subtotal
            double subtotal = 0.0; // fixed it for pizza
            double drinkSubtotal = 0.0; // drink
            double friesSubtotal=0.0;
            double total = 0.0;         // sales total = pizza subtotal + drink subtotal
            DecimalFormat currency = new DecimalFormat("$,###.00");

            // Set a heading
            taSalesSummary.appendText("Order ID \t Pizza Type \t" + "Pizza Price \t" + "Pizza Quantity \t\t" + "Pizza Size \t" + "Pizza Subtotal \t\t" // pizza
                    + "Drink Type \t" + "Drink Price \t" + "Drink Quantity\t\t" + "Drink Size \t" + "Drink Subtotal \t\t" // drink
                    + "Fries Size \t" + "Fries Price\t" + "Fries Quantity \t" + "Fries Subtotal \t\t"+ "\n");

            // Read data from a database (order_line table) using the index number of the table or name of the column
            // Iterate through the result set and display the order information
            while (rs.next()) {
                // order ID
                int orderId = rs.getInt(1);
                // pizza
                int type = rs.getInt(2);
                double price = rs.getDouble(3);
                int quantity = rs.getInt(4);
                int size = rs.getInt(5);
                subtotal = price * quantity;

                // drink
                int drinkType = rs.getInt(6);
                double drinkPrice = rs.getDouble(7);
                int drinkQuantity = rs.getInt(8);
                int drinkSize = rs.getInt(9);
                drinkSubtotal = drinkPrice * drinkQuantity;
                //fries
                int friesSize = rs.getInt(10);
                double friesPrice = rs.getDouble(11);
                int friesQuantity = rs.getInt(12);

                friesSubtotal = friesPrice * friesQuantity;
                taSalesSummary.appendText("\t" +orderId + "\t\t" + type + " \t\t\t" + currency.format(price) // pizza
                        + "   \t\t" + quantity + "\t\t\t\t" + size + "\t\t"
                        + currency.format(subtotal) + "\t\t\t\t"
                        + drinkType + "\t\t\t" + currency.format(drinkPrice) // drink
                        + "\t\t" + drinkQuantity + "\t\t\t\t" + drinkSize + "\t\t\t"
                        + currency.format(drinkSubtotal) + "\t\t\t"
                        + friesSize + "\t\t" + currency.format(friesPrice) // drink
                        + "\t\t\t" + friesQuantity + "\t\t\t"
                        + currency.format(friesSubtotal) + "\t\t" + "\n"); // coupon
                total += (subtotal + drinkSubtotal+friesSubtotal); // sales total = pizza subtotal + drink subtotal
            }
            taSalesSummary.appendText("Total Sales: " + currency.format(total) + "\n");
            taSalesSummary.appendText("Total: " + currency.format(total) + "\n");

            // Close the connection
            conn.close();
        } catch (SQLException ex) {
            System.out.println("ex " + ex);
        } catch (ClassNotFoundException ex) {
            System.out.println("ex " + ex);
        }
    }
    public void writeRecord(Pizza pizza, Drink drink, Fries fries) {

        try {
            // Load the JDBC Driver
            Class.forName("org.mariadb.jdbc.Driver");

            // Establish a connection
            Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/test", "user1", "pass1");

            // Set auto-commit to be false
            conn.setAutoCommit(false); // default is true

//            // Create a statement
//            Statement stmt = conn.createStatement();

            String sqlText = "INSERT INTO order_line (pizza_type, pizza_price, pizza_quantity, pizza_size, "
                    + "drink_type, drink_price, drink_quantity, drink_size, "
                    + "fries_size, fries_price, fries_quantity) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?,?, ?, ?,?)"; // insert a parameter with ?

            // Create a prepared statement that is used to execute a precompiled SQL statement
            PreparedStatement preparedStmt = conn.prepareStatement(sqlText);

            // Use setter method to set the values for the parameters before excuting statements
            preparedStmt.setInt(1, pizza.getType());
            preparedStmt.setDouble(2, pizza.getPrice());
            preparedStmt.setInt(3, pizza.getQuantity());
            preparedStmt.setInt(4, pizza.getSize());
            preparedStmt.setInt(5, drink.getType());
            preparedStmt.setDouble(6, drink.getPrice());
            preparedStmt.setInt(7, drink.getQuantity());
            preparedStmt.setInt(8, drink.getSize());
            preparedStmt.setDouble(9, fries.getSize());
            preparedStmt.setDouble(10, fries.getPrice());
            preparedStmt.setDouble(11, fries.getQuantity());

            // Execute a prepared statemet
            preparedStmt.executeUpdate();

            // Commit transactions
            btnCommit.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent event) {
                    try {
                        conn.commit(); // Makes all changes made

                        // set auto-commit mode to be true
                        conn.setAutoCommit(true);

                        // Close the connection
                        conn.close();
                    } catch (SQLException ex) {
                        System.out.println("ex " + ex);
                    }
                }
            });

            // Rollback transactions
            btnRollback.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent event) {
                    try {
                        conn.rollback(); // Undoes all changes made in the current transaction

                        // set auto-commit mode to be true
                        conn.setAutoCommit(true);

                        // Close the connection
                        conn.close();
                    } catch (SQLException ex) {
                        System.out.println("ex " + ex);
                    }
                }
            });
//
//            // Close the connection
//            conn.close();
        } catch (SQLException ex) {
            System.out.println("ex " + ex);
        } catch (ClassNotFoundException ex) {
            System.out.println("ex " + ex);
        }
    }


    @FXML
    // pizza
    private void handleAddQuantity(ActionEvent event) {
        pizza.setQuantity(pizza.getQuantity() + 1); // increase quantity by 1 after clicking a + button
    }

    @FXML
    // pizza
    private void handleMinusQuantity(ActionEvent event) {
        pizza.setQuantity(pizza.getQuantity() - 1); // decrease quantity by 1 after clicking a - button
    }

    @FXML
    private void handleSalesReset(ActionEvent event) {
        taSalesSummary.setText(null);
    }

    @FXML
    // drink
    private void handleAddDrinkQuantity(ActionEvent event) {
        drink.setQuantity(drink.getQuantity() + 1); // increase quantity by 1 after clicking a + button
    }

    @FXML
    // drink
    private void handleMinusDrinkQuantity(ActionEvent event) {
        drink.setQuantity(drink.getQuantity() - 1); // decrease quantity by 1 after clicking a - button
    }

    @FXML
    private void handleAddFriesQuantity(ActionEvent event){
        fries.setQuantity(fries.getQuantity()+1);
    }
    @FXML
    private void handleMinusFriesQuantity(ActionEvent event){
        fries.setQuantity(fries.getQuantity()-1);
    }

}
