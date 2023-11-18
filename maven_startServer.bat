
@REM Vor dem eigentlichen Start wird nochmal "compile" aufgerufen,
@REM damit der Server den aktuellen Stand hat

mvn compile exec:java -Dexec.mainClass=de.eldecker.dhbw.App
