
-- Inserting donors (donor_id will be auto-incremented)
INSERT INTO donor (first_name, last_name, donor_dob, donor_risk, donor_decision, donor_note) VALUES
                                                                                                 ('John', 'Doe', '1985-06-15', 'Low', 'nul', 'No known health risks'),
                                                                                                 ('Jane', 'Smith', '1990-11-22', 'Moderate', 'nul', 'Recent travel to malaria-endemic areas'),
                                                                                                 ('Mary', 'Johnson', '1978-04-09', 'High', 'nul', 'Clear of any diseases'),
                                                                                                ('Tom', 'Brown', '1980-01-01', 'Low', 'nul', 'No known health risks'),
                                                                                                ('Alice', 'Williams', '1995-03-30', 'Moderate', 'nul', 'Recent travel to malaria-endemic areas');



-- Inserting all countries
INSERT INTO countries (country_name) VALUES
                                         ('Afghanistan'),
                                         ('Bangladesh'),
                                         ('Bolivia'),
                                         ('Cameroon'),
                                         ('Chad'),
                                         ('Colombia'),
                                         ('Congo'),
                                         ('Ethiopia'),
                                         ('Ghana'),
                                         ('Guatemala'),
                                         ('Honduras'),
                                         ('India'),
                                         ('Kenya'),
                                         ('Liberia'),
                                         ('Myanmar'),
                                         ('Nicaragua'),
                                         ('Nigeria'),
                                         ('Pakistan'),
                                         ('Sudan'),
                                         ('Uganda'),
                                         ('Egypt'),
                                         ('China');

-- Inserting diseases
INSERT INTO diseases (disease_name) VALUES
                                        ('Malaria'),
                                        ('Dengue Fever'),
                                        ('Zika Virus'),
                                        ('Chikungunya');




INSERT INTO country_disease (country_id, disease_id, note, start_date, end_date) VALUES

                                                               -- Malaria occurrences
                                                               (1, 1, 'High rates of malaria from 2001-04-04 2001-04-05','2001-04-04', '2001,04,05'),  -- Afghanistan
                                                               (2, 1, 'Malaria widespread in rural areas from 2005-2015','2001-04-04', '2001,04,05'),  -- Bangladesh
                                                               (6, 1, 'Malaria present during rainy season from 2006-2010', '2017-02-01', '2017-02-10'),  -- Cameroon
                                                               (7, 1, 'Malaria outbreaks during 2008-2015', '2019-03-15', '2019-04-01'),  -- Colombia

                                                               -- Dengue Fever occurrences
                                                               (2, 2, 'Coastal areas affected 2015-2018','2001-04-04', '2001,04,05'),  -- Bangladesh
                                                               (4, 2, 'Northern areas faced Dengue Fever outbreaks 2015-2020','2001-04-04', '2001,04,05'),  -- Cameroon
                                                               (11, 2, 'Dengue Fever cases in urban areas 2016-2019','2001-04-04', '2001,04,05'),  -- Honduras

                                                               -- Zika Virus occurrences
                                                               (5, 3, 'Zika virus outbreak in rural areas 2015-2017','2001-04-04', '2001,04,05'),  -- Chad
                                                               (6, 3, 'Zika virus detected in urban areas 2017-2018','2001-04-04', '2001,04,05'),  -- Cameroon
                                                               (8, 3, 'Zika virus outbreaks in coastal regions 2015-04-04 to 2016-05-09','2015-04-04', '2016-05-09'),  -- Ethiopia  (where alice travelled not in same times)

                                                               -- Chikungunya occurrences
                                                               (11, 4, 'Chikungunya cases in urban regions 2001-04-04 to 2001-04-05', '2001-04-04', '2001-04-05'),  -- honduras (where alice travelled not in same times)
                                                               (9, 4, 'Chikungunya outbreaks in southern areas 2010-2015','2001-04-04', '2001,04,05'),  -- Guinea
                                                               (10, 4, 'Chikungunya reported in the capital city 2020-01-01 to 2022-01-15','2020-01-01', '2022-01-15'),  -- Guatemala
                                                               -- insert for india
                                                               (12, 1, 'High rates of malaria from 2000-2010', '2017-08-01', '2017-08-20'),  -- India
                                                               -- inserts for myanamar
                                                               (15, 1, 'High rates of malaria from 2000-2010', '1991-05-15', '1992-06-15');  -- Myanmar





INSERT INTO donor_travelled_to_country (donor_id, country_id, start_date, end_date) VALUES
                                                                                        (1, 4, '2017-02-01', '2017-02-10'),  -- John traveled to Cameroon
                                                                                        (1, 3, '2018-07-01', '2018-07-15'),  -- John traveled to Bolivia
                                                                                        (2, 5, '2016-05-01', '2016-06-01'),  -- Jane traveled to Chad
                                                                                        (2, 7, '2019-03-15', '2019-04-01'),  -- Jane traveled to Colombia
                                                                                        (3, 15, '2018-05-15', '2018-06-15'),  -- Mary traveled to Myanmar
                                                                                        (3, 10, '2020-01-01', '2020-01-15'),  -- Mary traveled to Guatemala
                                                                                        (3, 12, '2017-07-30', '2017-07-31'), -- Mary traveled to India
                                                                                        -- inserts for donor 5 alice
                                                                                        (5, 8, '2014-05-09', '2015-05-08'), -- Alice went to ethiopia
                                                                                        (5, 11, '2001-04-05', '2002-01-23'); -- alice went to honduras



INSERT INTO visitor_travelled_to_country (donor_id, country_id, start_date, end_date)
VALUES
    (1, 10, '2024-01-01', '2024-02-01'), -- guatamala
    (2, 15, '1992-05-01', '2023-06-15'), -- myanmar
    (3, 12, '2016-07-30', '2017-08-17'); -- india





INSERT INTO questions (question, no_risk_response) VALUES
('Have you travelled to a country within the last 12 months?', 'No'),
('Where have you travelled in the last 12 months?', '0'),
('When did you go to this country?', '0'),
('When did you return from this country?', '0'),
('Have you been in contact with anyone who has travelled to a country within the last 12 months?', 'No'),
('Where did they travel to?'),
('When did they go to this country?'),
('When did they return from this country?'),
('Have you lived  abroad for more than 6 months', 'No'),
('Where have you lived abroad?', '0'),
('When did you go abroad?', '0'),
('When did you return from abroad?', '0'),
('Have you been in contact with anyone who has lived abroad for more than 6 months?', 'No'),
('Where did they live abroad?'),
('When did they live abroad?'),
('When did they return from abroad?'),
('Have you travelled through multiple countries whilst travelling abroad?', 'No'),
('Which countries have you travelled through?', '0'),
('When did you go to these countries?', '0'),
('When did you return from these countries?', '0'),
('Have you been in contact with anyone who has travelled through multiple countries whilst travelling abroad?', 'No'),
('Which countries did they travel through?'),
('When did they go to these countries?'),
('When did they return from these countries?');



INSERT INTO travel_questionnaire (donor_id, question_id, answer)
VALUES
    (5, 1, 'Yes'),  -- Donor 1 travelled in the last 12 months.
    (5, 5, 'No'),   -- Donor 1 has not been in contact with recent travellers.
    (5, 6, 'Yes'),  -- Donor 1 lived abroad for more than 6 months.
    (5, 7, 'India'), -- Donor 1 lived in India.
    (5, 8, '2019-01-01'), -- Donor 1 went to India on 1st January 2019.
    (5, 9, '2020-01-01'), -- Donor 1 returned from India on 1st January 2020.
    (5, 10, 'Yes'), -- Donor 1 has been in contact with someone who lived abroad for more than 6 months.
    (5, 11, 'Yes'), -- Donor 1 travelled through multiple countries.
    (5, 15, 'Yes'), -- Donor 1 has been in contact with someone who travelled through multiple countries.
    (2, 1, 'No'),   -- Donor 2 did not travel in the last 12 months.
    (2, 5, 'Yes'),  -- Donor 2 has been in contact with recent travellers.
    (2, 6, 'No'),   -- Donor 2 has not lived abroad for more than 6 months.
    (2, 11, 'No'),  -- Donor 2 did not travel through multiple countries.
    (2, 15, 'No'),  -- Donor 2 has not been in contact with someone who travelled through multiple countries.
    (3, 1, 'Yes'),  -- Donor 3 travelled in the last 12 months.
    (3, 5, 'No'),   -- Donor 3 has not been in contact with recent travellers.
    (3, 6, 'No'),  -- Donor 3 lived abroad for more than 6 months.
    (3, 11, 'No'),  -- Donor 3 did not travel through multiple countries.
    (3, 15, 'No')  -- Donor 3 has not been in contact with someone who travelled through multiple countries.
;

