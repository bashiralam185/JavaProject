// package sample;

// Author Bashir Alam And Aqeel Ahamd
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Item {

    private int type;
    private int size;
    private double price;
    private final IntegerProperty quantity = new SimpleIntegerProperty();
    public final int getQuantity() {
        return quantity.get();  // get method works with a primitive value
    }

    public final void setQuantity(int value) {
        quantity.set(value);    // set method works with a primitive value
    }

    public final IntegerProperty quantityProperty() {
        return quantity;
    }


    public double getPrice() {
        return price;
    }


    public void setPrice(double price) {
        this.price = price;
    }


    public int getSize() {
        return size;
    }


    public void setSize(int size) {
        this.size = size;
    }


    public int getType() {
        return type;
    }


    public void setType(int type) {
        this.type = type;
    }
}
