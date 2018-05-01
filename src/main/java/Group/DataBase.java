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
    PreparedStatement preparedStatement;

    Scanner scanner = new Scanner(System.in);
    Users users = new Users();

    public DataBase() {

        try {
            connection = DriverManager.getConnection(HOST, USERNAME, PASSWORD);
            statement = connection.createStatement();

        }catch(SQLException e) {
            e.printStackTrace();
        }

    }



    /*Реєстрація Юзера (Admin)*/

    public void inputUserAdminDB() {

        do {
            System.out.print("Id: ");
            users.setId(scanner.nextInt());

            System.out.print("Username: ");
            users.setUsername(scanner.next());

            System.out.print("Password: ");
            users.setPassword(scanner.next());

            System.out.print("Age: ");
            users.setAge(scanner.nextInt());


            System.out.println("\nНатисніть 's' щоб зберегти...");
        } while (!scanner.next().trim().equals("s"));

        String inputQuery = "INSERT INTO Users (" +
                "id, username, password, age) VALUES (?, ?, ?, ?)";

        try {
            preparedStatement = connection.prepareStatement(inputQuery);

            preparedStatement.setInt(1, users.getId());
            preparedStatement.setString(2, users.getUsername());
            preparedStatement.setString(3, users.getPassword());
            preparedStatement.setInt(4, users.getAge());

            preparedStatement.execute();

            outputCustomUsersIdAdminDB(users.getId());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*Реєстрація Юзера (User)*/

    public void registerUserDB() {

        do {
            System.out.print("Username: ");
            users.setUsername(scanner.next());

            System.out.print("Password: ");
            users.setPassword(scanner.next());

            System.out.print("Age: ");
            users.setAge(scanner.nextInt());


            System.out.println("\nНатисніть 's' щоб зберегти...");
        } while (!scanner.next().trim().equals("s"));

        String inputQuery = "INSERT INTO Users (username, password, age) VALUES (?, ?, ?)";
        String outputQuery = "SELECT id FROM Users WHERE username = ? AND password = ?";

        try {
            preparedStatement = connection.prepareStatement(inputQuery);

            preparedStatement.setString(1, users.getUsername());
            preparedStatement.setString(2, users.getPassword());
            preparedStatement.setInt(3, users.getAge());

            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            preparedStatement = connection.prepareStatement(outputQuery);

            preparedStatement.setString(1, users.getUsername());
            preparedStatement.setString(2, users.getPassword());

            preparedStatement.execute();

            resultSet = preparedStatement.executeQuery();
            resultSet.next();

            users.setId(resultSet.getInt("id"));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.print("Вітаємо з реєстрацією!! \nВаш користувач: ");
        outputCustomUsersIdAdminDB(users.getId());
    }

    /*Виведення усіх Юзерів (Admin)*/

    public void outputAllUsersAdminDB() {

        String outputQuery = "SELECT * FROM Users";

        try {
            resultSet = statement.executeQuery(outputQuery);

            while (resultSet.next()){

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

    /*Виведення Юзера по його ID (Admin)*/

    public void outputCustomUsersIdAdminDB(int id) {

        String getUser = "SELECT * FROM Users WHERE id = ?";

        try {
            preparedStatement = connection.prepareStatement(getUser);
            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();
            resultSet.next();

            users.setId(resultSet.getInt("id"));
            users.setUsername(resultSet.getString("username"));
            users.setPassword(resultSet.getString("password"));
            users.setAge(resultSet.getInt("age"));

            System.out.println(users);

        }catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /*Видалення Юзера по його ID (Admin)*/

    public void deleteCustomUsersIdAdminDB(int id) {

        String deleteQuery = "DELETE FROM Users WHERE id = ?";
        String getUser = "SELECT * FROM Users WHERE id = ?";

            try {
                preparedStatement = connection.prepareStatement(getUser);
                preparedStatement.setInt(1, id);

                resultSet = preparedStatement.executeQuery();

                resultSet.next();

                users.setId(resultSet.getInt("id"));
                users.setUsername(resultSet.getString("username"));
                users.setPassword(resultSet.getString("password"));
                users.setAge(resultSet.getInt("age"));

            }catch (SQLException e) {
                e.printStackTrace();
            }

                int select = 0;

                do {
                    System.out.println("\nБажаєте видалити користувача: " + users);
                    System.out.println("\nНатисніть 1 щоб підтвердити або будь яку іншу цифру щоб відмінити: ");
                    select=scanner.nextInt();

                    switch(select){

                        case 1:
                            try {
                                preparedStatement = connection.prepareStatement(deleteQuery);
                                preparedStatement.setInt(1, id);
                                preparedStatement.executeUpdate();
                            }catch (SQLException e) {
                                e.printStackTrace();
                            }
                            System.out.println("КОРИСТУВАЧА: " + users + " ВИДАЛЕНО З БАЗИ");
                            select = 4;
                            break;

                            default: System.out.println("Відмінено");
                            select = 4;
                                break;
                    }
                } while (select!=4);

    }

    public Connection getConnection() {
        return connection;
    }

    public Statement getStatement() {
        return statement;
    }

    public ResultSet getResultSet() {
        return resultSet;
    }

    public void setResultSet(ResultSet resultSet) {
        this.resultSet = resultSet;
    }

    public PreparedStatement getPreparedStatement() {
        return preparedStatement;
    }
}
