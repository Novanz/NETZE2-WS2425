---
papersize:
  - a4
fontsize:
  - 12pt
geometry:
  - margin=1in
fontfamily: 
header-includes:
  - \setlength\parindent{24pt}
title: Computernetzwerke 2
author: Anatoly Naprimerov 275093, Luisa Schmid 275300
subtitle: Aufgabenblatt 1
---
\maketitle
\thispagestyle{empty} 
\clearpage 
\tableofcontents 
\pagenumbering{roman} 
\clearpage 
\pagenumbering{arabic} 
\setcounter{page}{1}
## Aufgabe 1
- a) Für E-Mails ist nur Integrität von Bedeutung.
- b) Da die Datenintegrität und Paketverlust keine große Rolle by VoIP-Telefonie spielen, kann man auf TCP  zu Gunsten der besseren Latenz die UDP bietet verzichten.
- c) Weil bei manchen Online-Spielen nicht nur Latenz sondern auch die Tatsache, dass die Pakete nicht verloren gehen. 

## Aufgabe 3
### 3.1

- a) 2
- b) 192.168.200.21 und 192.168.200.135
- c) 2000
- d) hello
- e) Textausschnitt aus einem Buch (Waitzman Experimental Page 6)
- f) 8

### 3.2


- a) download.html
- b) 2004
- c) Windows NT 5.0
- d) User-Agent sagt en-us und Accepted-Language auch, so vermutlich US.
- e) Apache-Server unterstützt HTTP/1.1
- f) Weil es kein Text ist
- g) Es handelt sich sehr wahrscheinlich um ein AD-Bild

### 3.3
 302 Redierekt

## 4
- a) Wie HTTP/1.0 aber Client sendet zusätzlich "keep alive" Token. Der Client sendet "close" Token um zu signalisieren dass Verbindung geschlossen werden kann.
- b) Die Nachrichten enthalten zum Beispiel: HTTP-Methode, Angefragte Ressource, HTTP-Version, Statuscode, Header(User-Agent, Connection usw.). Grundsätzlich ein Client macht ein Request und ein Server antwortet darauf, aber seit HTTP/1.1 dank Pipelining ist es nicht mehr zwingend erforderlich auf Response zu warten bevor nächste Anfrage gemacht wird.
- c) ja, mittels Connection-Header mit "close" Token.
