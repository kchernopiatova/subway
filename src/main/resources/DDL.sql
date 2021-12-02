
drop database subway;
create database if not exists subway;
use subway;

create table if not exists subways (
	id serial,
    city varchar(20) not null,
    primary key(id)
    );
    
create unique index unique_subways_city on subways(city);
    
create table if not exists trains (
	id serial,
    subway_id bigint unsigned not null,
    model varchar(45) not null,
    speed int unsigned not null,
    creation_date timestamp not null,
    primary key(id),
    constraint fk_trains_subway_id foreign key(subway_id) references subways(id)
		on update no action 
		on delete cascade
    );
    
create table if not exists departments (
	id serial,
    subway_id bigint unsigned not null,
    title varchar(45),
    primary key(id),
    constraint fk_departments_subway_id foreign key(subway_id) references subways(id) 
		on update no action 
		on delete cascade
    );

create table if not exists employees (
	id serial,
    department_id bigint unsigned not null,
    first_name varchar(20) not null,
    last_name varchar(20) not null,
    dob timestamp not null,
    position varchar(20) not null,
    primary key(id),
    constraint fk_employees_department_id foreign key(department_id) references departments(id) 
		on update no action 
		on delete cascade
    );

create table if not exists addresses (
	id serial,
    employee_id bigint unsigned not null,
    city varchar(45) not null,
    street varchar(45) not null,
    house_number int not null,
    primary key(id),
    constraint fk_addresses_employee_id foreign key(employee_id) references employees(id) 
		on update no action 
		on delete cascade
    );
    
create unique index unique_idx_addresses_employee on addresses(employee_id);

create table if not exists slines (
	id serial,
    subway_id bigint unsigned not null,
    title varchar(45) not null,
    primary key(id),
    constraint fk_slines_subway_id foreign key(subway_id) references subways(id) 
		on update no action 
		on delete cascade
    );

create table if not exists stations (
	id serial,
    line_id bigint unsigned not null,
    number int not null,
    title varchar(45),
    primary key(id),
    constraint fk_stations_line_id foreign key(line_id) references slines(id) 
		on update no action 
		on delete cascade
    );
    
create table if not exists line_transfers (
	id serial,
    from_station_id bigint unsigned not null,
    to_station_id bigint unsigned not null,
    primary key(id),
    constraint fk_line_transfers_from_station_id foreign key(from_station_id) references stations(id) 
		on update no action 
		on delete cascade,
	constraint fk_line_transfers_to_station_id foreign key(to_station_id) references stations(id) 
		on update no action 
		on delete cascade
    );
    
    create unique index unique_transfers_from_station on line_transfers(from_station_id);
    create unique index unique_transfers_to_station on line_transfers(to_station_id);
    
create table if not exists payment_options (
	id serial,
    type varchar(45) not null,
    price DECIMAl,
    primary key (id)
    );

create table if not exists subway_payment_options (
	subway_id bigint unsigned not null,
    payment_option_id bigint unsigned not null,
    constraint fk_subway_payment_options_subway_id foreign key(subway_id) references subways(id)
		on update no action 
		on delete cascade,
	constraint fk_subway_payment_options_payment_option_id foreign key(payment_option_id) references payment_options(id)
		on update no action 
		on delete cascade
    );
    
create table if not exists privileges (
	id serial,
    category varchar(45) not null,
    discount int not null,
    primary key (id)
    );
    
create table if not exists subway_privileges (
	privilege_id bigint unsigned not null,
    subway_id bigint unsigned not null,
    constraint fk_subway_privileges_privilege_id foreign key(privilege_id) references privileges(id) 
		on update no action 
		on delete cascade,
	constraint fk_subway_privileges_subway_id foreign key(subway_id) references subways(id) 
		on update no action 
		on delete cascade
    );