SELECT moteromID, sted, navn, storrelse 
FROM MOTEROM 
WHERE storrelse >= 15 AND moteromID NOT IN 
(SELECT moteromID FROM MOTEROM WHERE moteromID IN 
(SELECT moteromID FROM AVTALE 
WHERE (start <= 250120151101 AND slutt >= 250120151101)
OR (start <= 250120151115 AND slutt >= 250120151115)
OR (start > 250120151101 AND slutt < 250120151115)))
