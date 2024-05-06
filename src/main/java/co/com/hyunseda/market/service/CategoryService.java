/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.com.hyunseda.market.service;

import co.com.hyunseda.market.access.ICategoryRepository;
import co.com.hyunseda.market.domain.Category;
import co.com.hyunseda.market.domain.Product;
import co.com.hyunseda.market.access.ICategoryRepository;
import co.com.hyunseda.market.access.IProductRepository;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JUAN DAVID
 */
public class CategoryService {
    //private Connection conn;
    ICategoryRepository categoryR;
    private List<Category> categories;
    //private final IDataBaseInitializer databaseInitializer;
    
    public CategoryService(ICategoryRepository categoryR) {
        //this.conn = conn;
        this.categoryR = categoryR;
        categories = new ArrayList<>();
        //findAllCategories();
    }
    
    /*public Category findCategoryId(String name){
        
        return categoryR.findCategoryId(name);
    }*/
    
    public boolean saveCategory(Category category) {
      categories.add(category);
      return categoryR.save(category);
    }
    
    public List<Category> findAllCategories() {
        /*categories = categoryR.list();*/
        return /*categories*/categoryR.list();
    }
    
    public Category findCategoryById(Long id){
        return categoryR.findById(id);
    }
    
    /*public List<Category> findCategoryByName(String name) {
        categories = (List<Category>) categoryR.findByName(name);
        return categories;
    }*/
    
    public Category findCategoryByName(String name) {
        return categoryR.findByName(name);
    }
}
