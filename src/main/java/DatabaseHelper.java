

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class DatabaseHelper {
    private final String URL="jdbc:mysql://localhost:3306/game?serverTimezone=UTC";
    private final String USERNAME="root";
    private final String PASSWORD="81468146";
    private Statement statement;

    public DatabaseHelper() {
        Connection connection;
        try {
            Driver driver;
            connection=DriverManager.getConnection(URL,USERNAME,PASSWORD);
            statement=connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Can not to connect driver");
        }
    }
    public void insertPlayer(String name){
        try {
            statement.execute("INSERT INTO players(Name) values ('"+name+"');");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error in insert player");
        }
    }
    public void updatePlayer(int id,int kills){
        try {
            statement.executeUpdate("update players set Record='"+kills+"'where id='"+id+"';");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error in update record");
        }
    }
    public int getRecord(int id){
        try {
            ResultSet resultSet=statement.executeQuery("select * from players where id='"+id+"';");
            resultSet.next();
            return resultSet.getInt(4);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public void updateAmountGames(int id){
        try {
            ResultSet resultSet=statement.executeQuery("select * from players where id='"+id+"';");
            resultSet.next();
            int games=resultSet.getInt(3);
            games++;
            statement.executeUpdate("update players set Amount_games='"+games+"'where id='"+id+"';");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error in update amount game");
        }
    }
    public Map<Integer,String> getArrayOfNamesPlayers(){
        ResultSet resultSet= null;
        Map<Integer,String> map=new HashMap<Integer,String>();
        try {
            resultSet = statement.executeQuery("select * from players;");
            while(resultSet.next()){
                map.put(resultSet.getInt(1),resultSet.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }
}
