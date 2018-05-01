package Group;

import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.sql.*;
import java.util.Scanner;

public class DataBase {

    private static final String HOST = "jdbc:mysql://sql7.freemysqlhosting.net:3306/sql7235535";
    private static final String USERNAME = "sql7235535";
    private static final String PASSWORD = "rootroot123";

    Connection connection;
    Statement statement;
    ResultSet resultSet;

    public DataBase() {

        try {
            connection = DriverManager.getConnection(HOST, USERNAME, PASSWORD);
            statement = connection.createStatement();

        }catch(SQLException e) {
            e.printStackTrace();
        }

    }

    public void InputDB() {

        int id;
        String username;
        String password;
        int age;

        Scanner scanner = new Scanner(System.in);

        do {
            System.out.print("Id: ");
            id = scanner.nextInt();

            System.out.print("Username: ");
            username = scanner.next();

            System.out.print("Password: ");
            password = scanner.next();

            System.out.print("Age: ");
            age = scanner.nextInt();


            System.out.println("\nНатисніть 's' щоб зберегти...");
        } while (!scanner.next().trim().equals("s"));

        String sql = "INSERT INTO Users (" +
                "id, username, password, age) VALUES (?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, password);
            preparedStatement.setInt(4, age);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void OutputDB() {

        String query = "select * from Users";

        try{
            resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                Users users = new Users();
                users.setId(resultSet.getInt("id"));
                users.setUsername(resultSet.getString("username"));
                users.setPassword(resultSet.getString("password"));
                users.setAge(resultSet.getInt("age"));

                System.out.println(users);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }



    public Connection getConnection() {
        return connection;
    }

    public Statement getStatement() {
        return statement;
    }
}
