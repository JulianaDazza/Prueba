/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.com.hyunseda.market.access;

import co.com.hyunseda.market.domain.Category;
import co.com.hyunseda.market.domain.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author JUAN DAVID
 */
public class ProductRestRepository implements IProductRepository{
    
    private String apiUrl = "http://localhost:8001/products";
    
    public ProductRestRepository() {

    }

    @Override
    public boolean save(Product newProduct) {
        HttpClient httpClient = HttpClients.createDefault();
        ObjectMapper mapper = new ObjectMapper();
        //List<Category> list = new ArrayList<>();
        try {

            // Definir la URL de la API REST de categorias
            String apiUrl = "http://localhost:8001/products";
            
            // Convertir el objeto Category a JSON
            String jsonProduct = mapper.writeValueAsString(newProduct);  
            
            // Crear una solicitud POST para obtener todas las categorias
            HttpPost request = new HttpPost(apiUrl);
            request.setHeader("Content-Type", "application/json");
            request.setEntity(new StringEntity(jsonProduct));
            
            // Ejecutar la solicitud y obtener la respuesta
            HttpResponse response = httpClient.execute(request);

            // Verificar el código de estado de la respuesta
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.SC_OK || statusCode == HttpStatus.SC_CREATED) {
                return true;
            } else {
                // La solicitud falló, mostrar el código de estado
                Logger.getLogger(ProductRestRepository.class.getName()).log(Level.SEVERE, "Error al guardar el producto. Código de estado: " + statusCode);
                return false;
            }
        } catch (IOException ex) {
            Logger.getLogger(ProductRestRepository.class.getName()).log(Level.SEVERE, "Error al realizar la solicitud HTTP", ex);
            return false;
        }
    }

    @Override
    public List<Product> list() {
        HttpClient httpClient = HttpClients.createDefault();
        ObjectMapper mapper = new ObjectMapper();
        List<Product> list = new ArrayList<>();
        try {

            // Definir la URL de la API REST de productos
           //String apiUrl = "http://localhost:8001/products";
            // Crear una solicitud GET para obtener todos los productos
            HttpGet request = new HttpGet(apiUrl);

            // Ejecutar la solicitud y obtener la respuesta
            HttpResponse response = httpClient.execute(request);

            // Verificar el código de estado de la respuesta
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                // La solicitud fue exitosa, procesar la respuesta
                String jsonResponse = EntityUtils.toString(response.getEntity());

                // Mapear la respuesta JSON a objetos Product
                Product[] products = mapper.readValue(jsonResponse, Product[].class);

                for (Product product : products) {
                    list.add(product);
                }

            } else {
                // La solicitud falló, mostrar el código de estado
                Logger.getLogger(ProductRestRepository.class.getName()).log(Level.SEVERE, null, "Error al obtener productos. Código de estado: " + statusCode);
            }
        } catch (IOException ex) {
            Logger.getLogger(ProductRestRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public List<Product> findByName(String name) {
        HttpClient httpClient = HttpClients.createDefault();
        ObjectMapper mapper = new ObjectMapper();
        List<Product> list = new ArrayList<>();
        try {
            // Definir la URL de la API REST de productos con el nombre como parámetro de consulta
            //String apiUrl = "http://localhost:8001/products/" + URLEncoder.encode(name, StandardCharsets.UTF_8.toString());

            // Crear una solicitud GET para buscar productos por nombre
            HttpGet request = new HttpGet(apiUrl);

            // Ejecutar la solicitud y obtener la respuesta
            HttpResponse response = httpClient.execute(request);

            // Verificar el código de estado de la respuesta
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                // La solicitud fue exitosa, procesar la respuesta
                String jsonResponse = EntityUtils.toString(response.getEntity());

                // Mapear la respuesta JSON a objetos Product
                Product[] products = mapper.readValue(jsonResponse, Product[].class);

                /*// Agregar los productos encontrados a la lista
                list.addAll(Arrays.asList(products));*/
                
                for (Product product : products) {
                  // for(Category cate :categories){
                   if(product.getProductName().equalsIgnoreCase(name))
                    list.add(product);
                   //}
                }
                
            } else {
                // La solicitud falló, mostrar el código de estado
                Logger.getLogger(ProductRestRepository.class.getName()).log(Level.SEVERE, "Error al obtener productos. Código de estado: " + statusCode);
            }
        } catch (IOException ex) {
            Logger.getLogger(ProductRestRepository.class.getName()).log(Level.SEVERE, "Error al realizar la solicitud HTTP", ex);
        }
        return list;
    }

/*    @Override
    public List<Product> findByCategory(Category category) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }*/

    @Override
    public Product findById(int id) {
        HttpClient httpClient = HttpClients.createDefault();
        ObjectMapper mapper = new ObjectMapper();
        //Product product = null;
        try {
            // Definir la URL de la API REST de productos con el ID como parámetro de consulta
            //String apiUrl = "http://localhost:8001/products/";

            // Crear una solicitud GET para buscar un producto por su ID
            HttpGet request = new HttpGet(apiUrl);

            // Ejecutar la solicitud y obtener la respuesta
            HttpResponse response = httpClient.execute(request);

            // Verificar el código de estado de la respuesta
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                // La solicitud fue exitosa, procesar la respuesta
                String jsonResponse = EntityUtils.toString(response.getEntity());

                // Mapear la respuesta JSON a un objeto Product
                Product[]  products = mapper.readValue(jsonResponse, Product[].class);
                
                for (Product product : products) {
                  // for(Category cate :categories){
                   if(product.getProductId()==id )
                    return product;
                }
               
            } else {
                // La solicitud falló, mostrar el código de estado
                Logger.getLogger(ProductRestRepository.class.getName()).log(Level.SEVERE, "Error al obtener el producto. Código de estado: " + statusCode);
            }
        } catch (IOException ex) {
            Logger.getLogger(ProductRestRepository.class.getName()).log(Level.SEVERE, "Error al realizar la solicitud HTTP", ex);
        }
        return null;
    }

    @Override
    public Product findPorName(String prmName) {
        HttpClient httpClient = HttpClients.createDefault();
        ObjectMapper mapper = new ObjectMapper();
        //Product product = null;
        try {
            // Definir la URL de la API REST de productos con el Nombre como parámetro de consulta
            //String apiUrl = "http://localhost:8001/products/name/{prmName}";

            // Crear una solicitud GET para buscar un producto por su ID
            HttpGet request = new HttpGet(apiUrl);

            // Ejecutar la solicitud y obtener la respuesta
            HttpResponse response = httpClient.execute(request);

            // Verificar el código de estado de la respuesta
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                // La solicitud fue exitosa, procesar la respuesta
                String jsonResponse = EntityUtils.toString(response.getEntity());

                // Mapear la respuesta JSON a un objeto Product
                Product[]  products = mapper.readValue(jsonResponse, Product[].class);
                
                for (Product product : products) {
                  // for(Category cate :categories){
                   if(product.getProductName().equals(prmName))
                    return product;
                }
               
            } else {
                // La solicitud falló, mostrar el código de estado
                Logger.getLogger(ProductRestRepository.class.getName()).log(Level.SEVERE, "Error al obtener el producto. Código de estado: " + statusCode);
            }
        } catch (IOException ex) {
            Logger.getLogger(ProductRestRepository.class.getName()).log(Level.SEVERE, "Error al realizar la solicitud HTTP", ex);
        }
        return null;

    }
    
}
