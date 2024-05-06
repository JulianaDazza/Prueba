package co.com.hyunseda.market.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
/**
 *
 * @author Libardo Pantoja, Julio A. Hurtado
 */
public class Category {
    @JsonProperty("categoryId")
    private int categoryId;
    @JsonProperty("categoryName")
    private String categoryName;

    public Category(int categoryId, String name) {
        this.categoryId = categoryId;
        this.categoryName = name;
    }

    public Category(String name) {
        this.categoryName=name;
    }
    public Category(){
        
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String name) {
        this.categoryName = name;
    }
}

