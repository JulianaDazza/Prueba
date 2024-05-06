package co.com.hyunseda.market.access;

import co.com.hyunseda.market.domain.Category;
import co.com.hyunseda.market.domain.Product;
import java.util.List;

/**
 * @author JUAN DAVID
 * @brief Interfaz para interactuar con productos.
 */
public interface IProductRepository {
    
    /**
     * @brief Guarda un producto.
     * @param newProduct El nuevo producto a guardar.
     * @return Verdadero si el producto se guardó correctamente, de lo contrario falso.
     */
    boolean save(Product newProduct);
    
    /**
     * @brief Obtiene una lista de todos los productos.
     * @return Una lista que contiene todos los productos.
     */
    List<Product> list();
    
    /**
     * @brief Encuentra productos por su nombre.
     * @param name El nombre del producto a encontrar.
     * @return Una lista de productos con el nombre especificado.
     */
    List<Product> findByName(String name);
    
    /**
     * @brief Encuentra un producto por su ID.
     * @param id El ID del producto a encontrar.
     * @return El producto con el ID especificado, o null si no se encuentra.
     */
    Product findById(int id);
    
    /**
     * @brief Encuentra un producto por su nombre.
     * @param prmName El nombre del producto a encontrar.
     * @return El producto con el nombre especificado, o null si no se encuentra.
     */
    Product findPorName(String prmName);
}

