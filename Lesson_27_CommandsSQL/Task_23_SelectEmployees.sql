#Вивести всіх employee в яких salary між 2000 i 5000
SELECT 
    *
FROM
    employees
WHERE
    salary BETWEEN 2000.0 AND 5000.0
ORDER BY salary;

#Вивести всіх employee в яких jobId = 9,10,11
SELECT 
    *
FROM
    employees
WHERE
    job_id IN (9 , 10, 11);
    
#Вивести всіх(або одного) в кого найбільша salary
SELECT 
    *
FROM
    employees
WHERE
    salary = (SELECT 
            MAX(salary)
        FROM
            employees);
            
#Вивести всіх(або одного) в кого найменша salary
SELECT 
    *
FROM
    employees
WHERE
    salary = (SELECT 
            MIN(salary)
        FROM
            employees);