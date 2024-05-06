package co.com.hyunseda.market.access;

import co.com.hyunseda.market.domain.Category;
import co.com.hyunseda.market.domain.Product;

import java.util.List;

/**
 * @author JUAN DAVID
 * @brief Interfaz para interactuar con categorías.
 */
public interface ICategoryRepository {
    
    /**
     * @brief Guarda una categoría.
     * @param category La categoría a guardar.
     * @return Verdadero si la categoría se guardó correctamente, de lo contrario falso.
     */
    boolean save(Category category);
    
    /**
     * @brief Obtiene una lista de todas las categorías.
     * @return Una lista que contiene todas las categorías.
     */
    List<Category> list();
    
    /**
     * @brief Encuentra una categoría por su ID.
     * @param categoryId El ID de la categoría a encontrar.
     * @return La categoría con el ID especificado, o null si no se encuentra.
     */
    Category findById(Long categoryId);
    
    /**
     * @brief Encuentra una categoría por su nombre.
     * @param categoryName El nombre de la categoría a encontrar.
     * @return La categoría con el nombre especificado, o null si no se encuentra.
     */
    Category findByName(String categoryName);
}

