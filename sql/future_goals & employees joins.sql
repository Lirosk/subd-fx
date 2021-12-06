USE subd;

SELECT 
	 b.future_goal_id,
     a.descr,
     c.employee_id,
     c.name
FROM 
	future_goals as a
INNER JOIN 
	future_goals_to_employees as b ON a.future_goal_id = b.future_goal_id
INNER JOIN 
	employees as c ON c.employee_id = b.employee_id
ORDER BY a.descr;
