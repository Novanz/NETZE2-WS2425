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
subtitle: Aufgabenblatt 3
author: Anatoly Naprimerov 275093,  Luisa Schmid 275300
date: 30.10.2024
---

## Aufgabe 1
Vor dem ersten Besuch:
![[Screenshot from 2024-10-31 09-05-46.png]]
Nächster Besuch:
![[Screenshot from 2024-10-31 09-06-04.png]]
[Source](https://github.com/Novanz/NETZE2-WS2425/blob/main/B3/SimpleHTTPServer.java)
## Aufgabe 2
### a
![[HTTP_1_0.png]]
Es dauert 2 Sekunden, weil es insgesamt 8 RTTs je 250ms braucht. 
### b
![[HTTP_1_1..png]]
Es dauert nur 0,75 Sekunden, da nur 3 RTTs je 250ms
### c
Ja, falls die Seite schon vorher aufgerufen wurde, dann musste man die RTT zum Proxy für die Berechnungen nehmen.

## Aufgabe 3

### a
Da man bei DNS-Abfragen die Zuverlässigkeit der TCP nicht braucht, und gleichzeitig von der Geschwindigkeit der UDP profitiert. 
### b + c
```sh
# top lvl DNS-Server aus der Liste
dig @198.41.0.4 www.hs-furtwangen.de #output siehe Bild
# de lvl 
dig @194.0.0.53 www.hs-furtwangen.de
# Belwue
dig @129.143.2.10 www.hs-furtwangen.de

```

![[Pasted image 20241031161735.png]]
IP = 185.44.133.90
### d
CNAME = webs.alpha.hs-furtwangen.de