package co.com.hyunseda.market.service;


import co.com.hyunseda.market.access.IProductRepository;
import co.com.hyunseda.market.domain.Category;
import co.com.hyunseda.market.domain.Product;
import co.com.hyunseda.market.access.ICategoryRepository;
import co.com.hyunseda.market.access.IProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Libardo Pantoja, Julio Hurtado
 */
public class ProductService {

    IProductRepository productR;
    CategoryService categoryService;
    private List<Product> products;
    
    public ProductService(IProductRepository productR/*, CategoryService categoryService*/) {
        this.productR = productR;
        products = new ArrayList<>();
        /*this.categoryService = categoryService;*/
    }
    
    public ProductService(){
        
    }
    
  
    /*public boolean saveProduct(int id,String name, String description, double price, List<String> categoryNames /*llega la lista de categorias) {
        
        //Category category = (Category) categoryService.findCategoryByName(categoryName);
        
        Product newProduct = new Product();
        Category newCategory = new Category();
        List<Category> categories = new ArrayList();
        
        newProduct.setProductId(id);
        newProduct.setName(name);
        newProduct.setDescription(description);
        newProduct.setPrice(price);
        
        for (int i = 0; i < categoryNames.size();i++){
            
            categories.add(categoryService.findCategoryId(categoryNames.get(i))); 
        }
        
        newProduct.setCategory(categories);
        
        if (newProduct.getName().isEmpty() || newProduct.getDescription().isEmpty() || newProduct.getPrice() <= 0 || categoryNames.isEmpty()) {
            return false;
        }

        // Guardar el producto
        try {
            productR.save(newProduct);
            productR.saveProductCategory(newProduct, newCategory);
            return true;
        } catch (Exception e) {
            // Manejar excepciones al guardar el producto, si es necesario
            e.printStackTrace();
            return false;
        }

    }*/
    
    public boolean saveProduct(Product producto) {
      products.add(producto);
      return productR.save(producto);
    }
    public List<Product> listProducts() {
        return productR.list();
    }

    
    public Product findProductById(int id){
        return productR.findById(id);
    }
    
    public List<Product> findProductsByName(String name) {
        return productR.findByName(name);
    }
    
   public Product findPorName(String atrName)
    {
        return productR.findPorName(atrName);
    }


}
