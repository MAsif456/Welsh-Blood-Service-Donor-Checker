USE WelshBlood;
DROP TABLE IF EXISTS donor_travelled_to_country;
drop table if exists visitor_travelled_to_country;

DROP TABLE IF EXISTS country_disease;
DROP TABLE IF EXISTS travel_questionnaire;
DROP TABLE IF EXISTS questions;
DROP TABLE IF EXISTS diseases;
DROP TABLE IF EXISTS donor;
DROP TABLE IF EXISTS countries;


CREATE TABLE IF NOT EXISTS donor
(
    donor_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    donor_dob DATE,
    donor_risk VARCHAR(50),
    donor_decision VARCHAR(50),
    donor_note VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS countries
(
    country_id INT AUTO_INCREMENT PRIMARY KEY,
    country_name VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS diseases
(
    disease_id INT AUTO_INCREMENT PRIMARY KEY,
    disease_name VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS donor_travelled_to_country
(
    id INT AUTO_INCREMENT PRIMARY KEY,
    donor_id INT,
    country_id INT,
    start_date DATE,
    end_date DATE,
    FOREIGN KEY(donor_id) REFERENCES donor(donor_id),
    FOREIGN KEY(country_id) REFERENCES countries(country_id)
);

CREATE TABLE IF NOT EXISTS country_disease
(
    id INT AUTO_INCREMENT PRIMARY KEY,
    country_id INT,
    disease_id INT,
    note VARCHAR(200),
   -- PRIMARY KEY(country_id, disease_id),
    start_date DATE,
    end_date DATE,
    FOREIGN KEY(country_id) REFERENCES countries(country_id),
    FOREIGN KEY(disease_id) REFERENCES diseases(disease_id)
);

CREATE TABLE IF NOT EXISTS questions
(
    question_id INT AUTO_INCREMENT PRIMARY KEY,
    question VARCHAR(255),
    no_risk_response VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS travel_questionnaire
(
    questionnaire_id INT AUTO_INCREMENT PRIMARY KEY,
    donor_id INT,
    question_id INT,
    answer VARCHAR(50),
    FOREIGN KEY(donor_id) REFERENCES donor(donor_id),
    FOREIGN KEY(question_id) REFERENCES questions(question_id)
);

