This is my submission for the JDBC lab. Interact with my REPL loop to explore all of the options!

This is my stored procedure:
USE ArcadeGames;
delimiter {{
CREATE PROCEDURE FirstJavaProcedure()
BEGIN
    SELECT * FROM Player;
END; {{
delimiter ;
