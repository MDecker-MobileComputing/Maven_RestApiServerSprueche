# REST-API-Server für Sprüche #

<br>

Dieses Repository enthält eine einfache Java-Applikation, die über den integrierten Webserver [Jetty](https://eclipse.dev/jetty/)
eine einfache REST-API bereitstellt. Dies dient zur Demonstration der Verwendung des Build-Management-Werkzeugs [Maven](https://maven.apache.org/).

<br>

----

## REST-API ##

<br>

Von der von Java-Applikation bereitgestellten REST-API können Sprüche/Zitate zu verschiedenen Kategorien (Themenbereichen) abgefragt werden.

### REST-Endpunkt `/kategorien`

Eine Übersicht aller Kategorien inkl. Anzahl der Sprüche bekommen wir durch den Aufruf des Endpunktes `/kategorien`.
Der REST-API-Server wird standardmäßig auf Port `8080` gestartet, also lautet die volle URL: http://localhost:8080/kategorien

Es wird ein Array von Objekten zurückgegeben.
Jedes dieser Objekte repräsentiert eine Kategorie mit dem technischen Namen (ID) der Kategorie, einer Kurzbeschreibung und
der Anzahl der Sprüche.
```
[
  {
    "techName" : "INFO",
    "beschreibung" : "Informatik",
    "anzahlSprueche" : 8
  },
  {
    "techName" : "WISS",
    "beschreibung" : "Wissenschaft",
    "anzahlSprueche" : 5
  },
  {
    "techName" : "WIWI",
    "beschreibung" : "Wirtschaftswissenschaften",
    "anzahlSprueche" : 0
  }
]
```

### REST-Endpunkt `/sprueche`

Für eine bestimmte Kategorie, die über von `/kategorien` zurückgelieferten `techName` spezifiziert wird, kann durch Aufruf des folgenden
Endpunkts ein Spruch zurückgeliefert werden:
```
/sprueche?kategorie=<techName>&nummer=<nr>
```
Es sind also die beiden URL-Parameter `kategorie` und `nummer` zu spezifizieren.
Der Wert für den Parameter `nummer` kann hierbei auch der String "zufall" sein.

Beispiel-URLs:

* http://localhost:8080/sprueche?kategorie=INFO&nummer=3
* http://localhost:8080/sprueche?kategorie=WISS&nummer=1
* http://localhost:8080/sprueche?kategorie=WISS&nummer=zufall

<br>

Ein erfolgreicher Aufruf dieses Endpunkts liefert ein JSON-Objekt zurück, das
zwei Key-Value-Paare enthält:

* Der Key `erfolg` referenziert einen bool'schen Wert.
  Dieser Wert ist genau dann `true`, wenn ein Spruch gefunden wurde.
* Der Key `text` referenziert den gefunden Spruch (`erfolg=true`) oder
  die Fehlermeldung (`erfolg=false`).


Beispiel für einen erfolgreichen Aufruf:
```
{
  "text" : "Ein Mathematiker ist eine Maschine, die Kaffee in Theoreme umwandelt.",
  "erfolg" : true
}
```

<br>

Beispiel für einen erfolglosen Aufruf:
```
{
  "text" : "Keine oder keine gültige Kategorie spezifiziert",
  "erfolg" : false
}
```

<br>

----

## License ##

<br>

See the [LICENSE file](LICENSE.md) for license rights and limitations (BSD 3-Clause License).

<br>