public class Account {
    private int id;
    private String name;
    private String password;
    private Information idInfor;

    public Account() {
    }

    public Account(int id, String name, String password, Information idInfor) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.idInfor = idInfor;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Information getIdInfor() {
        return idInfor;
    }

    public void setIdInfor(Information idInfor) {
        this.idInfor = idInfor;
    }
}
