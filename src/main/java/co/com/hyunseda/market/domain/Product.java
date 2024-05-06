/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.com.hyunseda.market.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
/**
 *
 * @author JUAN DAVID
 */
public class Product {
    @JsonProperty("productId")
    private int productId;
    @JsonProperty("productName")
    private String productName;
    @JsonProperty("productDescription")
    private String productDescription;
    @JsonProperty("productPrice")
    private double productPrice;
    @JsonProperty("productStock")
    private double productStock;
    @JsonProperty("productCategories")
    private List<Category> productCategories;
    @JsonProperty("productURL")
    private String image;
    

    // Constructor parametrizado
    public Product(int productId, String productName, String productDescription,
                   double productPrice, double productStock/*, List<Category> productCategories*/) {
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productStock = productStock;
        //this.productCategories = productCategories;
    }
    
    public Product(){
        
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public double getProductStock() {
        return productStock;
    }

    public void setProductStock(double productStock) {
        this.productStock = productStock;
    }

    public List<Category> getProductCategories() {
        return productCategories;
    }

    public void setProductCategories(List<Category> productCategories) {
        this.productCategories = productCategories;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    
}
