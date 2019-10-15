package model;

public class food {
    private int id;
    private String name;
    private int category;
    private boolean status;

    public food() {
    }

    public food(int id, String name, int category, boolean status) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.status = status;
    }
    public food(food food){
        this.id = id;
        this.name = name;
        this.category = category;
        this.status = status;
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

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
