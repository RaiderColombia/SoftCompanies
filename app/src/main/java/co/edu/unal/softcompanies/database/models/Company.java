package co.edu.unal.softcompanies.database.models;

/**
 * SoftCompanies
 * Created by Jhon Ramirez on 11/2/17.
 * Universidad Nacional de Colombia
 */
public class Company {

    private long id;
    private String name;
    private String url;
    private String phone;
    private String email;
    private String products;
    private int classification;

    public enum COMPANY_KEYS {
        ID,
        NAME,
        URL,
        PHONE,
        EMAIL,
        PRODUCTS,
        CLASSIFICATION
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProducts() {
        return products;
    }

    public void setProducts(String products) {
        this.products = products;
    }

    public int getClassification() {
        return classification;
    }

    public void setClassification(int classification) {
        this.classification = classification;
    }

    @Override
    public String toString() {
        return name;
    }
}
