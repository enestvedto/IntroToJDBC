This is my submission for the JDBC lab. Interact with my REPL loop to explore all of the options!

This is my stored procedure:\n
USE ArcadeGames;\n
delimiter {{\n
CREATE PROCEDURE FirstJavaProcedure()\n
BEGIN\n
    SELECT * FROM Player;\n
END; {{\n
delimiter ;
