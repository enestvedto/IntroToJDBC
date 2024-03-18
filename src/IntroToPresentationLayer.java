// Author:  Wendy-Beth Minton
// Class:   3810 Database
// Lab:     Introduction to Database Connectivity

// This is an example of a presentation layer. Notice it includes all the needed prompts needed.
// This class also happens to have the main method. It is common to have the entry to a program 
// exist in the presentation layer, and actually each application tends to have its own entry point,
// if the program/product has multiple applications. 

import java.util.Scanner;

public class IntroToPresentationLayer {
    public static void main(String[] args) {
        Scanner userInformation = new Scanner(System.in);
        System.out.println("Enter username and password:");
        // String input
        String userName = userInformation.nextLine();
        String password = userInformation.nextLine();

        // Let's start simple. How do we connect to and access a database?
        // Well, the presentation layer can't do it. We need an instance of the DAL!
        IntroToDAL dal = new IntroToDAL();

        // My new code
        ArcadeGamesDAL arcadeDAL = new ArcadeGamesDAL();

        // REPL LOOP
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println();
            System.out.println("Enter a number between 1 and 5 for the following options(or 0 to exit):");
            System.out.println("1. Running a single query against the meal planning database");
            System.out.println("2. Running GetRecipes stores procedure against the meal planning database");
            System.out.println("3. Running ArcadeGames custom statement");
            System.out.println("4. Running ArcadeGames prepared statement");
            System.out.println("5. Running ArcadeGames stored procedure");
            System.out.print("> ");

            // Read user input
            if (scanner.hasNextInt()) {
                int userInput = scanner.nextInt();

                // Check if input is in the valid range
                if (userInput >= 1 && userInput <= 5) {
                    // Execute code based on user input
                    switch (userInput) {
                        case 1:
                            System.out.println("Executing code for option 1...");
                            // Now we can use the dal object, so let's print
                            // out some rows from the Meal table, in the MealPlanningDatabase.
                            // We need to pass the dal method everything it needs to run a query, including
                            // the database name, the query, and the user's sql credentials.
                            if (dal.TryExecutingAQuery("MealPlanning", "Select * from Meal", userName, password)) {
                                System.out.println("Successfully connected to the database");
                            } else {
                                System.out.println("Failed to connect to the database");
                            }
                            break;
                        case 2:
                            System.out.println("Executing code for option 2...");
                            // Let's try calling a stored procedure, and let's start simple.
                            // I made a new stored procedure that just returns everything in the
                            // Recipe table, called GetRecipes. No parameters, just a simple call.
                            if (dal.TryExecutingAStoredProcedure("MealPlanning", userName, password)) {
                                System.out.println("Successfully ran a stored procedure");
                            } else {
                                System.out.println("Failed to run a stored procedure");
                            }
                            break;
                        case 3:
                            System.out.println("Executing code for option 3...");
                            System.out.println("Input a query:");
                            String userQuery = userInformation.nextLine();
                            if (arcadeDAL.executeStatement(userQuery, userName, password)) {
                                System.out.println("Successfully connected to the database");
                            } else {
                                System.out.println("Failed to connect to the database");
                            }
                            break;
                        case 4:
                            System.out.println("Executing code for option 4...");
                            System.out.println("Input a minimum score:");
                            int userScore = userInformation.nextInt();
                            if (arcadeDAL.executePreparedScoreStatement(userScore, userName, password)) {
                                System.out.println("Successfully connected to the database");
                            } else {
                                System.out.println("Failed to connect to the database");
                            }
                            break;
                        case 5:
                            System.out.println("Executing code for option 5...");
                            System.out.println("Stored Procedure...\n");
                            if (arcadeDAL.TryExecutingAStoredProcedure(userName, password)) {
                                System.out.println("Successfully connected to the database");
                            } else {
                                System.out.println("Failed to connect to the database");
                            }
                            break;
                    }
                } else if (userInput == 0) {
                    // Exit loop if user inputs 0
                    System.out.println("Exiting...");
                    break;
                } else {
                    System.out.println("Invalid input. Please enter a number between 1 and 5.");
                }
            } else {
                // Handle non-integer input
                System.out.println("Invalid input. Please enter a number between 1 and 5.");
                scanner.next(); // Consume the invalid input
            }
        }
    }
}