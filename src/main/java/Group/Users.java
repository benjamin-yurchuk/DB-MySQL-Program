package Group;

public class Users {

    private int id;
    private String username;
    private String password;
    private int age;

    public Users(){

    }

    public Users(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Users(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return  username + "{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", age=" + age +
                '}';
    }
}
