// Author:  Wendy-Beth Minton
// Class:   3810 Database
// Lab:     Introduction to Database Connectivity

// THIS IS THE ONLY CLASS THAT SHOULD IMPORT JDBC! 
import java.sql.*;

// This is an ArcadeGamesDAL. Notice all the database logic is contained within this class.
public class ArcadeGamesDAL {
    // Notice that the databaseName, user and password are passed into this method.
    // We are in the DAL,
    // and we cannot prompt the user for this information. That should be done in
    // the presentation layer
    private Connection getMySQLConnection(String user, String password) {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/" + "ArcadeGames", user, password);
        } catch (SQLException exception) {
            System.out.println("Failed to connect to the database" + exception.getMessage());
            return null;
        }
    }

    public boolean executeStatement(String query, String user, String password) {
        try {
            Connection myConnection = getMySQLConnection(user, password);

            Statement myStatement = myConnection.createStatement();
            ResultSet myRelation = myStatement.executeQuery(query);

            // Iterate through the result set and print the values
            // Get the metadata
            ResultSetMetaData metaData = myRelation.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Iterate through the result set and print the values
            while (myRelation.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    Object value = myRelation.getObject(i);
                    System.out.println(columnName + ": " + value);
                }
            }
        } catch (SQLException exception) {
            System.out.println("Failed Executing a Custom Query:" + exception.getMessage());
            return false;
        }
        return true;
    }

    public boolean executePreparedScoreStatement(int score, String user, String password) {
        try {
            Connection myConnection = getMySQLConnection(user, password);

            PreparedStatement myStatement = myConnection.prepareStatement("SELECT * FROM SCORE WHERE Score > ?");
            myStatement.setInt(1, score);
            ResultSet myRelation = myStatement.executeQuery();

            // Iterate over the ResultSet, row by row
            while (myRelation.next()) {
                int id = myRelation.getInt("Id");
                int gameId = myRelation.getInt("GameId");
                int playerId = myRelation.getInt("PlayerId");
                int playerScore = myRelation.getInt("Score");
                System.out.println("Tuple Values:" + id + "," + gameId + "," + playerId + "," + playerScore);
            }
        } catch (SQLException exception) {
            System.out.println("Failed Executing a Custom Query:" + exception.getMessage());
            return false;
        }
        return true;
    }

    public boolean TryExecutingAStoredProcedure(String user, String password) {
        Connection myConnection = getMySQLConnection(user, password);
        try {
            CallableStatement myStoredProcedureCall = myConnection.prepareCall("{call FirstJavaProcedure()}");
            ResultSet myResults = myStoredProcedureCall.executeQuery();

            // Iterate over the ResultSet, row by row
            while (myResults.next()) {
                int id = myResults.getInt("Id");
                String username = myResults.getString("UserName");
                int favoritegame = myResults.getInt("FavoriteGame");
                System.out.println("Tuple Values:" + id + "," + username + "," + favoritegame);
            }
        } catch (SQLException myException) {
            System.out.println("Failed to execute stored procedure:" + myException.getMessage());
            return false;
        }
        return true;
    }

    /**
     * LAB EXTRA CREDIT - PLEASE ADD FIVE POINTS TO ASSIGNMENT Problem Set Four
     */
    public boolean insertGame(String GameName, String DeveloperName, Date ReleaseDate, String user, String password) {
        Connection myConnection = getMySQLConnection(user, password);
        try {
            // Prepare the stored procedure call
            CallableStatement myStoredProcedureCall = myConnection.prepareCall("{call InsertGameJDBC(?, ?, ?)}");

            // Set the values for the parameters
            myStoredProcedureCall.setString(1, GameName);
            myStoredProcedureCall.setString(2, DeveloperName);
            myStoredProcedureCall.setDate(3, ReleaseDate);
            myStoredProcedureCall.executeQuery();

            System.out.println("Here is the game in the database:");
            PreparedStatement myStatement = myConnection.prepareStatement("SELECT * FROM Game WHERE GameName = ?");
            myStatement.setString(1, GameName);
            ResultSet myRelation = myStatement.executeQuery();
            // Iterate over the ResultSet, row by row
            while (myRelation.next()) {
                int id = myRelation.getInt("Id");
                String gameName = myRelation.getString("GameName");
                String devName = myRelation.getString("DeveloperName");
                Date releaseDate = myRelation.getDate("ReleaseDate");
                System.out.println("Tuple Values:" + id + "," + gameName + "," + devName + "," + releaseDate);
            }
        } catch (SQLException myException) {
            System.out.println("Failed to execute stored procedure:" + myException.getMessage());
            return false;
        }
        return true;
    }
}