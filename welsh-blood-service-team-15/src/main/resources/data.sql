-- Inserting countries
INSERT INTO countries (country_name) VALUES
                                         ('Afghanistan'),
                                         ('Albania'),
                                         ('Algeria'),
                                         ('Andorra'),
                                         ('Angola'),
                                         ('Argentina'),  -- Country ID 6
                                         ('Armenia'),
                                         ('Australia'),  -- Region Check needed when implemented
                                         ('Austria'),
                                         ('Bahamas'),
                                         ('Bahrain'),
                                         ('Bangladesh'),
                                         ('Bolivia'),
                                         ('Belarus'),
                                         ('Belgium'),
                                        
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
                                        ('Malaria'),  -- Disease ID 1
                                        ('Trypanosoma Cruzi Risk'),  -- Disease ID 2
                                        ('West Nile Virus Risk'),  -- Disease ID 3
                                        ('Dengue Virus'),  -- Disease ID 4
                                        ('Zika Virus'),  -- Disease ID 5
                                        ('Chikungunya'),  -- Disease ID 6
                                        ('Yellow Fever');  -- Disease ID 7



-- Inserting donors (donor_id will be auto-incremented)
INSERT INTO donor (first_name, last_name, donor_dob, donor_risk, donor_decision, donor_note) VALUES
                                                                                                 ('John', 'Doe', '1985-06-15', 'Low', null, 'No known health risks'),
                                                                                                 ('Jane', 'Smith', '1990-11-22', 'Moderate', null, 'Recent travel to malaria-endemic areas'),
                                                                                                 ('Mary', 'Johnson', '1978-04-09', 'High', null, 'Clear of any diseases');

-- Inserting country_disease occurrences (ensuring correct country_id and disease_id)
INSERT INTO country_disease (country_id, disease_id, note) VALUES
                                                               -- Malaria occurrences
                                                               (1, 1, 'Yes all year whole country until Feb 1996'),  -- Afghanistan
                                                               (3, 1, 'All year round until February 1996'),  -- Algeria
                                                               (5, 1, 'All year in the country'), -- Angola
                                                               (6, 1, 'Until February 1996'),  -- Argentina
                                                               (7, 1, 'Malaria outbreaks during 2008-2015'),  -- Colombia


                                                               -- Trypanosoma Cruzi Risk occurrences
                                                               (6, 2, 'N/A'),  -- Argentina



                                                               -- West Nile Virus Risk occurrences
                                                               (2, 3, 'Donors who have visited between 1st May to 30th November must have the WNV Donor Selection Guideline applied.'), -- Albania
                                                               (3, 3, 'Donors who have visited between 1st May to 30th November must have the WNV Donor Selection Guideline applied.'), -- Algeria
                                                               (7, 3, 'Donors who have visited between 1st May to 30th November must have the WNV Donor Selection Guideline applied.'), -- Armenia
                                                               (9, 3, 'Donors who have visited between 1st May to 30th November must have the WNV Donor Selection Guideline applied.'), -- Austria

                                                               -- Dengue Virus occurrences
                                                               (1, 4, 'N/A'), -- Afghanistan
                                                               (5, 4, 'N/A'), -- Angola
                                                               (6, 4, 'N/A'),  -- Argentina
                                                               (2, 2, 'Coastal areas affected 2015-2018'),  -- Bangladesh
                                                               (4, 2, 'Northern areas faced Dengue Fever outbreaks 2015-2020'),  -- Cameroon
                                                               (11, 2, 'Dengue Fever cases in urban areas 2016-2019'),  -- Honduras

                                                               -- Zika Virus occurrences
                                                               (5, 5, 'N/A'), -- Angola
                                                               (6, 5, 'N/A'),  -- Argentina
                                                               (6, 3, 'Zika virus detected in urban areas 2017-2018'),  -- Cameroon
                                                               (8, 3, 'Zika virus outbreaks in coastal regions 2016-2017'),  -- Ethiopia

                                                               -- Chikungunya occurrences
                                                               (6, 6, 'N/A'),  -- Argentina
                                                               (3, 4, 'Chikungunya cases in urban regions 2015-2017'),  -- Bolivia
                                                               (9, 4, 'Chikungunya outbreaks in southern areas 2010-2015'),  -- Guinea
                                                               (10, 4, 'Chikungunya reported in the capital city 2012-2015'),  -- Guatemala


                                                               -- Yellow Fever occurrences
                                                                (5, 7, 'N/A'), -- Angola
                                                                (6, 2, 'N/A');  -- Argentina


    select * from country_disease;

-- Inserting donor_travelled_to_country records (using correct donor_id and country_id)

INSERT INTO donor_travelled_to_country (donor_id, country_id, start_date, end_date) VALUES
                                                                                        (1, 4, '2017-02-01', '2017-02-10'),  -- John traveled to Cameroon
                                                                                        (1, 3, '2018-07-01', '2018-07-15');  -- John traveled to Bolivia

-- Jane traveled to Chad, Colombia, and Bangladesh
INSERT INTO donor_travelled_to_country (donor_id, country_id, start_date, end_date) VALUES
                                                                                        (2, 5, '2016-05-01', '2016-06-01'),  -- Jane traveled to Chad
                                                                                        (2, 7, '2019-03-15', '2019-04-01');  -- Jane traveled to Colombia

-- Mary traveled to Myanmar, Guatemala, and India
INSERT INTO donor_travelled_to_country (donor_id, country_id, start_date, end_date) VALUES
                                                                                        (3, 15, '2018-05-15', '2018-06-15'),  -- Mary traveled to Myanmar
                                                                                        (3, 10, '2020-01-01', '2020-01-15'),  -- Mary traveled to Guatemala
                                                                                        (3, 12, '2017-08-01', '2017-08-20');  -- Mary traveled to India




INSERT INTO questions (question) VALUES
('Have you travelled to a country within the last 12 months?'),
('Where have you travelled in the last 12 months?'),
('When did you go to this country?'),
('When did you return from this country?'),
('Have you been in contact with anyone who has travelled to a country within the last 12 months?'),
('Have you lived  abroad for more than 6 months'),
('Where have you lived abroad?'),
('When did you go abroad?'),
('When did you return from abroad?'),
('Have you been in contact with anyone who has lived abroad for more than 6 months?'),
('Have you travelled through multiple countries whilst travelling abroad?'),
('Which countries have you travelled through?'),
('When did you go to these countries?'),
('When did you return from these countries?'),
('Have you been in contact with anyone who has travelled through multiple countries whilst travelling abroad?');


INSERT INTO travel_questionnaire (donor_id, question_id, answer)
VALUES
    (1, 1, 'Yes'),  -- Donor 1 travelled in the last 12 months.
    (1, 5, 'No'),   -- Donor 1 has not been in contact with recent travellers.
    (1, 6, 'Yes'),  -- Donor 1 lived abroad for more than 6 months.
    (1, 7, 'India'), -- Donor 1 lived in India.
    (1, 8, '2019-01-01'), -- Donor 1 went to India on 1st January 2019.
    (1, 9, '2020-01-01'), -- Donor 1 returned from India on 1st January 2020.
    (1, 10, 'Yes'), -- Donor 1 has been in contact with someone who lived abroad for more than 6 months.
    (1, 11, 'Yes'), -- Donor 1 travelled through multiple countries.
    (1, 15, 'Yes'), -- Donor 1 has been in contact with someone who travelled through multiple countries.
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






