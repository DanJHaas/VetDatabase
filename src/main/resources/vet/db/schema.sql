

DROP TABLE IF EXISTS invoices;
DROP TABLE IF EXISTS items;
DROP TABLE IF EXISTS appointments;
DROP TABLE IF EXISTS staff;
DROP TABLE IF EXISTS employees;
DROP TABLE IF EXISTS patients;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS clinics;


CREATE TABLE clinics(
    clinic_id       INTEGER PRIMARY KEY,
    name            VARCHAR(50) NOT NULL,
    address         VARCHAR(50) NOT NULL,
    phone_number    VARCHAR(12) NOT NULL
);

CREATE TABLE users(
    user_id         INTEGER PRIMARY KEY,
    clinic_id       INTEGER,
    first_name      VARCHAR(50) NOT NULL,
    last_name       VARCHAR(50) NOT NULL,
    phone_number    VARCHAR(12) NOT NULL,
    email           VARCHAR(50),
    current_bill    DOUBLE DEFAULT 0.0,
    FOREIGN KEY (clinic_id) REFERENCES clinics(clinic_id)
);

CREATE TABLE patients(
    patient_id         	INTEGER PRIMARY KEY,
    user_id			   	INTEGER,
    first_name      	VARCHAR(50) NOT NULL,
    last_name       	VARCHAR(50) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(user_id)

);


CREATE TABLE employees(
    employee_id     INTEGER PRIMARY KEY,
    first_name      VARCHAR(50) NOT NULL,
    last_name       VARCHAR(50) NOT NULL,
    phone_number    VARCHAR(12) NOT NULL
);

CREATE TABLE staff(
    staff_id    	INTEGER PRIMARY KEY,
    employee_id 	INTEGER,
    clinic_id 		INTEGER,
    schedule    	VARCHAR(200),
    FOREIGN KEY (employee_id) REFERENCES employees(employee_id),
    FOREIGN KEY (clinic_id) REFERENCES clinics(clinic_id)
);

CREATE TABLE appointments(
    appointment_id    	INTEGER PRIMARY KEY,
    employee_id 		INTEGER,
    patient_id 			INTEGER,
    summary     		VARCHAR(200),
    date_time     		INTEGER,
    FOREIGN KEY (employee_id) REFERENCES employees(employee_id),
    FOREIGN KEY (patient_id) REFERENCES patients(patient_id)
);

CREATE TABLE items(
    item_id    	INTEGER PRIMARY KEY,
    clinic_id 	INTEGER,
    item_name     	VARCHAR(50) NOT NULL,
    item_cost     	DOUBLE DEFAULT 0.0,
    item_count    	INTEGER DEFAULT 0,
    FOREIGN KEY (clinic_id) REFERENCES clinics(clinic_id)

);

CREATE TABLE invoices(
    invoice_id    	INTEGER PRIMARY KEY,
    appointment_id 	INTEGER,
    item_id 		INTEGER,
    items_used     		INTEGER NOT NULL,
    item_cost     		DOUBLE NOT NULL,
    FOREIGN KEY (appointment_id) REFERENCES appointments(appointment_id),
    FOREIGN KEY (item_id) REFERENCES items(item_id)
);
