# sew9-2425-worttrainer-srychkov"

# Worttrainer Reloaded

## Hier entwickle ich ein Programm für Volksschulkinder, bei dem man die Namen von Tieren anhand eines Bildes erraten muss.

## Dies mache ich in einem SW-Development Prozess.

# Projekbeschreibung aus dem Kurs:

## Inhalt der Anwendung

#### Für einen Rechtschreibtrainer für Wörter (Zielgruppe Volksschulkinder) soll ein erster Entwurf der Funktionalität erstellt werden. Die Kinder sollen dabei zu einem Bild (z.B. einem Hund) das entsprechende Wort eintippen. Dieses Wort wird dann mit der richtigen Schreibweise verglichen und eine entsprechende Meldung wird angezeigt.

## Model

- Paare von Wörtern mit den dazugehörigen Bildern (über eine URL gespeichert, die auf das jeweilige Bild verweist) sind als Klasse abgebildet.
  - Objekte dieser Klasse sind durch entsprechende Checks zu jeder Zeit in einem gültigen Zustand (z.B. bzgl. null-Werte oder ungültiger URLs).
- Der Rechtschreibtrainer selbst ist als Klasse abgebildet.
  - Der Rechtschreibtrainer hat eine Menge an Wort-Bild-Paaren zur Verfügung. Anfangs ist kein Paar ausgewählt.
  - Um zu trainieren kann ein Wort-Bild-Paar ausgewählt werden: entweder wird (mittels Index) ein bestimmtes Paar gewählt, oder ein zufälliges wird ausgewählt.
  - Nachdem ein Paar ausgewählt wurde, kann die Bild-URL abgerufen werden und das zugehörige Wort geraten werden. Bei einer falschen Antwort bleibt das Wort-Bild-Paar ausgewählt; bei einer richtigen Antwort ist das Paar danach nicht mehr ausgewählt und es muss vor dem nächsten Raten ein neues Paar ausgewählt werden.
  - Der Rechtschreibtrainer führt eine Statistik darüber wie oft insgesamt/richtig/falsch geraten wurde.
  - Objekte dieser Klasse sind durch entsprechende Checks zu jeder Zeit in einem gültigen Zustand (z.B. bzgl. null-Werte oder Statistiken); Versuche ungültige Aktionen auszuführen werden erkannt und verhindert.

## Persistenz

#### Eine Worttrainer-Session (bestehend aus den zur Verfügung stehenden Wort-Bild-Paaren, dem aktuell ausgewählten Paar (falls vorhanden) sowie der aktuellen Statistik) soll auch gespeichert werden können. Die genaue Umsetzung ist großteils dir überlassen:

- wähle ein beliebiges Speicherformat (z.B. selbst festgelegt, Java-Serialisierung, JSON, XML, SQLite, ...)
- benutze beliebige dafür geeignete Bibliotheken, falls notwendig (z.B. org.json:json, eine von zahlreichen XML-Libraries, org.xerial:sqlite-jdbc)
- wähle zur Integration der Persistierung in deine Anwendung ein sinnvolles Pattern, sodass die Speicherstrategie austauschbar bleibt.

## Grafische Oberfläche

#### Ermögliche es, den Worttrainer über eine grafische Oberfläche zu benutzen. Es ist dabei ausreichend, JOptionPane zu benutzen. Der Ablauf wäre dann etwa so:

- Beim Programmstart werden die persistierten Daten geladen. Falls keine Daten persistiert sind, wird ein neuer Worttrainer mit einigen fix einprogrammierten Wortpaaren erstellt.
- Danach werden die folgenden Schritte wiederholt:
  - Ein Wortpaar wird zufällig ausgewählt.
  - Mittels JOptionPane wird die bisherige Statistik und das aktuelle Bild angezeigt. Falls es nicht der erste Versuch ist, wird auch angezeigt ob der vorherige Versuch richtig oder falsch war. Außerdem wird hier eine Eingabemöglichkeit für das Wort gegeben.
  - Sofern die Eingabe nicht leer ist, wird die Eingabe überprüft und es geht von vorne los. Ansonsten wird die Schleife abgebrochen.
- Zum Schluss wird der aktuelle Zustand des Worttrainers persistiert.

# Ausführung des Programms:

Beim Ausführen wird ein Bild und Text von einem Tier dargestellt. Unter dem Bild kann man die Tiernamen eintragen.
Falls man ein weiteres Wort/Tier hinzufügen möchte, ist dies durch den Knopf "Hinzufugen" möglich, dabei muss man das Wort hinschreiben und danach einen Link zu einem Bild benutzen. Nach mehreren Versuchen wird die Statistik oben angezeigt. Beim Beenden des Programms werden alle Daten gespeichert und beim Erneuten Start wiederverwendet.
Beim Löschen der Datei wird ein neues Spiel gestartet.
