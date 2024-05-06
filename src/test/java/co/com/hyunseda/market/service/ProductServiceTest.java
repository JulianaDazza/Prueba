/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.com.hyunseda.market.service;

import co.com.hyunseda.market.domain.Product;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author JUAN DAVID
 */
public class ProductServiceTest {
    
    
    @Test
    public void testAddProduct() {
        // Crear un objeto ProductService mock o utilizar un objeto ProductService real si tienes acceso a una instancia
        ProductService productService = new ProductService();
        
        // Definir un producto de prueba
        Product product = new Product(1, "Producto de prueba", "Descripción de prueba", 10.0, 1);
        
        // Llamar al método para agregar el producto
        boolean result = productService.saveProduct(product);
        
        // Verificar si el producto se agregó correctamente
        assertTrue("El producto debería haberse agregado correctamente", result);
    }
    
}
