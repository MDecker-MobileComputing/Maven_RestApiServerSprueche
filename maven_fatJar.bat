
@REM Fat-Jar erstellen, also EINE Jar-Datei, die neben dem eigentlichen Programm
@REM (hier: REST-API-Server) auch alle benötigten Abhängigkeiten (Bibliotheken)
@REM enthält. Die Fat-Jar-Datei kann z.B. auf einer Server kopiert werden, und dort
@REM mit folgendem Befehl ausgeführt werden: 
@REM
@REM    java -jar rest-api-server-sprueche-1.0-SNAPSHOT-jar-with-dependencies.jar
@REM 
@REM Die Datei wird im Ordner "target/" abgelegt.

mvn clean package
