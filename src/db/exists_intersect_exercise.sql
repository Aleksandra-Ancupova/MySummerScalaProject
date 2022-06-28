-- TODO 
-- query for all tracks that appear on any playlist
-- (playlist_track)
-- order by track name

SELECT TrackId, Name FROM tracks t 
WHERE EXISTS (
	SELECT TrackId FROM tracks t
	INTERSECT
	SELECT TrackId FROM playlist_track pt)
ORDER BY Name;


-- TODO 2
-- query for all tracks that have been bought 

SELECT TrackId, Name FROM tracks t
WHERE EXISTS (
	SELECT TrackId FROM tracks t2 
	INTERSECT
	SELECT TrackId FROM invoice_items ii);