DROP TABLE IF EXISTS donor_travelled_to_country;
DROP TABLE IF EXISTS country_disease;
DROP TABLE IF EXISTS donorDetails;
DROP TABLE IF EXISTS countries;
DROP TABLE IF EXISTS diseases;

USE WelshBlood;

CREATE TABLE IF NOT EXISTS donorDetails
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
    donor_id INT,
    country_id INT,
    start_date DATE,
    end_date DATE,
    PRIMARY KEY(donor_id, country_id),
    FOREIGN KEY(donor_id) REFERENCES donorDetails(donor_id),
    FOREIGN KEY(country_id) REFERENCES countries(country_id)
);

CREATE TABLE IF NOT EXISTS country_disease
(
    country_id INT,
    disease_id INT,
    note VARCHAR(50),
    PRIMARY KEY(country_id, disease_id),
    FOREIGN KEY(country_id) REFERENCES countries(country_id),
    FOREIGN KEY(disease_id) REFERENCES diseases(disease_id)
);
