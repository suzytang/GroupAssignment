package com.example.groupassignment;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Database {


    public static ResultSet selectQuery(String query){
        try{
            Connection conn = DriverManager.getConnection("jdbc:sqlite:infs3634.db");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            return rs;
        }
        catch(Exception e){

            System.err.println(e);
            System.out.println("Error in selectQuery");
        }
        return null;
    }

    public static void createPetTable(){
        try{
            Connection conn = DriverManager.getConnection("jdbc:sqlite:infs3634.db");
            java.sql.Statement st = conn.createStatement();
            //st.execute("DROP TABLE IF EXISTS Pet");
            String createQuery = "CREATE TABLE IF NOT EXISTS Pet"
                    + "(pet_id INTEGER PRIMARY KEY AUTOINCREMENT"
                    + ", petName TEXT NOT NULL"
                    + ", coins INTEGER"
                    + ", food INTEGER"
                    + ", experience INTEGER"
                    + ", mood TEXT NOT NULL"
                    + ", hunger INTEGER"
                    +");";
            st.execute(createQuery);

            String insertData = "INSERT INTO Pet (pet_id, petName, coins, food, experience, mood, hunger)"
                    + "VALUES (1, 'Helpme', 100, 0, 0, 'good', 1)";
            st.execute(insertData);
            System.out.println("Pet table created");

            st.close();
        }catch(Exception e){
            System.err.println(e);
            System.out.println("Error creating Pet table");
        }

    }

    public static void createAccessoriesTable(){
        try{
            Connection conn = DriverManager.getConnection("jdbc:sqlite:infs3634.db");
            java.sql.Statement st = conn.createStatement();
            st.execute("DROP TABLE IF EXISTS Accessories");
            String createQuery = "CREATE TABLE IF NOT EXISTS Accessories"
                    + "(accessories_id INTEGER PRIMARY KEY AUTOINCREMENT"
                    + ", name TEXT NOT NULL"
                    + ")";
            st.execute(createQuery);

            String insertData = "INSERT INTO Accessories (accessories_id, name)"
                    + "VALUES (1, 'Glasses')";
            st.execute(insertData);

            st.close();
            conn.close();
        }catch(Exception e){
            System.err.println(e);
            System.out.println("Error creating Accessories Inventory table");
        }

    }
    public static void createWallpapersTable(){
        try{
            Connection conn = DriverManager.getConnection("jdbc:sqlite:infs3634.db");
            java.sql.Statement st = conn.createStatement();
            st.execute("DROP TABLE IF EXISTS Wallpapers");
            String createQuery = "CREATE TABLE IF NOT EXISTS Wallpapers"
                    + "(wallpapers_id INTEGER PRIMARY KEY AUTOINCREMENT"
                    + ", name TEXT NOT NULL"
                    + ")";
            st.execute(createQuery);

            String insertData = "INSERT INTO Wallpapers (wallpapers_id, name)"
                    + "VALUES (1, 'Pink')";
            st.execute(insertData);

            st.close();
            conn.close();
        }catch(Exception e){
            System.err.println(e);
            System.out.println("Error creating Wallpapers Inventory table");
        }

    }


}
