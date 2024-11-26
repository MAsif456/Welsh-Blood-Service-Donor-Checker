
-- Inserting donors (donor_id will be auto-incremented)
INSERT INTO donor (first_name, last_name, donor_dob, donor_risk, donor_decision, donor_note) VALUES
                                                                                                 ('John', 'Doe', '1985-06-15', 'Low', null, 'No known health risks'),
                                                                                                 ('Jane', 'Smith', '1990-11-22', 'Moderate', null, 'Recent travel to malaria-endemic areas'),
                                                                                                 ('Mary', 'Johnson', '1978-04-09', 'High', null, 'Clear of any diseases');


-- Inserting all countries
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
                                         ('Azerbaijan'),
                                         ('Bahamas'),  -- Country ID 11
                                         ('Bahrain'),
                                         ('Bangladesh'),  -- Country ID 13
                                         ('Barbados'),
                                         ('Belarus'),
                                         ('Belgium'),
                                         ('Bosnia and Herzegovina'),
                                         ('Botswana'),
                                         ('Brazil'),
                                         ('Brunei'),
                                         ('Bulgaria'),  -- Country ID 21
                                         ('Burkina Faso'),  -- Not done
                                         ('Burundi'),
                                         ('Cabo Verde'),
                                         ('Cambodia'), -- Country ID 25
                                         ('Cameroon'),
                                         ('Canada'),
                                         ('Central African Republic'),
                                         ('Chad'),
                                         ('Chile'),
                                         ('China'),
                                         ('Colombia'), -- Country ID 32
                                         ('Comoros'),-- Not done
                                         ('Congo'), -- Not done
                                         ('Costa Rica'),
                                         ('Croatia'), -- Not done
                                         ('Cuba'),-- Not done
                                         ('Cyprus'),-- Not done
                                         ('Czechia (Czech Republic)'),-- Not done
                                         ('Denmark'),
                                         ('Djibouti'),
                                         ('Dominica'),
                                         ('Dominican Republic'),
                                         ('Ecuador'),
                                         ('Egypt'),
                                         ('El Salvador'),
                                         ('Equatorial Guinea'),
                                         ('Eritrea'),
                                         ('Estonia'),
                                         ('Eswatini (fmr. "Swaziland")'),
                                         ('Ethiopia'), -- Country Id 51
                                         ('Fiji'),
                                         ('Finland'),
                                         ('France'),  -- Region Check needed when implemented
                                         ('Gabon'),
                                         ('Gambia'),
                                         ('Georgia'),
                                         ('Germany'),
                                         ('Ghana'),
                                         ('Greece'),
                                         ('Grenada'),
                                         ('Guatemala'),
                                         ('Guinea'),
                                         ('Guinea-Bissau'),
                                         ('Guyana'),
                                         ('Haiti'),
                                         ('Holy See'),
                                         ('Honduras'),
                                         ('Hungary'),
                                         ('Iceland'),
                                         ('India'),
                                         ('Indonesia'),
                                         ('Iran'),
                                         ('Iraq'),
                                         ('Ireland'),
                                         ('Italy'),
                                         ('Jamaica'),
                                         ('Japan'),
                                         ('Jordan'),
                                         ('Kazakhstan'),
                                         ('Kenya'),
                                         ('Kiribati'),
                                         ('Korea (North)'),
                                         ('Korea (South)'),
                                         ('Kosovo'),
                                         ('Kuwait'),
                                         ('Kyrgyzstan'),
                                         ('Laos'),
                                         ('Latvia'),
                                         ('Lebanon'),
                                         ('Lesotho'),
                                         ('Liberia'),
                                         ('Libya'),
                                         ('Liechtenstein'),
                                         ('Lithuania'),
                                         ('Luxembourg'),
                                         ('Madagascar'),
                                         ('Malawi'),
                                         ('Malaysia'),
                                         ('Maldives'),
                                         ('Mali'),
                                         ('Malta'),
                                         ('Marshall Islands'),
                                         ('Mauritania'),
                                         ('Mauritius'),
                                         ('Mexico'),
                                         ('Micronesia'),
                                         ('Moldova'),
                                         ('Monaco'),
                                         ('Mongolia'),
                                         ('Montenegro'),
                                         ('Morocco'),
                                         ('Mozambique'),
                                         ('Myanmar (Burma)'),
                                         ('Namibia'),
                                         ('Nauru'),
                                         ('Nepal'),
                                         ('Netherlands'),
                                         ('New Zealand'),
                                         ('Nicaragua'),
                                         ('Niger'),
                                         ('Nigeria'),
                                         ('North Macedonia (formerly Macedonia)'),
                                         ('Norway'),
                                         ('Oman'),
                                         ('Pakistan'),
                                         ('Palau'),
                                         ('Palestine'),
                                         ('Panama'),
                                         ('Papua New Guinea'),
                                         ('Paraguay'),
                                         ('Peru'),
                                         ('Philippines'),
                                         ('Poland'),
                                         ('Portugal'),
                                         ('Qatar'),
                                         ('Romania'),
                                         ('Russia'),
                                         ('Rwanda'),
                                         ('Saint Kitts and Nevis'),
                                         ('Saint Lucia'),
                                         ('Saint Vincent and the Grenadines'),
                                         ('Samoa'),
                                         ('San Marino'),
                                         ('Sao Tome and Principe'),
                                         ('Saudi Arabia'),
                                         ('Senegal'),
                                         ('Serbia'),
                                         ('Seychelles'),
                                         ('Sierra Leone'),
                                         ('Singapore'),
                                         ('Slovakia'),
                                         ('Slovenia'),
                                         ('Solomon Islands'),
                                         ('Somalia'),
                                         ('South Africa'),
                                         ('South Sudan'),
                                         ('Spain'),
                                         ('Sri Lanka'),
                                         ('Sudan'),
                                         ('Suriname'),
                                         ('Sweden'),
                                         ('Switzerland'),
                                         ('Syria'),
                                         ('Tajikistan'),
                                         ('Tanzania'),
                                         ('Thailand'),
                                         ('Timor-Leste'),
                                         ('Togo'),
                                         ('Tonga'),
                                         ('Trinidad and Tobago'),
                                         ('Tunisia'),
                                         ('Turkey'),
                                         ('Turkmenistan'),
                                         ('Tuvalu'),
                                         ('Uganda'),
                                         ('Ukraine'),
                                         ('United Arab Emirates'),
                                         ('United Kingdom'),
                                         ('United States of America'),
                                         ('Uruguay'),
                                         ('Uzbekistan'),
                                         ('Vanuatu'),
                                         ('Venezuela'),
                                         ('Vietnam'),
                                         ('Yemen'),
                                         ('Zambia'),
                                         ('Zimbabwe');

-- Inserting diseases
INSERT INTO diseases (disease_name) VALUES
                                        ('Malaria'),  -- Disease ID 1
                                        ('Trypanosoma Cruzi Risk'),  -- Disease ID 2
                                        ('West Nile Virus Risk'),  -- Disease ID 3
                                        ('Dengue Virus'),  -- Disease ID 4
                                        ('Zika Virus'),  -- Disease ID 5
                                        ('Chikungunya'),  -- Disease ID 6
                                        ('Yellow Fever');  -- Disease ID 7






INSERT INTO country_disease (country_id, disease_id, note, start_date, end_date) VALUES

                                                               -- Malaria occurrences

                                                               (1,  1, 'Yes whole country below 2000m (May-Nov) until Sept 2015','1996-02-01','2015-09-30'),  -- Afghanistan
                                                               (3,  1, 'From March 1996 to May 2019: desert oasis of Ihrir (Illizi Department) only','1996-03-01','2019-05-30'),  -- Algeria
                                                               (5,  1, 'All year in the country','1974-11-01','2024-11-26'), -- Angola
                                                               (6,  1, 'Until February 1996, All year in rural areas','1996-02-01','2024-11-24'),  -- Argentina
                                                               (11, 1, 'Yes Great Exuma Island only Jul-Oct 2006 and Sept 2007- Sept 2015','2006-07-01','2015-09-30'),  -- Bahamas
                                                               (13, 1,'Yes all year whole country until Sept 2015','1976-09-01','2015-11-30'),  -- Bangladesh
                                                               (18, 1,'Risk exists throughout the year but especially from November to June in the northern parts of the country','1996-02-01','2024-11-26'),  -- Botswana
                                                               (19, 1, 'All year in Amazonia, urban areas (e.g., Manaus), Amazon cruises, rural areas near Iguaçu Falls.', '1996-02-01', '2024-11-26'), -- Brazil
                                                               (20, 1, 'All year round','1976-11-01','2024-11-26'), -- Brunei
                                                               (25, 1, 'All year in most of the country, except Phnom Penh, Siem Reap, and Angkor Wat.', '1996-02-01', '2024-11-26'), -- Cambodia
                                                               (26, 1, 'all year round', '1976-11-26', '2024-11-26'), -- Cameroon
                                                               (28, 1, 'all year in the whole country', '1976-11-26', '2024-11-26'), -- Central African Republic
                                                               (29, 1, 'all year in the whole country', '1976-11-26', '2024-11-26'), -- Chad
                                                               (31, 1, 'Was at previous risk in multiple provinces ', '1996-02-01', '2021-06-30'), -- China
                                                               (32, 1,'Yes all year whole country until Feb 2015','1985-02-01','2015-02-30'), -- Colombia
                                                               (35, 1,'All year in whole country','1976-11-26','2024-11-26'), -- Costa Rica
                                                               (41, 1,'All year in whole country','1976-11-26','2024-11-26'), -- Djibouti
                                                               (43, 1,'All year in whole country','1976-11-26','2024-11-26'), -- Dominican Republic)
                                                               (44, 1, 'All year in most of the country. Visitors to Guayaquil, Quito, and other cities in the inter-Andean region and the Galapagos Islands, accept.', '1976-11-26', '2024-11-26'), -- Ecuador
                                                               (45, 1,'During the months Jun-Oct, in El Faiyum region until May 2012','1996-02-001','2012-05-30'), -- Egypt
                                                               (46, 1,'All Year whole country up until September 2006','1976-09-01','2006-09-30'), -- El Salvador
                                                               (47, 1, 'all year in the whole country', '1976-11-26', '2024-11-26'), -- Equatorial Guinea
                                                               (48, 1, 'all year in the whole country, Only accept visitors from Asmara and the central highlands', '1976-11-26', '2024-11-26'), -- Eritrea
                                                               (50, 1,'All year in most of the country, Visitors to airport of Manzine and western half of country only accept','1976-11-26','2024-11-26'), -- Eswatini
                                                               (51, 1,'All year in whole country. Visitors to city of Addis Ababa only accept','1976-11-26','2024-11-26'), -- Ethiopia



                                                               -- Trypanosoma Cruzi Risk occurrences
                                                               (6,  2, 'N/A','1976-11-26','2024-11-26'),  -- Argentina
                                                               (11, 2, 'N/A','1976-11-26','2024-11-26'),  -- Bahamas
                                                               (19, 2, 'N/A','1976-11-26','2024-11-26'),  -- Brazil
                                                               (30, 2, 'N/A', '1976-11-26', '2024-11-26'), -- Chile
                                                               (32, 2, 'N/A', '1976-11-26', '2024-11-26'), -- Colombia
                                                               (46, 2, 'N/A','1976-11-26','2024-11-26'), -- El Salvador



                                                               -- West Nile Virus Risk occurrences
                                                               (2,  3, 'Donors who have visited between 1st May to 30th November must have the WNV Donor Selection Guideline applied.','2024-05-01','2024-11-30'), -- Albania
                                                               (3,  3, 'Donors who have visited between 1st May to 30th November must have the WNV Donor Selection Guideline applied.','2024-05-01','2024-11-30'), -- Algeria
                                                               (7,  3, 'Donors who have visited between 1st May to 30th November must have the WNV Donor Selection Guideline applied.','2024-05-01','2024-11-30'), -- Armenia
                                                               (9,  3, 'Donors who have visited between 1st May to 30th November must have the WNV Donor Selection Guideline applied.','2024-05-01','2024-11-30'), -- Austria
                                                               (17, 3, 'Donors who have visited between 1st May to 30th November must have the WNV Donor Selection Guideline applied.','2024-05-01','2024-11-30'), -- Bosnia and Herzegovina
                                                               (21, 3, 'Donors who have visited between 1st May to 30th November must have the WNV Donor Selection Guideline applied.','2024-05-01','2024-11-30'), -- Bulgaria
                                                               (27, 3, 'Donors who have visited between 1st May to 30th November must have the WNV Donor Selection Guideline applied.','2024-05-01','2024-11-30'), -- Canada
                                                               (54, 3, 'Donors who have visited between 1st May to 30th November must have the WNV Donor Selection Guideline applied.','2024-05-01','2024-11-30'), -- France




                                                               -- Dengue Virus occurrences
                                                               (1,  4, 'N/A', '1976-11-26','2024-11-26'), -- Afghanistan
                                                               (5,  4, 'N/A', '1976-11-26','2024-11-26'), -- Angola
                                                               (6,  4, 'N/A', '1976-11-26','2024-11-26'),  -- Argentina
                                                               (13, 4, 'N/A','1976-11-26','2024-11-26'),  -- Bangladesh
                                                               (14, 4, 'N/A','1976-11-26','2024-11-26'),  -- Barbados
                                                               (25, 4, 'N/A', '1976-11-26', '2024-11-26'), -- Cambodia
                                                               (29, 4, 'N/A', '1976-11-26', '2024-11-26'), -- Chad
                                                               (31, 4, 'N/A', '1976-11-26', '2024-11-26'), -- China
                                                               (32, 4, 'N/A', '1976-11-26', '2024-11-26'), -- Colombia
                                                               (35, 4, 'N/A', '1976-11-26', '2024-11-26'), -- Costa Rica
                                                               (41, 4, 'N/A', '1976-11-26', '2024-11-26'), -- Djibouti
                                                               (42, 4,'N/A','1976-11-26','2024-11-26'), -- Dominica
                                                               (43, 4,'N/A','1976-11-26','2024-11-26'), -- Dominican Republic
                                                               (45, 4, 'N/A', '1976-11-26', '2024-11-26'), -- Egypt
                                                               (46, 4, 'N/A','1976-11-26','2024-11-26'), -- El Salvador
                                                               (48, 4, 'N/A', '1976-11-26', '2024-11-26'), -- Eritrea
                                                               (50, 4,'N/A','1976-11-26','2024-11-26'), -- Eswatini
                                                               (51, 4,'N/A','1976-11-26','2024-11-26'), -- Ethiopia
                                                               (52, 4,'N/A','1976-11-26','2024-11-26'), -- Fiji
                                                               (54, 4, 'N/A', '1976-11-26', '2024-11-26'), -- France



                                                               -- Zika Virus occurrences
                                                               (5,  5, 'N/A', '1976-11-26','2024-11-26'), -- Angola
                                                               (6,  5, 'N/A', '1976-11-26','2024-11-26'),  -- Argentina
                                                               (11, 5, 'N/A','1976-11-26','2024-11-26'),  -- Bahamas
                                                               (13, 5, 'N/A','1976-11-26','2024-11-26'),  -- Bangladesh
                                                               (14, 5, 'N/A','1976-11-26','2024-11-26'),  -- Barbados
                                                               (20, 5, 'N/A','1976-11-26','2024-11-26'),  -- Brunei
                                                               (25, 5, 'N/A', '1976-11-26', '2024-11-26'), -- Cambodia
                                                               (26, 5, 'N/A', '1976-11-26', '2024-11-26'), -- Cameroon
                                                               (28, 5, 'N/A', '1976-11-26', '2024-11-26'), -- Central African Republic
                                                               (30, 5, 'N/A', '1976-11-26', '2024-11-26'), -- Chile
                                                               (32, 5, 'N/A', '1976-11-26', '2024-11-26'), -- Colombia
                                                               (35, 5, 'N/A', '1976-11-26', '2024-11-26'), -- Costa Rica
                                                               (42, 5,'N/A','1976-11-26','2024-11-26'), -- Dominica
                                                               (43, 5,'N/A','1976-11-26','2024-11-26'), -- Dominican Republic
                                                               (46, 5, 'N/A','1976-11-26','2024-11-26'), -- El Salvador
                                                               (50, 5,'N/A','1976-11-26','2024-11-26'), -- Eswatini
                                                               (52, 5,'N/A','1976-11-26','2024-11-26'), -- Fiji






                                                               -- Chikungunya occurrences
                                                               (6, 6, 'N/A', '1976-11-26','2024-11-26'),  -- Argentina
                                                               (13, 6, 'N/A','1976-11-26','2024-11-26'),  -- Bangladesh
                                                               (25, 6, 'N/A', '1976-11-26', '2024-11-26'), -- Cambodia
                                                               (26, 6, 'N/A', '1976-11-26', '2024-11-26'), -- Cameroon
                                                               (28, 6, 'N/A', '1976-11-26', '2024-11-26'), -- Central African Republic
                                                               (29, 6, 'N/A', '1976-11-26', '2024-11-26'), -- Chad
                                                               (31, 6, 'N/A', '1976-11-26', '2024-11-26'), -- China
                                                               (32, 6, 'N/A', '1976-11-26', '2024-11-26'), -- Colombia
                                                               (35, 6, 'N/A', '1976-11-26', '2024-11-26'), -- Costa Rica
                                                               (42, 6,'N/A','1976-11-26','2024-11-26'), -- Dominica
                                                               (43, 6,'N/A','1976-11-26','2024-11-26'), -- Dominican Republic
                                                               (46, 6, 'N/A','1976-11-26','2024-11-26'), -- El Salvador
                                                               (47, 6, 'N/A', '1976-11-26', '2024-11-26'), -- Equatorial Guinea
                                                               (50, 6,'N/A','1976-11-26','2024-11-26'), -- Eswatini
                                                               (51, 6,'N/A','1976-11-26','2024-11-26'), -- Ethiopia
                                                               (52, 6,'N/A','1976-11-26','2024-11-26'), -- Fiji





                                                               -- Yellow Fever occurrences
                                                                (5, 7, 'N/A', '1976-11-26','2024-11-26'), -- Angola
                                                                (6, 7, 'N/A', '1976-11-26','2024-11-26'),  -- Argentina
                                                                (26, 7, 'N/A', '1976-11-26', '2024-11-26'), -- Cameroon
                                                                (27, 7, 'N/A', '1976-11-26', '2024-11-26'), -- Central African Republic
                                                                (29, 7, 'N/A', '1976-11-26', '2024-11-26'), -- Chad
                                                                (32, 7, 'N/A', '1976-11-26', '2024-11-26'), -- Colombia
                                                                (47, 7, 'N/A', '1976-11-26', '2024-11-26'), -- Equatorial Guinea
                                                                (51, 7, 'N/A', '1976-11-26', '2024-11-26'); -- Ethiopia





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


INSERT INTO questions (question, no_risk_response) VALUES
('Have you travelled to a country within the last 12 months?', 'No'),
('Where have you travelled in the last 12 months?', '0'),
('When did you go to this country?', '0'),
('When did you return from this country?', '0'),
('Have you been in contact with anyone who has travelled to a country within the last 12 months?', 'No'),
('Have you lived  abroad for more than 6 months', 'No'),
('Where have you lived abroad?', '0'),
('When did you go abroad?', '0'),
('When did you return from abroad?', '0'),
('Have you been in contact with anyone who has lived abroad for more than 6 months?', 'No'),
('Have you travelled through multiple countries whilst travelling abroad?', 'No'),
('Which countries have you travelled through?', '0'),
('When did you go to these countries?', '0'),
('When did you return from these countries?', '0'),
('Have you been in contact with anyone who has travelled through multiple countries whilst travelling abroad?', 'No');


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
