package co.com.hyunseda.market.service;

import co.com.hyunseda.market.domain.Carrito;
import co.com.hyunseda.market.domain.Product;
import co.com.hyunseda.market.infra.Subject;
import java.util.ArrayList;
import java.util.List;
//import java.util.List;

/**
 *
 * @author JUAN DAVID
 */
public class CarritoService extends Subject{
    private Carrito carrito;
    private List<Carrito> itemList;
    
   public CarritoService(Product listaP, int total){
        carrito = new Carrito(listaP,total);
   }
   
   public CarritoService(){
       
   }
   
   
    public Carrito getCarrito() {
        return carrito;
    }

    public List<Carrito> getItemList() {
        return itemList;
    }
    
    public void setCarrito(Carrito carrito)
    {
        this.carrito = carrito;
    }
    
    public void setCarrito(Carrito carrito, List<Carrito> prmList) {
        this.carrito = carrito;
        this.itemList = prmList;
        this.notifyAllObserves();
    }
    
}
