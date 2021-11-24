use subway;

#Вывод данных обо всех работниках
Select * from employees;

#Вывод данных о поездах, где год создания после 2015
Select trains.id as train_number, city, model, speed, year(creation_date) as year 
	from trains inner join subways on trains.subway_id = subways.id
Where year(creation_date) > 2015;

#Вывод данных обо всех отделах и их сотрудниках (данные выводятся даже об отделах без сотрудников)
Select title, first_name, last_name from departments left join employees on department_id = departments.id;

#Вывод всех уникальных названий отделов
Select distinct title from departments;

#вывод информации о работниках отделов HR и Engineer
Select first_name, last_name, position from employees inner join departments on department_id = departments.id
where departments.title IN("HR", "Engineer");

#вывод информации о количестве моделей поездов в каждом метро
Select city, count(trains.id) as trains_amount from subways left join trains on subway_id = subways.id
Group by city;

#Вывод информации о адресах всех сотрудников, чья фамилия начинается с буквы J
Select first_name, last_name, dob, city, street, house_number from employees left join addresses on employees.id = addresses.employee_id
Where last_name Like 'J%';

#Вывод информации о станции, линии и метро, к которым она принадлежит, сортировка по возрастанию кода станции
Select city, ln.title as line, number as station_number, st.title as station from stations st 
inner join slines ln on st.line_id = ln.id
inner join subways on ln.subway_id = subways.id
order by station_number;

#Вывод средней стоимости проездных билетов по городам, начинающимся
Select city, round(avg(price), 2) as average_price from subways 
join subway_payment_options on subway_id = subways.id
join payment_options on payment_option_id = payment_options.id
group by city;

#Вывод всех городов из таблиц метро и адреса без повторяющихся значений 
Select city from subways
Union
Select city from addresses;

#-//- с повторяющимися значениями (сортировка по алфавиту)
Select city from subways
Union all
Select city from addresses
order by city;

#Вывод поездов со скоростью от 100 до 120
Select * from trains
Where speed between 100 and 120;

#Вывод 3 поездов с наибольшей скоростью
Select * from trains 
order by speed desc limit 3;