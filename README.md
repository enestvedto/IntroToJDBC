# JDBC Lab Submission

## Description

This is my submission for the JDBC lab. Interact with my REPL loop to explore all of the options!

## Stored Procedure for option 5

```sql
USE ArcadeGames;
delimiter {{
CREATE PROCEDURE FirstJavaProcedure()
BEGIN
    SELECT * FROM Player;
END; {{
delimiter ;
```

## Stored Procedure for option 6

```sql
USE ArcadeGames;
delimiter {{
CREATE PROCEDURE InsertGameJDBC(nameOfGame VARCHAR(200), nameOfDeveloper VARCHAR(200), dateOfRelease DATE)
BEGIN
	DECLARE valueExists INT;

    -- Check if the game name exists in the column
    SELECT COUNT(*)
    INTO valueExists
    FROM Game
    WHERE GameName = nameOfGame;

    IF valueExists = 0 THEN
        -- Insert new game
        INSERT INTO Game (GameName, DeveloperName, ReleaseDate) VALUES (nameOfGame, nameOfDeveloper, dateOfRelease);
    END IF;
END; {{
    delimiter ;
```
