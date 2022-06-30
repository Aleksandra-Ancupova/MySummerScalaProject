-- TODO milliseconds
SELECT * FROM tracks t ;
 
CREATE INDEX idx_mlseconds
ON tracks(milliseconds);


EXPLAIN QUERY PLAN
SELECT * FROM tracks t 
WHERE Milliseconds > 300000;


-- TODO customers

SELECT FirstName, LastName,    
LENGTH (FirstName || LastName)
FROM customers c 
ORDER BY LENGTH (FirstName || LastName) DESC;


CREATE INDEX idx_customer_name_length
ON customers(LENGTH(FirstName || LastName));

EXPLAIN QUERY PLAN
SELECT * FROM customers c 
WHERE LENGTH(FirstName || LastName) > 20;