

DROP TABLE IF EXISTS clinics;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS patients;
DROP TABLE IF EXISTS employees;
DROP TABLE IF EXISTS staff;
DROP TABLE IF EXISTS appointments;
DROP TABLE IF EXISTS items;
DROP TABLE IF EXISTS invoices;


CREATE TABLE clinics(
    clinic_id       INTEGER PRIMARY KEY,
    name            TEXT NOT NULL,
    address         TEXT NOT NULL,
    phone_number    VARCHAR(12) NOT NULL
);

CREATE TABLE users(
    user_id         INTEGER PRIMARY KEY,
    FOREIGN KEY clinic_id REFERENCES clinics(clinic_id),
    first_name      TEXT NOT NULL,
    last_name       TEXT NOT NULL,
    phone_number    VARCHAR(12),
    email           TEXT NOT NULL,
    current_bill    DOUBLE DEFAULT 0.0
);

CREATE TABLE patients(
    patient_id         INTEGER PRIMARY KEY,
    FOREIGN KEY user_id REFERENCES users(user_id),
    first_name      TEXT NOT NULL,
    last_name       TEXT NOT NULL
);


CREATE TABLE employees(
    employee_id     INTEGER PRIMARY KEY,
    first_name      TEXT NOT NULL,
    last_name       TEXT NOT NULL,
    phone_number    VARCHAR(12) NOT NULL
);

CREATE TABLE staff(
    staff_id    INTEGER PRIMARY KEY,
    FOREIGN KEY employee_id REFERENCES employees(employee_id),
    FOREIGN KEY clinic_id REFERENCES clinics(clinic_id),
    schedule    TEXT
);

CREATE TABLE appointments(
    appointment_id    INTEGER PRIMARY KEY,
    FOREIGN KEY employee_id REFERENCES employees(employee_id),
    FOREIGN KEY patient_id REFERENCES patients(patient_id),
    summary     TEXT,
    datetime     INTEGER,
);

CREATE TABLE items(
    item_id    INTEGER PRIMARY KEY,
    FOREIGN KEY clinic_id REFERENCES clinics(clinic_id),
    name     TEXT NOT NULL,
    cost     DOUBLE DEFAULT 0.0,
    count    INTEGER DEFAULT 0
);

CREATE TABLE invoices(
    invoice_id    INTEGER PRIMARY KEY,
    FOREIGN KEY appointment_id REFERENCES appointments(appointment_id),
    FOREIGN KEY item_id REFERENCES items(item_id),
    used     INTEGER NOT NULL,
    cost     DOUBLE NOT NULL
);
