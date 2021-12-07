USE subd;

SELECT 
	b.employee_id, 
    b.employee_name,
    b.employee_type
FROM
	(SELECT * FROM employees_to_branches WHERE branch_id = 1) as a
INNER JOIN employees as b ON a.employee_id = b.employee_id
ORDER BY employee_name;