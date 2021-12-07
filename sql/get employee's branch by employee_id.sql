USE subd;

SELECT 
	b.branch_id,
	b.branch_name, 
    b.branch_addr 
FROM
	(
		SELECT 
			branch_id 
		FROM 
			employees_to_branches 
		WHERE employee_id = 1
    ) as a
INNER JOIN branches as b ON a.branch_id = b.branch_id;