USE subd;

SELECT
	a.user_id,
    a.user_name,
    c.subscription_id,
    c.subscription_name,
    c.subscription_descr
FROM 
	users as a
LEFT JOIN
	users_to_subscriptions as b ON a.user_id = b.user_id
INNER JOIN
	subscriptions as c ON b.subscription_id = c.subscription_id
ORDER BY user_name;