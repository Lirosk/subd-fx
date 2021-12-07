USE subd;

SELECT
	a.employee_id,
    a.employee_name,
    a.employee_type,
    c.branch_id,
    c.branch_name,
    c.branch_addr
FROM 
	employees as a
INNER JOIN
	employees_to_branches as b ON a.employee_id = b.employee_id
INNER JOIN
	branches as c ON b.branch_id = c.branch_id
ORDER BY employee_name;
