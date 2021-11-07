package Condos.models;



import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Product {
    private String id_P;//8B01,8B02..,16B01,16B01
    private String name_P;
    private float price_P;
    private int quantity_P;
    private String image_P;

    public Product(){

    }
    public Product(String id_P, String name_P, float price_P, int quantity_P, String image_P) {
        this.id_P = id_P;
        this.name_P = name_P;
        this.price_P = price_P;
        this.quantity_P = quantity_P;
        this.image_P = image_P;
    }

    public String getId_P() {
        return id_P;
    }

    public void setId_P(String id_P) {
        this.id_P = id_P;
    }

    public String getName_P() {
        return name_P;
    }

    public void setName_P(String name_P) {
        this.name_P = name_P;
    }

    public float getPrice_P() {
        return price_P;
    }

    public void setPrice_P(float price_P) {
        this.price_P = price_P;
    }

    public int getQuantity_P() {
        return quantity_P;
    }

    public void setQuantity_P(int quantity_P) {
        this.quantity_P = quantity_P;
    }

    public String getImage_P() {
        return image_P;
    }

    public void setImage_P(String image_P) {
        this.image_P = image_P;
    }

    public void increaseStock(int quantity){
        if(quantity >0){
            quantity_P -= quantity;
        }
    }


    public void decreaseStock(int quantity){
        System.out.println("d 1");
        if(quantity > 0 && quantity <= this.quantity_P){
            this.quantity_P-=quantity;
            System.out.println("d 2");
        }
    }


}
