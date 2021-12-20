package budget;

import java.io.Serializable;

public class Product implements Serializable {
    String name;
    Double value;
    String type;
    Double balance;

    public Product(String name, Double value, String type, Double balance) {
        this.name = name;
        this.value = value;
        this.type = type;
        this.balance = balance;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
