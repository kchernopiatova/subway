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