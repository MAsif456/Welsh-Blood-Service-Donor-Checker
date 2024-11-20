-- Inserting countries
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

-- Inserting donors (donor_id will be auto-incremented)
INSERT INTO donor (first_name, last_name, donor_dob, donor_risk, donor_decision, donor_note) VALUES
                                                                                                 ('John', 'Doe', '1985-06-15', 'Low', null, 'No known health risks'),
                                                                                                 ('Jane', 'Smith', '1990-11-22', 'Moderate', null, 'Recent travel to malaria-endemic areas'),
                                                                                                 ('Mary', 'Johnson', '1978-04-09', 'High', null, 'Clear of any diseases');

-- Inserting country_disease occurrences (ensuring correct country_id and disease_id)
INSERT INTO country_disease (country_id, disease_id, note) VALUES
                                                               -- Malaria occurrences
                                                               (1, 1, 'High rates of malaria from 2000-2010'),  -- Afghanistan
                                                               (2, 1, 'Malaria widespread in rural areas from 2005-2015'),  -- Bangladesh
                                                               (6, 1, 'Malaria present during rainy season from 2006-2010'),  -- Cameroon
                                                               (7, 1, 'Malaria outbreaks during 2008-2015'),  -- Colombia

                                                               -- Dengue Fever occurrences
                                                               (2, 2, 'Coastal areas affected 2015-2018'),  -- Bangladesh
                                                               (4, 2, 'Northern areas faced Dengue Fever outbreaks 2015-2020'),  -- Cameroon
                                                               (11, 2, 'Dengue Fever cases in urban areas 2016-2019'),  -- Honduras

                                                               -- Zika Virus occurrences
                                                               (5, 3, 'Zika virus outbreak in rural areas 2015-2017'),  -- Chad
                                                               (6, 3, 'Zika virus detected in urban areas 2017-2018'),  -- Cameroon
                                                               (8, 3, 'Zika virus outbreaks in coastal regions 2016-2017'),  -- Ethiopia

                                                               -- Chikungunya occurrences
                                                               (3, 4, 'Chikungunya cases in urban regions 2015-2017'),  -- Bolivia
                                                               (9, 4, 'Chikungunya outbreaks in southern areas 2010-2015'),  -- Guinea
                                                               (10, 4, 'Chikungunya reported in the capital city 2012-2015');  -- Guatemala


    select * from countries where country_name = 'Honduras' ;

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



