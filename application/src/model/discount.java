package model;

public class discount {
    private int id;
    private String name;
    private float sale;

    public discount() {
    }

    public discount(int id, String name, float sale) {
        this.id = id;
        this.name = name;
        this.sale = sale;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getSale() {
        return sale;
    }

    public void setSale(float sale) {
        this.sale = sale;
    }
}
