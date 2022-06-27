--I was hired by a woman with a lot of money. I don't know her name but I know she's around 5'5" (65") or 5'7" (67"). 
-- She has red hair and she drives a Tesla Model S. I know that she attended the SQL Symphony Concert 3 times in December 2017.

SELECT * FROM drivers_license dl
WHERE car_make LIKE 'Tesla%'
AND car_model LIKE 'Model S'
AND hair_color LIKE 'red'
AND gender LIKE 'female';

SELECT * FROM person p
JOIN drivers_license dl 
ON p.license_id = dl.id
WHERE car_make LIKE 'Tesla%'
AND car_model LIKE 'Model S'
AND hair_color LIKE 'red'
AND gender LIKE 'female';


SELECT COUNT(*) attendance From facebook_event_checkin fec
WHERE event_name LIKE 'SQL Symphony Concert'
AND date LIKE '201712%'
GROUP BY fec.person_id;


SELECT fec.person_id, fec.date, p.name,
COUNT(*) attendance FROM facebook_event_checkin fec
JOIN person p 
ON fec.person_id = p.id 
WHERE event_name LIKE 'SQL Symphony Concert'
AND date LIKE '201712%'
GROUP BY fec.person_id
ORDER BY COUNT(fec.person_id) DESC;

