package model;

public class table_food {
    private int id;
    private int status;
    private int x;
    private int y;

    public table_food() {
    }

    public table_food(int id, int status, int x, int y) {
        this.id = id;
        this.status = status;
        this.x = x;
        this.y = y;
    }
    public table_food(table_food table_food){
        this.id = id;
        this.status = status;
        this.x = x;
        this.y = y;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
