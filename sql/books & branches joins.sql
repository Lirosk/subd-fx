USE subd;

SELECT
	a.book_id,
	title,
	author,
	b.branch_id,
	name,
	addr
FROM
	books as a
INNER JOIN
	books_to_branches as b ON a.book_id = b.book_id
INNER JOIN
	branches as c ON b.branch_id = c.branch_id
ORDER BY title;
