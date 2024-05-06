package co.com.hyunseda.market.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
/**
 *
 * @author JUAN DAVID
 */
public class Carrito {
    @JsonProperty("producto")
    private Product producto;
    @JsonProperty("totalArticulos")
    int totalArticulos;

    public Carrito(Product producto, int totalArticulos) {
        this.producto = producto;
        this.totalArticulos = totalArticulos;
    }

    public Product getProducto() {
        return producto;
    }

    public void setProducto(Product producto) {
        this.producto = producto;
    }

    public int getTotalArticulos() {
        return totalArticulos;
    }

    public void setTotalArticulos(int totalArticulos) {
        this.totalArticulos = totalArticulos;
    }
    
    
}

