# Projekt: SoccerTab

Name & Praktikumstermin: David Waldmann, <Matrikelnummer> (Fr/1, Hb)

<Inhaltsverzeichnis>

## Kurzbeschreibung

> Die Anwendung "SoccerTab" stellt eine Fußball-Tabelle dar, die sich automatisch mit neuen Ergebnissen aktualisiert. Da dies schwer live zu demonstrieren ist, wird ein Live-Match simuliert und dann per Websocket die Tabelle während des laufenden Spiels aktualisiert.


![Screenshot](src/main/resources/public/screenshot2.png)

## Beschreibung des Projektaufbaus

Das Projekt besteht aus dem funktionalen Anteil mit den Dateien Country.java, Match.java, Table.java, der App.java, die den Javalin-Server aufsetzt, und die index.html, in der die GUI erstellt wird, und die auch den clientseitigen Javascript-Teil enthält.

### Abgabedateien (LOC)

Verlinkter Dateiname | Dateiart | LOC
---------------------|----------|-----
[App.java](\src\main\java\App.java) | Java | 25
[T3.java](https://git.thm.de/dhzb87/JbX/blob/master/TicTacToe.Spielfeld/src/main/java/tictactoe/T3.java) | Java | 54
[index.html](https://git.thm.de/dhzb87/JbX/blob/master/TicTacToe.Spielfeld/src/main/resources/public/index.html) | HTML | 77

### Testdateien (TST)

Verlinkter Dateiname | Testart | Anzahl der Tests
---------------------|---------|-----------------
[Test.java](\src\test\java\FootballTest.java) | JUnit5 | 35

Die Tests werden wie folgt ausgeführt:

<Beschreiben Sie, wie die Tests auszuführen sind.>

### Aufbau der Anwendung

<Ihre Beschreibung zum Projektaufbau>

## Dokumentation des implementierten WebAPIs

<Ihre Dokumentation dazu>
Das WebAPI ist durch die Request/Replies und den damit stattfindenden Datenaustausch zu dokumentieren.

## Dokumentation des Interfaces

<Ihre Dokumentation dazu>

## Technischer Anspruch (TA) und Umsetzung der Features

Ich habe folgende Features verwendet. Die verlinkte Datei zeigt beispielhaft den Einsatz dieses Features in den angegebenen Zeilen im Quellcode.

1. Speicherung/Abruf von Daten im lokalen Dateisystem, [App.java](/src/main/java/tictactoe/App.java) (104-112)
2. WebSockets, [App.java](/src/main/java/tictactoe/App.java) (204-240)
3. Validation, [App.java](/src/main/java/tictactoe/App.java) (300-303)
4. Streams, [App.java](/src/main/java/tictactoe/App.java) (130-135)

<Ihre Dokumentation zu den Features>


**Hinweise**: keine