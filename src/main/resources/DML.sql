use subway;

Insert into subways(city) values
	("Minsk"),
    ("Moscow"),
    ("Berlin");
    
Insert into trains(subway_id, model, speed, creation_date) values
	(1, "Stadler M110/M111", 90, str_to_date("01-01-2018", "%d-%m-%Y")),
    (2, "Oka", 110, str_to_date("01-01-2011", "%d-%m-%Y")),
    (3, "U1", 97, str_to_date("01-01-2016", "%d-%m-%Y")),
    (3, "U6", 115, str_to_date("01-01-2019", "%d-%m-%Y")),
    (1, "A60/A62", 85, str_to_date("01-01-2009", "%d-%m-%Y")),
    (2, "Moscow-2020", 125, str_to_date("01-01-2020", "%d-%m-%Y"));
    
Insert into departments(subway_id, title) values
	(1, "HR"),
    (1, "Engineer"),
    (1, "Security"),
    (2, "Accounting"),
    (2, "Security"),
    (3, "HR"),
    (3, "Logistics"),
    (3, "Engineer");
    
Insert into employees(department_id, first_name, last_name, dob, position) values
	(1, "Wes", "Long", str_to_date("05-10-2001", "%d-%m-%Y"), "HR manager"),
    (2, "Derrick", "Jones", str_to_date("11-07-1985", "%d-%m-%Y"), "Head engineer"),
    (2, "Ronin", "Torres", str_to_date("05-07-1997", "%d-%m-%Y"), "Engineer"),
    (3, "William", "Watson", str_to_date("01-01-1992", "%d-%m-%Y"), "Guard"),
    (4, "Octavio", "Reed", str_to_date("05-03-1975", "%d-%m-%Y"), "Accounter"),
    (6, "Lia", "James", str_to_date("12-09-1986", "%d-%m-%Y"), "Logistician"),
    (7, "Bella", "Bryant", str_to_date("03-03-1994", "%d-%m-%Y"), "Engineer");
    
Insert into addresses(employee_id, city, street, house_number) values 
	(1, "Minsk", "Orlovskaya", 5),
    (2, "Minsk", "Vaneeva", 27),
    (4, "Minsk", "International", 11),
    (5, "Moscow", "Arbat", 1),
    (7, "Berlin", "Himmelstrat", 76);
    
Insert into slines(subway_id, title) values 
	(1, "Moskovskaya"),
    (1, "Autozavodskaya"),
    (2, "Kolzevaya"),
    (2, "Kahovskaya"),
    (3, "U9");
    
Insert into stations(line_id, number, title) values
	(1, 110, "Malinauka"),
    (1, 121, "Maskouskaya"),
    (2, 217, "Niamiha"),
    (2, 212, "Partyzanskaya"),
    (3, 6, "Sokol"),
    (4, 17, "BundesPlatz"),
    (4, 60, "Weberweise");
    
Insert into line_transfers(from_station_id, to_station_id) values
	(1, 3),
    (2, 4),
    (6, 7);

Insert into payment_options(type, price) values
	("ticket", 0.8),
    ("pass card", 12),
    ("unlim ticket", 3);
    
Insert into subway_payment_options(subway_id, payment_option_id) values
	(1, 1),
    (1, 2),
    (2, 2),
    (2, 3),
    (3, 1),
    (3, 2),
    (3, 3);

Insert into privileges(category, discount) values 
	("Pupil", 100),
    ("Retired", 40),
    ("Subway workers", 70);
    
Insert into subway_privileges(privilege_id, subway_id) values
	(1, 1),
    (3, 1),
    (3, 2),
    (1, 3),
    (2, 3);
  
# Установить другой адрес для работника с id 2 
Update addresses set city = "London", street = "Bakerstreet", house_number = 4 where employee_id = 2;

# Установить скидку 40 для всех льгот
Update privileges set discount = 40;

# Для поездов Берлина увеличить скорость на 10
Update trains set speed = speed + 10 where subway_id = 3;

# Увеличить код станций на 1 для линии кольцевая
Update stations inner join slines on stations.line_id = slines.id
set number = number + 1
where slines.title = "Kolzevaya";

# Установить другую станцию перехода для станции с id 1
Update line_transfers set to_station_id = 2 where from_station_id = 1;

# Удалить метро в городах, начинающихся на L
Delete from subways where city like 'L%';

# Удалить все записи таблицы с адресами
Delete from addresses;

# Удалить все варианты оплаты с ценой больше 10
Delete from payment_options where price > 10;

# Удалить все поезда с годом изготовления до 2012
Delete from trains where Year(creation_date) < 2012;