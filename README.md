# JDBC Lab Submission

## Description
This is my submission for the JDBC lab. Interact with my REPL loop to explore all of the options!

## Stored Procedure
```sql
USE ArcadeGames;
delimiter {{
CREATE PROCEDURE FirstJavaProcedure()
BEGIN
    SELECT * FROM Player;
END; {{
delimiter ;
```
