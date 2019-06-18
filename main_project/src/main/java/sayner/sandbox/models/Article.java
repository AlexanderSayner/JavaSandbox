package sayner.sandbox.models;

import javax.persistence.*;

@Entity
@Table(name = "Article")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column
    private String name;

    @Column
    private String manufacturer;

    public Article(){}

    public Article(String name, String manufacturer) {
        this.name = name;
        this.manufacturer = manufacturer;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
}