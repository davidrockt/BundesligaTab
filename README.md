# Projekt: SoccerTab

Name & Praktikumstermin: David Waldmann, <Matrikelnummer> (Die/1, Hb)

<Inhaltsverzeichnis>

## Kurzbeschreibung

> Die Anwendung "SoccerTab" stellt eine Fußball-Tabelle dar,
  die sich automatisch mit neuen Ergebnissen aktualisiert. Da dies schwer mit LIVE-
  Spielen zu demonstrieren ist, wird ein Live-Match simuliert und die Tabelle dann
  während des laufenden Spiels aktualisiert.
  Es gibt außerdem noch die Möglichkeit fertige Spielstände einzutragen. Es lassen
  sich mehrere Fenster öffnen, und ein in einem Fenster gestartetes Live-Spiel wird
  über Websockets in allen Fenstern angezeigt, und die Tabelle entsprechend
  aktualisiert.


![Screenshot](src/main/resources/public/screenshot.png)


## Beschreibung des Projektaufbaus

### Abgabedateien (LOC)

Verlinkter Dateiname | Dateiart | LOC
---------------------|----------|-----
[App.java](\src\main\java\App.java) | Java | 70
[ICountry.java](\src\main\java\ICountry.java) | Java | 10
[Country.java](\src\main\java\Country.java) | Java | 150
[ITable.java](\src\main\java\ITable.java) | Java | 9
[Table.java](\src\main\java\Table.java) | Java | 59
[Match.java](\src\main\java\Match.java) | Java | 38
[SimulatedLiveMatch.java](\src\main\java\SimulatedLiveMatch.java) | Java | 72
[Goals.java](\src\main\java\Goals.java) | Java | 40
[WinLooseTie.java](\src\main\java\WinLooseTie.java) | Java | 40
[index.html](\src\main\resources\public\index.html) | HTML | 130
[javascript.js](\src\main\resources\public\javascript.js) | Javascript | 37
[Screenshot.png](\src\main\resources\public\screenshot.png) | Bild

### Testdateien (TST)

Verlinkter Dateiname | Testart | Anzahl der Tests
---------------------|---------|-----------------
[FootballTest.java](\src\test\java\FootballTest.java) | JUnit5 | 60

Die Tests werden wie folgt ausgeführt:

Ich hoffe, das kann ich bis zur Abgabe noch herausfinden. Ich führe die Tests
in IntelliJ per "Knopfdruck" aus.

### Aufbau der Anwendung

Datei | Aufgabe 
---------------------|----------
[App.java](\src\main\java\App.java) | Hier wird der **Javalin-Server** aufgesetzt, und die **Websockets** eingerichtet
[ICountry.java](\src\main\java\ICountry.java) | Interface für Country (s.u.)
[Country.java](\src\main\java\Country.java) | Klasse für eine **Mannschaft**, mit Statistiken wie Punkten, Toren, Gegentoren usw
[ITable.java](\src\main\java\ITable.java) | Interface für Table (s.u.)
[Table.java](\src\main\java\Table.java) | Klasse für die **Tabelle**, der über den Konstruktor neue Länder hinzugefügt 
<d>| werden können, und die die enthaltenen Länder sortieren kann, und neue Matches hinzufügen kann
[Match.java](\src\main\java\Match.java) | Klasse für ein **Match**, das die beteiligten Mannschaften und das End-Ergebnis enthält
[SimulatedLiveMatch.java](\src\main\java\SimulatedLiveMatch.java) | Klasse für ein simuliertes **Live-Spiel**, das Daten enthält, damit der Tabelle Zwischenstände übergeben werden können, ohne dass Tore oder Punkte doppelt verbucht werden.
[Goals.java](\src\main\java\Goals.java) | Klasse für die **Tor-Statistik** einer Mannschaft, mit Gegentoren und Tordifferenz
[WinLooseTie.java](\src\main\java\WinLooseTie.java) | Klasse für die **Sieg-Niederlage**-Unentschieden-Bilanz einer Mannschaft
[index.html](\src\main\resources\public\index.html) | HTML-Datei, in der die **GUI** definiert wird
[javascript.js](\src\main\resources\public\javascript.js) | Javascript-Datei, die die Verbindung zwischen GUI und Javalin herstellt.



## Dokumentation des implementierten WebAPIs

Request/Reply | Funktion
--------|---------
[App.java](\src\main\java\App.java) | 
- "/livematch" (WS) | Der Websocket-Pfad **"/livematch"** wird aktiviert, wenn in der GUI zwei Mannschaften für ein Live-Spiel ausgewählt und der entsprechende Button gedrückt wird, um das Spiel zu starten. Dann wird Javalin die Auswahl übergeben, und daraufhin ein Spiel gestartet. Anschließend wird in regelmäßigen Abständen das gerade aktuelle Ergebnis des Live-Spiels abgefragt, und die entsprechend aktualisierte Tabelle (per toString) mitsamt Spielstand über JSON an den Client geschickt. 
- "/start" | **"/start"** wird beim ersten Laden der Seite aufgerufen, und tut nichts anderes, als die Tabelle zu initialiseren und zur Anzeige in der GUI per ctx.result() zurückzugeben.
- "/addgame" | **"/addgame"** wird aktiviert, wenn in der GUI ein fertiges Spielergebnis eingetragen wird. Über die Parameter wird das Ergebnis Javalin mitgeteilt, woraufhin wieder die Tabelle aktualisiert zurückgegeben wird.




## Dokumentation der Interfaces

Methode | Funktion
--------|---------
[ICountry.java](\src\main\java\ICountry.java) | 
- String getName(); | gibt den **Ländernamen** zurück
- int getGamesPlayed(); | gibt Anzahl **gespielter Spiele** zurück
- WinLooseTie getWinLooseTie(); | gibt die **Sieg-Niederlage**-Unentschieden-Statistik zurück
- Goals getGoals(); | gibt Goals zurück, das **Tore**, Gegentore und Tor-Differenz enthält.
- int getPoints(); | gibt die Gesamt-**Punkte** aller bisher gespielten Spiele der Mannschaft zurück
- void update(int goals, int goalsAgainst); | Gibt dem Land ein Update der Werte (Tore, Punkte, usw) gemäß dem endgültigen Ergebnis eines **beendeten Spiels**
- void liveUpdate(int oldGoals, int oldGoalsAgainst, int newGoals, int newGoalsAgainst, boolean alreadyStarted); | Gibt dem Land ein Update der Werte (Tore, Punkte, usw) gemäß dem aktuellen Spielstand eines **Live-Spiels**
- int points(int goals, int goalsAgainst); | Berechnet, wie viele Punkte der eingegebene Spielstand ergibt
[ITable.java](\src\main\java\ITable.java) | 
- void sortCountries(); | sortiert die Länder nach der vorgegebenen Reihenfolge. Hier: Zuerst nach Punkten, dann nach Tordifferenz, und als letztes nach dem Ländernamen
- Map<String, ICountry> getCountries(); | Gibt die Liste aller in der Tabelle enthaltenen Länder zurück
- ICountry getCountryOnPosition(int position); | Gibt das Land auf der gewünschten Position in der Tabelle zurück
- void update(Match match); | Aktualisiert die Tabelle gemäß dem Ergebnis des eingegebenen Spiels
- JSONObject liveUpdate(SimulatedLiveMatch simMatch, boolean alreadyStarted); | Aktualisiert die Tabelle gemäß dem aktullen Spielstand eines Live-Spiels, und gibt ein JSONObject mit dem Spielstand zurück (das für die Übermittlung zum Client benötigt wird)


## Technischer Anspruch (TA) und Umsetzung der Features

Ich habe folgende Features verwendet. Die verlinkte Datei zeigt beispielhaft den Einsatz dieses
Features in den angegebenen Zeilen im Quellcode.

1. WebSockets, [App.java](\src\main\java\App.java) (30-74)
2. JSON, [javascript.js](\src\main\resources\public\javascript.js) (39-46)

Über Websockets können mehrere Fenster der Anwendung geöffnet werden und miteinander synchronisiert werden.
Außerdem ist es eine wichtige Grundlage, dass noch ein Upgrade stattfinden kann, tatsächlich Live-Daten aus
dem Internet abzurufen.

Per JSON können die Daten der Länder und neue Spielstände sehr unkompliziert übertragen werden.

## Quellennachweis

* Websocket auf Javascript-Seite: https://javalin.io/tutorials/websocket-example
* https://code.tutsplus.com/tutorials/real-time-sports-application-using-nodejs--cms-30594
* Hintergrundbild: https://unsplash.com/photos/nPCiBaK8WPk (Photo by Sylwia Pietruszka on Unsplash)
* Tabelle: http://tablestyler.com/#


**Hinweise**: keine