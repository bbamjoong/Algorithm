SELECT I.ANIMAL_ID, I.NAME
FROM ANIMAL_INS I
join ANIMAL_OUTS O on I.animal_id = o.animal_id
ORDER BY (O.DATETIME - I.DATETIME) DESC
LIMIT 2;