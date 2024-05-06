/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.com.hyunseda.market.access;

import co.com.hyunseda.market.domain.Category;
import co.com.hyunseda.market.domain.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author JUAN DAVID
 */
public class CategoryRestRepository implements ICategoryRepository{

    private String apiUrl = "http://localhost:8001/categories";
    
    @Override
    public boolean save(Category category) {
        HttpClient httpClient = HttpClients.createDefault();
        ObjectMapper mapper = new ObjectMapper();
        //List<Category> list = new ArrayList<>();
        try {

            // Definir la URL de la API REST de categorias
            String apiUrl = "http://localhost:8001/categories";
            
            // Convertir el objeto Category a JSON
            String jsonCategory = mapper.writeValueAsString(category);  
            
            // Crear una solicitud POST para obtener todas las categorias
            HttpPost request = new HttpPost(apiUrl);
            request.setHeader("Content-Type", "application/json");
            request.setEntity(new StringEntity(jsonCategory));
            
            // Ejecutar la solicitud y obtener la respuesta
            HttpResponse response = httpClient.execute(request);

            // Verificar el código de estado de la respuesta
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.SC_OK || statusCode == HttpStatus.SC_CREATED) {
                return true;
            } else {
                // La solicitud falló, mostrar el código de estado
                Logger.getLogger(CategoryRestRepository.class.getName()).log(Level.SEVERE, "Error al guardar la categoría. Código de estado: " + statusCode);
                return false;
            }
        } catch (IOException ex) {
            Logger.getLogger(CategoryRestRepository.class.getName()).log(Level.SEVERE, "Error al realizar la solicitud HTTP", ex);
            return false;
        }
    }

    @Override
    public List<Category> list() {
        HttpClient httpClient = HttpClients.createDefault();
        ObjectMapper mapper = new ObjectMapper();
        List<Category> list = new ArrayList<>();
        try {

            // Definir la URL de la API REST de categorias
            String apiUrl = "http://localhost:8001/categories";
            // Crear una solicitud GET para obtener todas las categorias
            HttpGet request = new HttpGet(apiUrl);

            // Ejecutar la solicitud y obtener la respuesta
            HttpResponse response = httpClient.execute(request);

            // Verificar el código de estado de la respuesta
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                // La solicitud fue exitosa, procesar la respuesta
                String jsonResponse = EntityUtils.toString(response.getEntity());

                // Mapear la respuesta JSON a objetos Product
                Category[] categories = mapper.readValue(jsonResponse, Category[].class);

                for (Category category : categories) {
                    list.add(category);
                }

            } else {
                // La solicitud falló, mostrar el código de estado
                Logger.getLogger(CategoryRestRepository.class.getName()).log(Level.SEVERE, null, "Error al obtener categorias. Código de estado: " + statusCode);
            }
        } catch (IOException ex) {
            Logger.getLogger(CategoryRestRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public Category findById(Long categoryId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Category findByName(String categoryName) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    /*@Override
    public Category findCategoryId(String categoryName) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }*/
    
}
