-- TODO 1
-- whuch city has the most invoice?
-- order by invoice count

SELECT BillingCity, COUNT(BillingCity) cnt FROM invoices i 
GROUP BY BillingCity 
ORDER BY cnt DESC;

-- TODO 2
-- Which cities have the best customers
-- ordered list
-- 5 best cities with highest sum of totals

SELECT BillingCity, SUM(Total) total FROM invoices i 
GROUP BY BillingCity 
ORDER BY total DESC
LIMIT 5;

--TODO 3 Find the biggest 3 spenders
-- join customers and invoices and invoice item
-- then Group BY and Sum on grouped TOTAL

SELECT i.CustomerId, c.FirstName, c.LastName, SUM(total) totalByCustomer 
FROM invoices i 
JOIN customers c 
ON i.CustomerId = c.CustomerId 
GROUP BY i.CustomerId 
ORDER BY totalByCustomer DESC 
LIMIT 3;

-- TODO 4 
-- find all listeners to classical music
-- include names, emails, phones

SELECT ii.TrackId, i.CustomerId, c.FirstName, c.LastName, c.Email, c.Phone, g.Name AS genreName
FROM invoice_items ii  
JOIN invoices i 
ON ii.InvoiceId = i.InvoiceId
JOIN customers c 
ON i.CustomerId = c.CustomerId
JOIN tracks t 
ON ii.TrackId = t.TrackId
JOIN genres g 
ON t.GenreId = g.GenreId
WHERE g.Name = 'Classical';
