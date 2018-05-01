package Group;

import java.sql.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        DataBase dataBase = new DataBase();


        int select = 0;
        do {
            System.out.println("\nМЕНЮ: \n " +
                    "1.Ввід користувача в базу \n " +
                    "2.Переглянути користувачів в базі \n " +
                    "3.ВИХІД"  );
            System.out.print("\n Виберіть пункт меню: ");
            select=scanner.nextInt();

            switch(select){

                case 1: System.out.println("\nВВІД КОРИСТУВАЧА В БАЗУ:");
                    dataBase.InputDB();
                    break;

                case 2: System.out.println("ПЕРЕГЛЯНУТИ КОРИСТУВАЧІВ В БАЗІ:");
                    dataBase.OutputDB();
                    break;

                default: System.out.println("EXIT");
                    break;
            }
        } while (select!=3);






    }

}
