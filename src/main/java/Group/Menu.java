package Group;

import java.sql.SQLException;
import java.util.Scanner;

public class Menu {

    Scanner scanner = new Scanner(System.in);
    DataBase dataBase = new DataBase();
    Users users = new Users();

    /*Меню авторизації*/

    public void menuAutorization() {

        int select = 0;
        do {
            System.out.println("\nМЕНЮ: \n " +
                    "1.Вхід \n " +
                    "2.Реєстрація \n " +
                    "3.ВИХІД" );
            System.out.print("\n Виберіть пункт меню: ");
            select = scanner.nextInt();

            switch (select) {

                case 1:

                    String login;
                    String password;

                    int okUser = 0;
                    int good = 0;
                    int adminNum =1;

                    String outputQuery = "SELECT * FROM Users";

                    try {
                        dataBase.setResultSet(dataBase.getStatement().executeQuery(outputQuery));

                        System.out.println("\nВХІД:");
                        System.out.print("Введіть логін:");
                        login = scanner.next();
                        System.out.print("Введіть пароль:");
                        password = scanner.next();

                        while (good == 0) {

                            while (dataBase.getResultSet().next()) {

                                users.setId(dataBase.getResultSet().getInt("id"));
                                users.setUsername(dataBase.getResultSet().getString("username"));
                                users.setPassword(dataBase.getResultSet().getString("password"));
                                users.setAge(dataBase.getResultSet().getInt("age"));


                                if (login.equals(users.getUsername()) && password.equals(users.getPassword()) && adminNum != users.getId()) {
                                    menuUser();
                                    okUser++;
                                    good++;
                                    break;
                                }if (login.equals(users.getUsername()) && password.equals(users.getPassword()) && adminNum == users.getId()) {
                                    menuAdmin();
                                    okUser++;
                                    good++;
                                    break;
                                }
                            }

                            if (okUser == 0) {
                                System.out.println("\nТакий логін чи пароль - відсутній");
                                good++;
                            }
                        }

                    }catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;

                case 2:
                    System.out.println("\nРЕЄСТРАЦІЯ:");
                    dataBase.registerUserDB();
                    break;

                case 3:
                    select = 3;
                    break;

                default:
                    System.out.println("Не коректне введення!!! Спробуйте знову.");
                    break;
            }
        } while (select != 3);
    }

    /*Меню адміна*/

    public void menuAdmin() {

        int select = 0;
        do {
            System.out.println("\nМЕНЮ АДМІНА: \n " +
                    "1.Ввід користувача в базу \n " +
                    "2.Переглянути всіх користувачів в базі \n " +
                    "3.Переглянути користувача в базі по ID \n " +
                    "4.Видалення користувача з бази \n " +
                    "5.ВИХІД З ОБЛІКОВОГО ЗАПИСУ");
            System.out.print("\n Виберіть пункт меню: ");
            select = scanner.nextInt();

            switch (select) {

                case 1:
                    System.out.println("\nВВІД КОРИСТУВАЧА В БАЗУ:");
                    dataBase.inputUserAdminDB();
                    break;

                case 2:
                    System.out.println("\nПЕРЕГЛЯНУТИ КОРИСТУВАЧІВ В БАЗІ:");
                    dataBase.outputAllUsersAdminDB();
                    break;

                case 3:
                    System.out.println("\nПЕРЕГЛЯНУТИ КОРИСТУВАЧА В БАЗІ ПО ID:");
                    System.out.print("Введіть Id користувача, якого бажаєте переглянути: ");
                    dataBase.outputCustomUsersIdAdminDB(scanner.nextInt());
                    break;

                case 4:
                    System.out.println("\nВИДАЛЕННЯ КОРИСТУВАЧА З БАЗИ ПО ID:");
                    System.out.println("Введіть Id користувача, якого бажаєте видалити: ");
                    dataBase.deleteCustomUsersIdAdminDB(scanner.nextInt());
                    break;

                case 5:
                    select = 5;
                    break;

                default:
                    System.out.println("Не коректне введення!!! Спробуйте знову.");
                    break;
            }
        } while (select != 5);
    }

    /*Меню Юзера*/

    public void menuUser() {

        int select = 0;
        do {
            System.out.println("\nМЕНЮ КОРИСТУВАЧА: \n " +
                    "1.Переглянути всіх користувачів в базі \n " +
                    "2.Переглянути користувача в базі по ID \n " +
                    "3.ВИХІД З ОБЛІКОВОГО ЗАПИСУ");
            System.out.print("\n Виберіть пункт меню: ");
            select = scanner.nextInt();

            switch (select) {

                case 1:
                    System.out.println("\nПЕРЕГЛЯНУТИ КОРИСТУВАЧІВ В БАЗІ:");
                        dataBase.outputAllUsersAdminDB();
                    break;

                case 2:
                    System.out.println("\nПЕРЕГЛЯНУТИ КОРИСТУВАЧА В БАЗІ ПО ID:");
                        dataBase.outputCustomUsersIdAdminDB(scanner.nextInt());
                    break;

                case 3:
                    select = 3;
                    break;

                default:
                    System.out.println("Не коректне введення!!! Спробуйте знову.");
                    break;
            }
        } while (select != 3);
    }



}
