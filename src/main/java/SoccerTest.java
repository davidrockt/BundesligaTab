// Kein Land kann gegen sich selbst spielen
// WinLoose und Goals als eigene Klasse


/o ITable.java
/o Country.java
/o Match.java
/o Table.java

System.out.println("Running tests ...");

try {
assert false;
System.out.println("Bitte asserts aktivieren: jshell -R-ea");
} catch (AssertionError e) { // Wenn der Error geworfen wird, ist alles gut
}


Country de = new Country("Deutschland");
assert de.getGamesPlayed() == 0 : "Test 1";
assert de.getWinLoose()[0] == 0 && de.getWinLoose()[1] == 0 && de.getWinLoose()[2] == 0: "Test 2";
assert de.getGoals()[0] == 0 && de.getGoals()[1] == 0 && de.getGoals()[2] == 0 : "Test 3";
assert de.getPoints() == 0 : "Test 4";

// de wird upgedatet mit 2 Toren und 1 Gegentor
de.update(1, 1);
assert de.getGamesPlayed() == 1 : "Test 5";
assert de.getWinLoose()[0] == 0 && de.getWinLoose()[1] == 0 && de.getWinLoose()[2] == 1: "Test 6";
assert de.getGoals()[0] == 1 && de.getGoals()[1] == 1 && de.getGoals()[2] == 0 : "Test 7";
assert de.getPoints() == 1 : "Test 8";

Country es = new Country("Spanien");
Map<String, Country> countries = new HashMap<String, Country>() {{
    put("de", de);
    put("es", es);
}};
Table table = new Table(countries);

// Test für richtiges Sortieren
table.sortCountries();
Country[] cArr = table.getCountryOnPosition(); // (Country[]) table.getCountries().toArray();
assert cArr[0].equals(de) && cArr[1].equals(es) : "Test 9";

es.update(3, 2);
assert es.getGamesPlayed() == 1 : "Test 10";
assert es.getWinLoose()[0] == 1 && es.getWinLoose()[1] == 0 && es.getWinLoose()[1] == 0: "Test 11";
assert es.getGoals()[0] == 3 && es.getGoals()[1] == 2 && es.getGoals()[2] == 1 : "Test 12";
assert es.getPoints() == 3 : "Test 13";

table.sortCountries();
cArr = table.getCountryOnPosition();
assert cArr[0].equals(es) && cArr[1].equals(de) : "Test 14";




/*
// Numeral aus max. 4 Zeichen (inkl. '.')
        FloatInput n = new FloatNumber(4);
// Initialwert ist 0, woran auch ein undo nichts ändert
        assert n.getFloat() == 0.0f && n.toString().equals("0") : "Test 1";
        n.undo();
        assert n.getFloat() == 0.0f && n.toString().equals("0") : "Test 2";
// Vorzeichen spielen bei Zahleneingabe keine Rolle
        assert !n.put('-');
        assert !n.put('+');
        assert n.getFloat() == 0.0f && n.toString().equals("0") : "Test 3";
// Eingabe führender Nullen bleibt ohne Effekt
        assert n.put('0');
        assert n.getFloat() == 0.0f && n.toString().equals("0") : "Test 4";
        assert n.put('0');
        assert n.getFloat() == 0.0f && n.toString().equals("0") : "Test 5";
// Eingabe 0.34
        assert n.put('.');
        assert n.getFloat() == 0.0f && n.toString().equals("0.") : "Test 6";
        assert n.put('3');
        assert n.getFloat() == 0.3f && n.toString().equals("0.3") : "Test 7";
        assert n.put('4');
        assert n.getFloat() == 0.34f && n.toString().equals("0.34") : "Test 8";
// Mehr als vier Zeichen sind nicht erlaubt
        assert !n.put('5');
// Eingabe in wissenschaftlicher Notation nicht möglich
        n.undo();
        n.undo();
        assert n.getFloat() == 0.0f && n.toString().equals("0.") : "Test 9";
        assert !n.put('E');
        assert !n.put('e');
        assert n.getFloat() == 0.0f && n.toString().equals("0.") : "Test 10";
// Sei besser als der Windows-Taschenrechner
        n.undo();
        assert n.getFloat() == 0.0f && n.toString().equals("0") : "Test 11";
        assert n.put('0');
        assert n.getFloat() == 0.0f && n.toString().equals("0") : "Test 12";
        assert n.put('7');
        assert n.getFloat() == 7.0f && n.toString().equals("7") : "Test 13";
        assert n.put('1');
        assert n.getFloat() == 71.0f && n.toString().equals("71") : "Test 14";

        */