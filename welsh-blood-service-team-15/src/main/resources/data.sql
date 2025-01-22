
-- Inserting donors (donor_id will be auto-incremented)
INSERT INTO donor (first_name, last_name, donor_dob, donor_decision, donor_note, decision_made_by) VALUES
                                                                                                 ('John', 'Doe', '1985-06-15','Eligible to donate today', 'No known health risks', 'Dr. Smith'),
                                                                                                 ('Jane', 'Smith', '1990-11-22','Eligible to donate but further testing needed', 'Recent travel to malaria-endemic areas','Nurse Johnson'),
                                                                                                 ('Mary', 'Johnson', '1978-04-09','Eligible to donate but must wait 6 months', 'Clear of any diseases','Dr. Smith'),
                                                                                                 ('Tom', 'Brown', '1980-01-01','Not Eligible to donate at all', 'No known health risks','Nurse Johnson'),
                                                                                                 ('Alice', 'Williams', '1995-03-30','Eligible to donate today', 'Recent travel to malaria-endemic areas','Dr. Smith'),
                                                                                                 ('Yu','Hwang','2000-07-26',NULL,NULL,NULL);
-- Inserting all countries
INSERT INTO countries (country_name) VALUES
                                         ('Afghanistan'),
                                         ('Albania'),
                                         ('Algeria'),
                                         ('Andorra'),
                                         ('Angola'),
                                         ('Argentina'),  -- Country ID 6
                                         ('Armenia'),
                                         ('Australia'),  -- Region Check needed when implemented, Country ID 8
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
                                         ('Burkina Faso'),  -- Country ID 22
                                         ('Burundi'),
                                         ('Cabo Verde'),
                                         ('Cambodia'), -- Country ID 25
                                         ('Cameroon'),
                                         ('Chad'),
                                         ('Chile'),
                                         ('China'),
                                         ('Clipperton Island'), -- Country ID 30
                                         ('Cocos Island'), -- Country ID 31
                                         ('Colombia'), -- Country ID 32
                                         ('Comoros'), -- Country ID 33
                                         ('Congo'), --  Country ID 34
                                         ('Costa Rica'),
                                         ('Croatia'), -- Country ID 36
                                         ('Cuba'), -- Country ID 37
                                         ('Cyprus'), -- Country ID 38
                                         ('Czechia (Czech Republic)'), -- Country ID 39
                                         ('Denmark'),
                                         ('Djibouti'),
                                         ('Dominica'),
                                         ('Dominican Republic'),
                                         ('Ecuador'),
                                         ('Egypt'), -- Country ID 45
                                         ('El Salvador'),
                                         ('Equatorial Guinea'),
                                         ('Eritrea'),
                                         ('Estonia'),
                                         ('Eswatini (fmr. "Swaziland")'),
                                         ('Ethiopia'), -- Country ID 51
                                         ('Fiji'),
                                         ('Finland'),
                                         ('France'),  -- Region Check needed when implemented, Country ID 54
                                         ('Gabon'), -- Country ID 55
                                         ('Gambia'),
                                         ('Georgia'),
                                         ('Germany'),
                                         ('Ghana'),
                                         ('Guatemala'),
                                         ('Guinea'),
                                         ('Guinea-Bissau'), -- Country ID 62
                                         ('Guyana'),  -- Country ID 63
                                         ('Guernsey'),
                                         ('Heard Island and McDonald Islands'),
                                         ('Haiti'),  -- Country ID 66
                                         ('Holy See'),  -- Country ID 67
                                         ('Honduras'),
                                         ('Hungary'),
                                         ('Iceland'),  -- Country ID 70
                                         ('India'),
                                         ('Indonesia'),
                                         ('Iran'),  -- Region check needed when implemented, Country ID 73
                                         ('Iraq'),  -- Region check needed when implemented, Country ID 74
                                         ('Ireland'),
                                         ('Italy'),  -- Region check needed when implemented, Country ID 76
                                         ('Jamaica'), -- Country ID 77
                                         ('Japan'),
                                         ('Jordan'),
                                         ('Kazakhstan'),
                                         ('Kenya'), -- Region check needed when implemented, Country ID 81
                                         ('Kiribati'),  -- Country ID 82
                                         ('Kosovo'),
                                         ('Kuwait'),
                                         ('Kyrgyzstan'),
                                         ('Laos'),  -- Region check needed when implemented, Country ID 86
                                         ('Latvia'),
                                         ('Lebanon'),
                                         ('Lesotho'),  -- Country ID 89
                                         ('Liberia'),
                                         ('Libya'), -- Region Check needed when implemented, Country ID 91
                                         ('Liechtenstein'),
                                         ('Lithuania'),
                                         ('Luxembourg'),
                                         ('Madagascar'),
                                         ('Malawi'),
                                         ('Malaysia'), -- Region check needed when implemented, Country ID 97
                                         ('Maldives'),
                                         ('Mali'),
                                         ('Malta'), -- Country ID 100
                                         ('Marshall Islands'),
                                         ('Martinique'),
                                         ('Mauritania'),
                                         ('Mauritius'),
                                         ('Mexico'),  -- Region Check needed when implemented, Country ID 105
                                         ('Moldova'),
                                         ('Monaco'),
                                         ('Mongolia'),
                                         ('Montenegro'),
                                         ('Morocco'),  -- Region Check needed when implemented, Country ID 110
                                         ('Mozambique'),
                                         ('Myanmar'),
                                         ('Namibia'),  -- Region Check needed when implemented, Country ID 113
                                         ('Nauru'), -- Country ID 114
                                         ('Nepal'),  -- Region Check needed when implemented, Country ID 115
                                         ('Netherlands'),
                                         ('New Zealand'),
                                         ('Nicaragua'),
                                         ('Niger'),
                                         ('Nigeria'),
                                         ('North Korea'),
                                         ('North Macedonia (formerly Macedonia)'),
                                         ('Norway'),
                                         ('Oman'),
                                         ('Pakistan'),
                                         ('Palau'),  -- Country ID 126
                                         ('Palestine'),
                                         ('Panama'),  -- Region Check needed when implemented, Country ID 128
                                         ('Papua New Guinea'),
                                         ('Paraguay'),  -- Region check needed when implemented, Country ID 130
                                         ('Peru'),  -- Region check needed when implemented, Country ID 131
                                         ('Philippines'), -- Region Check needed when implemented, Country ID 132
                                         ('Poland'),
                                         ('Portugal'),
                                         ('Qatar'),
                                         ('Romania'),
                                         ('Russia'),
                                         ('Rwanda'), -- Country ID 138
                                         ('Saint Kitts and Nevis'), -- Country ID 139
                                         ('Saint Lucia'),  -- Country ID 140
                                         ('Saint Vincent and the Grenadines'),  -- Country ID 141
                                         ('Samoa'), -- Country ID 142
                                         ('San Martin'),  -- Country ID 143
                                         ('Sao Tome and Principe'),  -- Country ID 144
                                         ('Saudi Arabia'), -- Country ID 145 + Region Check needed when implemented
                                         ('Senegal'),
                                         ('Serbia'),
                                         ('Seychelles'),
                                         ('Sierra Leone'),
                                         ('Singapore'), -- Country ID 150
                                         ('Slovakia'),
                                         ('Slovenia'),
                                         ('Solomon Islands'),
                                         ('Somalia'),
                                         ('South Africa'),  -- Region Check needed when implemented, Country ID 155
                                         ('South Korea'),
                                         ('South Sudan'), -- Country ID 157
                                         ('Spain'), -- Region Check needed when implemented, Country ID 158
                                         ('Sri Lanka'),
                                         ('Sudan'), -- Country ID 160
                                         ('Suriname'),  -- Region Checks needed when implemented, Country ID 161
                                         ('Sweden'),
                                         ('Switzerland'),
                                         ('Syria'),
                                         ('Tajikistan'),
                                         ('Tanzania'),
                                         ('Thailand'), -- Region Check needed when implemented, Country ID 167
                                         ('Timor-Leste'),  -- Country ID 168
                                         ('Togo'),
                                         ('Tonga'),
                                         ('Trinidad and Tobago'),
                                         ('Tunisia'),
                                         ('Turkey'),  -- Region Check needed when implemented, Country ID 173
                                         ('Turkmenistan'),  -- Region Check needed when implemented, Country ID 174
                                         ('Tuvalu'),
                                         ('Uganda'),
                                         ('Ukraine'),
                                         ('United Arab Emirates'), -- Country ID 178 + Region Check needed when implemented
                                         ('United Kingdom'),
                                         ('United States of America'), -- Region Check needed when implemented, Country ID 180
                                         ('Uruguay'),  -- Country ID 181
                                         ('Uzbekistan'),
                                         ('Vanuatu'),
                                         ('Venezuela'),
                                         ('Vietnam'),
                                         ('Yemen'),
                                         ('Zambia'),
                                         ('Zimbabwe'),
                                         ('Antigua and Barbuda'),  -- Country ID 189
                                         ('Belize'),  -- Country ID 190
                                         ('Benin');  -- Country ID 191

-- Original data split into multiple records for 3NF
INSERT INTO regions (region_name, country_id)
VALUES
    ('Ihrir (Illizi Department)', 3),
    ('Rural Areas', 6),
    ('Great Exuma Island', 11),
    ('Northen Parts', 18),
    ('Amazonia urban areas (eg Manaus)', 19),
    ('Phnom Penh', 25),
    ('Siem Reap', 25),
    ('Angkor Wat', 25),
    ('Multiple provinces', 31),
    ('Guayaquil', 44),
    ('Quito', 44),
    ('Other cities in the inter-Andean region', 44),
    ('Galapagos Islands', 44),
    ('El Faiyum region', 45),
    ('Airport of Manzine', 50),
    ('Western half of country', 50),
    ('South-East Of the Country', 57),
    ('Bali', 72),
    ('Java', 72),
    ('City of Jakarta', 72),
    ('All year in most of the country', 81),
    ('Vientiane', 86),
    ('Bokeo', 86),
    ('Houaphanh', 86),
    ('South-West of the country', 91),
    ('Central Peninsular Malaysia', 97),
    ('Taman Negara National Park', 97),
    ('Sabah', 97),
    ('Sarawak', 97),
    ('Widespread (Except Cancun)', 105),
    ('Al Hoceima', 110),
    ('Taounate', 110),
    ('Taza', 110),
    ('Northern regions', 113),
    ('Managua', 118),
    ('De-militarized zone between North and South Korea (Kyunggi-do) 1 ', 121),
    ('De-militarized zone between North and South Korea (Gangwon-do) 1', 121),
    ('All year in the country up until February 1996', 131),
    ('De-militarized zone between North and South Korea (Kyunggi-do) 2 ', 156),
    ('De-militarized zone between North and South Korea (Gangwon-do) 2', 156),
    ('South Eastern border regions', 174),
    ('East of country', 178),
    ('Abu Dhabi', 178),
    ('Dubai', 178),
    ('Sharjah', 178),
    ('Ajman', 178),
    ('Hawaii', 180),
    ('US Virgin Islands (St Choix)', 180),
    ('US Virgin Islands (St John)', 180),
    ('US Virgin Islands (St Thomas)', 180),
    ('Ihrir TEST 2 (Illizi Department)', 3);


-- Inserting diseases
INSERT INTO diseases (disease_name) VALUES
                                        ('Malaria'),  -- Disease ID 1
                                        ('Trypanosoma Cruzi Risk'),  -- Disease ID 2
                                        ('West Nile Virus Risk'),  -- Disease ID 3
                                        ('Dengue Virus'),  -- Disease ID 4
                                        ('Zika Virus'),  -- Disease ID 5
                                        ('Chikungunya'),  -- Disease ID 6
                                        ('Yellow Fever');  -- Disease ID 7

-- Inserting the possible deferral periods
INSERT INTO deferral_period (period_days) VALUES
                                              (0),
                                              (28),
                                              (120),
                                              (150);


INSERT INTO country_disease (country_id, region_id, disease_id, note, start_date, end_date) VALUES
                                                               (1, null, 1, 'Yes whole country below 2000m (May-Nov) until Sept 2015','1996-02-01','2015-09-30'),  -- Afghanistan
                                                               (3, 1, 1, 'From March 1996 to May 2019: desert oasis of Ihrir (Illizi Department) only','1996-03-01','2019-05-30'),  -- Algeria
                                                               (5, null, 1, 'All year in the country','1974-11-01','2024-11-26'), -- Angola
                                                               (6, 2, 1, 'Until February 1996, All year in rural areas','1996-02-01','2024-11-24'),  -- Argentina
                                                               (11, 3, 1, 'Yes Great Exuma Island only Jul-Oct 2006 and Sept 2007- Sept 2015','2006-07-01','2015-09-30'),  -- Bahamas
                                                               (13, null, 1,'Yes all year whole country until Sept 2015','1976-09-01','2015-11-30'),  -- Bangladesh
                                                               (18, 4, 1,'Risk exists throughout the year but especially from November to June in the northern parts of the country','1996-02-01','2024-11-26'),  -- Botswana
                                                               (19, 5, 1, 'All year in Amazonia, urban areas (e.g., Manaus), Amazon cruises, rural areas near Iguaçu Falls.', '1996-02-01', '2024-11-26'), -- Brazil
                                                               (20, null, 1, 'All year round','1976-11-01','2024-11-26'), -- Brunei
                                                               (25, 6, 1, 'All year in most of the country, except Phnom Penh, Siem Reap, and Angkor Wat.', '2024-05-01', '2024-11-26'), -- Cambodia
                                                               (26, null, 1, 'all year round', '1976-11-26', '2024-11-26'), -- Cameroon
                                                               (28, null, 1, 'all year in the whole country', '1976-11-26', '2024-11-26'), -- Central African Republic
                                                               (29, null, 1, 'all year in the whole country', '1976-11-26', '2024-11-26'), -- Chad
                                                               (31, null, 1, 'Was at previous risk in multiple provinces ', '1996-02-01', '2021-06-30'), -- China
                                                               (32, null, 1,'Yes all year whole country until Feb 2015','2015-01-01','2015-02-28'), -- Colombia
                                                               (35, null, 1,'All year in whole country','1976-11-26','2024-11-26'), -- Costa Rica
                                                               (41, null, 1,'All year in whole country','1976-11-26','2024-11-26'), -- Djibouti
                                                               (43, null, 1,'All year in whole country','1976-11-26','2024-11-26'), -- Dominican Republic
                                                               (44, 10, 1, 'All year in most of the country. Visitors to Guayaquil, Quito, and other cities in the inter-Andean region and the Galapagos Islands, accept.', '1976-11-26', '2024-11-26'), -- Ecuador
                                                               (44, 11, 1, 'All year in most of the country. Visitors to Guayaquil, Quito, and other cities in the inter-Andean region and the Galapagos Islands, accept.', '1976-11-26', '2024-11-26'), -- Ecuador
                                                               (44, 12, 1, 'All year in most of the country. Visitors to Guayaquil, Quito, and other cities in the inter-Andean region and the Galapagos Islands, accept.', '1976-11-26', '2024-11-26'), -- Ecuador
                                                               (44, 13, 1, 'All year in most of the country. Visitors to Guayaquil, Quito, and other cities in the inter-Andean region and the Galapagos Islands, accept.', '1976-11-26', '2024-11-26'), -- Ecuador

                                                               (45, 14, 1,'During the months Jun-Oct, in El Faiyum region until May 2012','1996-02-001','2012-05-30'), -- Egypt
                                                               (46, null, 1,'All Year whole country up until September 2006','2006-01-01','2006-09-30'), -- El Salvador
                                                               (47, null, 1, 'all year in the whole country', '1976-11-26', '2024-11-26'), -- Equatorial Guinea
                                                               (48, null, 1, 'all year in the whole country, Only accept visitors from Asmara and the central highlands', '1976-11-26', '2024-11-26'), -- Eritrea
                                                               (50, 15, 1,'All year in most of the country, Visitors to airport of Manzine and western half of country only accept','1976-11-26','2024-11-26'), -- Eswatini
                                                               (50, 16, 1,'All year in most of the country, Visitors to airport of Manzine and western half of country only accept','1976-11-26','2024-11-26'), -- Eswatini


                                                               (51, null, 1,'All year in whole country. Visitors to city of Addis Ababa only accept','1976-11-26','2024-11-26'), -- Ethiopia
                                                               (55, null, 1,'All year in the whole country','1976-11-26', '2024-11-26'), -- Gabon
                                                               (56, null, 1,'All year in the whole country','1976-11-26', '2024-11-26'), -- Gambia
                                                               (57, 17, 1,'June - October in the South-East Of the Country','2024-06-01','2024-10-30'), -- Georgia
                                                               (59, null, 1,'All year in the whole country','1976-11-26', '2024-11-26'), -- Ghana
                                                               (62, null, 1, 'All year in the whole country up until December 2011','2011-01-01','2011-12-31'), -- Guatemala
                                                               (63, null, 1,'All year in the whole country','1976-11-26', '2024-11-26'), -- Guinea
                                                               (66, null, 1,'All year in the whole country','1976-11-26', '2024-11-26'), -- Haiti
                                                               -- TEST
                                                               (68, null, 1, 'All year in the whole country','2024-10-26', '2024-11-26'), -- Iceland
                                                               (71, null, 1, 'All year in the whole country until May 2012','2012-01-01','2012-05-30'), -- India
                                                               (72, 18, 1, 'Only Accept visitors of the islands of Bali, Java and the city of Jakarta','1976-11-26', '2024-11-26'), -- Indonesia
                                                               (72, 19, 1, 'Only Accept visitors of the islands of Bali, Java and the city of Jakarta','1976-11-26', '2024-11-26'), -- Indonesia
                                                               (72, 20, 1, 'Only Accept visitors of the islands of Bali, Java and the city of Jakarta','1976-11-26', '2024-11-26'), -- Indonesia

                                                               (73, NULL, 1, 'All year in the whole country up until February 1996','1976-02-01','1996-02-28'), -- Iran
                                                               (74, NULL, 1, 'All year in the whole country up until February 1996','1976-02-01','1996-02-28'), -- Iraq
                                                               (77, NULL, 1, 'In the whole country during Dec 2006 - May 2012','2006-12-01','2012-05-30'), -- Jamaica
                                                               (81, NULL, 1, 'All year in most of the country','1976-11-01','2024-11-26'), -- Kenya
                                                               (85, NULL, 1, 'All year in the whole country up until December 2016','2016-01-01','2016-12-31'), -- Kyrgyzstan
                                                               (86, 22, 1, 'All year in most of the country, Visitors to Vientiane, Bokeo, Houaphanh only accept','1976-11-26','2024-11-26'), -- Laos
                                                               (86, 23, 1, 'All year in most of the country, Visitors to Vientiane, Bokeo, Houaphanh only accept','1976-11-26','2024-11-26'), -- Laos
                                                               (86, 24, 1, 'All year in most of the country, Visitors to Vientiane, Bokeo, Houaphanh only accept','1976-11-26','2024-11-26'), -- Laos
                                                               (90, NULL, 1, 'All year in the whole country','1976-11-26','2024-11-26'), -- Liberia
                                                               (91, 25, 1, 'South-West of the country during February 1996 up until September 2006','1996-02-01','2006-09-30'), -- Libya,
                                                               (95, NULL, 1, 'All year in the whole country','1976-11-26','2024-11-26'), -- Madagascar
                                                               (96, NULL, 1, 'All year in the whole country','1976-11-26','2024-11-26'), -- Malawi
                                                               (97, 26, 1, 'All year in central Peninsular Malaysia and the Taman Negara National Park and all provinces of Sabah and Sarawak (on the island of Borneo)','1976-11-26','2024-11-26'), -- Malaysia
                                                               (97, 27, 1, 'All year in central Peninsular Malaysia and the Taman Negara National Park and all provinces of Sabah and Sarawak (on the island of Borneo)','1976-11-26','2024-11-26'), -- Malaysia
                                                               (97, 28, 1, 'All year in central Peninsular Malaysia and the Taman Negara National Park and all provinces of Sabah and Sarawak (on the island of Borneo)','1976-11-26','2024-11-26'), -- Malaysia
                                                               (97, 29, 1, 'All year in central Peninsular Malaysia and the Taman Negara National Park and all provinces of Sabah and Sarawak (on the island of Borneo)','1976-11-26','2024-11-26'), -- Malaysia
                                                               (98, NULL, 1, 'All years in whole country up until October 2009','1976-10-01', '2009-10-31'), -- Maldives
                                                               (99, NULL, 1, 'All year in the whole country','1976-11-26','2024-11-26'); -- Mali
INSERT INTO country_disease (country_id, region_id, disease_id, note, start_date, end_date)
VALUES
    (103, NULL, 1, 'All year in the whole country', '1976-11-26', '2024-11-26'), -- Mauritania
    (104, NULL, 1, 'All year in the whole country up until September 2006', '2006-01-01', '2006-09-30'), -- Mauritius
    (105, NULL, 1, 'Yes widespread (Except Cancun) up until December 2011', '2011-01-01', '2011-12-31'), -- Mexico
    (110, 31, 1, 'Yes May-Oct in provinces of Al Hoceima, Taounate, and Taza until Dec 2011', '2011-05-01', '2011-12-31'), -- Morocco
    (110, 32, 1, 'Yes May-Oct in provinces of Al Hoceima, Taounate, and Taza until Dec 2011', '2011-05-01', '2011-12-31'), -- Morocco
    (110, 33, 1, 'Yes May-Oct in provinces of Al Hoceima, Taounate, and Taza until Dec 2011', '2011-05-01', '2011-12-31'), -- Morocco

    (111, NULL, 1, 'All year in the whole country', '1976-11-26', '2024-11-26'), -- Mozambique
    (112, NULL, 1, 'All year in the whole country', '1976-11-26', '2024-11-26'), -- Myanmar
    (113, 34, 1, 'Yes all year northern regions until May 2015', '2015-01-01', '2015-05-31'), -- Namibia
    (115, NULL, 1, 'Yes all year whole country until Sept 2006', '2006-01-01', '2006-09-30'), -- Nepal
    (118, NULL, 1, 'All year in most of the country, visitors to Managua only excepted', '1976-11-26', '2024-11-26'), -- Nicaragua

    (119, NULL, 1, 'All year in the whole country', '1976-11-26', '2024-11-26'), -- Niger
    (120, NULL, 1, 'All year in the whole country', '1976-11-26', '2024-11-26'), -- Nigeria
    (121, 36, 1, 'All year in the de-militarized zone between North and South Korea (Kyunggi-do and Gangwon-do provinces)', '1976-11-26', '2024-11-26'), -- North Korea
    (121, 37, 1, 'All year in the de-militarized zone between North and South Korea (Kyunggi-do and Gangwon-do provinces)', '1976-11-26', '2024-11-26'), -- North Korea

    (124, NULL, 1, 'All year in the whole country up until September 2006', '2006-01-01', '2006-09-30'), -- Oman
    (125, NULL, 1, 'All year in the whole country', '1976-11-26', '2024-11-26'), -- Pakistan
    (128, NULL, 1, 'Yes all year whole country until Dec 2011', '2011-01-01', '2011-12-31'), -- Panama
    (129, NULL, 1, 'All year in the whole country', '1976-11-26', '2024-11-26'), -- Papua New Guinea
    (130, NULL, 1, 'All year in the whole country up until February 1996', '1996-02-01', '1996-02-28'), -- Paraguay
    (131, NULL, 1, 'All year in the country up until February 1996', '1996-02-01', '1996-02-28'), -- Peru
    (132, NULL, 1, 'All year in the whole country up until February 1996', '1996-02-01', '1996-02-28'), -- Philippines
    (138, NULL, 1, 'All year in the whole country', '1976-11-26', '2024-11-26'), -- Rwanda
    (145, NULL, 1, 'Yes all year whole country (except urban Jeddah, Mecca, Medina and Taif) until Sept 2006', '2006-01-01', '2006-09-30'), -- Saudi Arabia
    (146, NULL, 1, 'All year in the whole country', '1976-11-26', '2024-11-26'), -- Senegal
    (149, NULL, 1, 'All year in the whole country', '1976-11-26', '2024-11-26'), -- Sierra Leone
    (153, NULL, 1, 'All year in the whole country', '1976-11-26', '2024-11-26'), -- Solomon Islands
    (154, NULL, 1, 'All year in the whole country', '1976-11-26', '2024-11-26'), -- Somalia
    (155, NULL, 1, 'All year in the whole country up until February 1996', '1996-02-01', '1996-02-28'), -- South Africa
    (156, 39, 1, 'All year in the de-militarized zone between North and South Korea (Kyunggi-do and Gangwon-do provinces)', '1976-11-26', '2024-11-26'), -- South Korea
    (156, 40, 1, 'All year in the de-militarized zone between North and South Korea (Kyunggi-do and Gangwon-do provinces)', '1976-11-26', '2024-11-26'), -- South Korea

    (157, NULL, 1, 'All year in the whole country', '1976-11-26', '2024-11-26'), -- South Sudan
    (159, NULL, 1, 'Yes all year whole country until Dec 2011', '2011-01-01', '2011-12-31'), -- Sri Lanka
    (160, NULL, 1, 'All year in the whole country', '1976-11-26', '2024-11-26'), -- Sudan
    (161, NULL, 1, 'All year in the whole country up until December 2011', '2011-01-01', '2011-12-31'), -- Suriname
    (164, NULL, 1, 'Must be considered to be all year in whole country in view of current situation.', '1976-11-26', '2024-11-26'), -- Syria
    (166, NULL, 1, 'All year in the whole country', '1976-11-26', '2024-11-26'), -- Tanzania
    (167, NULL, 1, 'All year in the whole country up until May 2012', '2012-01-01', '2012-05-30'), -- Thailand
    (169, NULL, 1, 'All year in the whole country', '1976-11-26', '2024-11-26'), -- Togo
    (173, NULL, 1, 'Yes all year whole country until Feb 1996', '1996-02-01', '1996-02-28'), -- Turkey
    (174, 41, 1, 'Yes Jun – Oct in South Eastern border regions until Sept 2015', '2015-06-01', '2015-09-30'), -- Turkmenistan
    (176, NULL, 1, 'All year in the whole country', '1976-11-26', '2024-11-26'), -- Uganda
    (178, 43, 1, 'Yes in East of country until Dec 2011 with no risk in the cities of Abu Dhabi, Dubai, Sharjah, Ajman and Umm al Qaiwain', '1976-11-26', '2024-11-26'), -- United Arab Emirates
    (183, NULL, 1, 'All year in the whole country', '1976-11-26', '2024-11-26'), -- Vanuatu
    (184, NULL, 1, 'All year in the whole country', '1976-11-26', '2024-11-26'), -- Venezuela
    (185, NULL, 1, 'Yes all year whole country until May 2012', '2012-01-01', '2012-05-30'), -- Vietnam
    (186, NULL, 1, 'Yes all year whole country until May 2012', '2012-01-01', '2012-05-30'); -- Yemen


INSERT INTO country_disease (country_id, region_id, disease_id, note, start_date, end_date) VALUES
                                                                -- Trypanosoma Cruzi Risk occurrences
                                                                (6, null, 2, 'N/A', '1976-11-26', '2024-11-26'), -- Argentina
                                                                (11, null, 2, 'N/A', '1976-11-26', '2024-11-26'), -- Bahamas
                                                                (19, null, 2, 'N/A', '1976-11-26', '2024-11-26'), -- Brazil
                                                                (30, null, 2, 'N/A', '1976-11-26', '2024-11-26'), -- Chile
                                                                (32, null, 2, 'N/A', '1976-11-26', '2024-11-26'), -- Colombia
                                                                (46, null, 2, 'N/A', '1976-11-26','2024-11-26'), -- El Salvador
                                                                (68, null, 2, 'N/A', '1976-11-26', '2024-11-26'), -- Honduras
                                                                (118, null, 2, 'N/A', '1976-11-26', '2024-11-26'), -- Nicaragua
                                                                (130, null, 2, 'N/A', '1976-11-26', '2024-11-26'), -- Paraguay
                                                                (131, null, 2, 'N/A', '1976-11-26', '2024-11-26'), -- Peru
                                                                (172, null, 2, 'N/A', '1976-11-26', '2024-11-26'), -- Tunisia
                                                                (181, null, 2, 'N/A', '1976-11-26', '2024-11-26'); -- Uruguay

INSERT INTO country_disease (country_id, region_id, disease_id, note, start_date, end_date) VALUES
                                                                -- West Nile Virus Risk occurrences

                                                                (2, null, 3,'Donors who have visited between 1st May to 30th November must have the WNV Donor Selection Guideline applied.','2024-05-01', '2024-11-30'), -- Albania
                                                                (3, null, 3,'Donors who have visited between 1st May to 30th November must have the WNV Donor Selection Guideline applied.','2024-05-01', '2024-11-30'), -- Algeria
                                                                (7, null, 3,'Donors who have visited between 1st May to 30th November must have the WNV Donor Selection Guideline applied.','2024-05-01', '2024-11-30'), -- Armenia
                                                                (9, null, 3,'Donors who have visited between 1st May to 30th November must have the WNV Donor Selection Guideline applied.','2024-05-01', '2024-11-30'), -- Austria
                                                                (17, null, 3,'Donors who have visited between 1st May to 30th November must have the WNV Donor Selection Guideline applied.','2024-05-01', '2024-11-30'), -- Bosnia and Herzegovina
                                                                (21, null, 3,'Donors who have visited between 1st May to 30th November must have the WNV Donor Selection Guideline applied.','2024-05-01', '2024-11-30'), -- Bulgaria
                                                                (27, null, 3,'Donors who have visited between 1st May to 30th November must have the WNV Donor Selection Guideline applied.','2024-05-01', '2024-11-30'), -- Canada
                                                                (54, null, 3,'Donors who have visited between 1st May to 30th November must have the WNV Donor Selection Guideline applied.','2024-05-01', '2024-11-30'), -- France
                                                                (58, null, 3,'Donors who have visited between 1st May to 30th November must have the WNV Donor Selection Guideline applied.','2024-05-01', '2024-11-30'), -- Germany
                                                                (60, null, 3,'Donors who have visited between 1st May to 30th November must have the WNV Donor Selection Guideline applied.','2024-05-01', '2024-11-30'), -- Greece
                                                                (69, null, 3,'Donors who have visited between 1st May to 30th November must have the WNV Donor Selection Guideline applied.','2024-05-01', '2024-11-30'), -- Hungary
                                                                (76, null, 3,'Donors who have visited between 1st May to 30th November must have the WNV Donor Selection Guideline applied.', '2024-05-01', '2024-11-30'), -- Italy
                                                                (83, null, 3,'Donors who have visited between 1st May to 30th November must have the WNV Donor Selection Guideline applied.','2024-05-01', '2024-11-30'), -- Italy
                                                                (107, null, 3,'N/A','1976-11-26','2024-11-26'), -- Monaco
                                                                (109, null, 3,'Donors who have visited between 1st May to 30th November must have the WNV Donor Selection Guideline applied.','2024-05-01', '2024-11-30'), -- Montenegro
                                                                (122, null, 3,'Donors who have visited between 1st May to 30th November must have the WNV Donor Selection Guideline applied.','2024-05-01', '2024-11-30'), -- North Macedonia (formerly Macedonia)
                                                                (136, null, 3,'Donors who have visited between 1st May to 30th November must have the WNV Donor Selection Guideline applied.','2024-05-01', '2024-11-30'), -- Romania
                                                                (137, null, 3,'Donors who have visited between 1st May to 30th November must have the WNV Donor Selection Guideline applied.','2024-05-01', '2024-11-30'), -- Russia
                                                                (147, null, 3,'Donors who have visited between 1st May to 30th November must have the WNV Donor Selection Guideline applied.','2024-05-01', '2024-11-30'), -- Serbia
                                                                (152, null, 3,'Donors who have visited between 1st May to 30th November must have the WNV Donor Selection Guideline applied.','2024-05-01', '2024-11-30'), -- Slovenia
                                                                (158, null, 3,'Donors who have visited between 1st May to 30th November must have the WNV Donor Selection Guideline applied.','2024-05-01', '2024-11-30'), -- Spain
                                                                (173, null, 3,'Donors who have visited between 1st May to 30th November must have the WNV Donor Selection Guideline applied.','2024-05-01', '2024-11-30'), -- Turkey
                                                                (177, null, 3,'Donors who have visited between 1st May to 30th November must have the WNV Donor Selection Guideline applied.','2024-05-01', '2024-11-30'), -- Ukraine
                                                                (180, null, 3,'Donors who have visited between 1st May to 30th November must have the WNV Donor Selection Guideline applied.','2024-05-01', '2024-11-30'); -- United States of America

INSERT INTO country_disease (country_id, region_id,  disease_id, note, start_date, end_date) VALUES
        -- Dengue Virus occurrences
        (1, NULL, 4, 'N/A', '1976-11-26','2024-11-26'), -- Afghanistan
        (5, NULL, 4, 'N/A', '1976-11-26', '2024-11-26'), -- Angola
        (6, NULL, 4, 'N/A', '1976-11-26', '2024-11-26'), -- Argentina
        (13, NULL, 4, 'N/A', '1976-11-26','2024-11-26'), -- Bangladesh
        (14, NULL, 4, 'N/A', '1976-11-26', '2024-11-26'), -- Barbados
        (25, NULL, 4, 'N/A', '1976-11-26', '2024-11-26'), -- Cambodia
        (29, NULL, 4, 'N/A', '1976-11-26', '2024-11-26'), -- Chad
        (31, NULL, 4, 'N/A', '1976-11-26', '2024-11-26'), -- China
        (32, NULL, 4, 'N/A', '1976-11-26', '2024-11-26'), -- Colombia
        (35, NULL, 4, 'N/A', '1976-11-26','2024-11-26'), -- Costa Rica
        (41, NULL, 4, 'N/A', '1976-11-26', '2024-11-26'), -- Djibouti
        (42, NULL, 4, 'N/A', '1976-11-26', '2024-11-26'), -- Dominica
        (43, NULL, 4, 'N/A', '1976-11-26','2024-11-26'), -- Dominican Republic
        (45, NULL, 4, 'N/A', '1976-11-26', '2024-11-26'), -- Egypt
        (46, NULL, 4, 'N/A', '1976-11-26','2024-11-26'), -- El Salvador
        (48, NULL, 4, 'N/A', '1976-11-26', '2024-11-26'), -- Eritrea
        (50, NULL, 4, 'N/A', '1976-11-26', '2024-11-26'), -- Eswatini
        (51, NULL, 4, 'N/A', '1976-11-26', '2024-11-26'), -- Ethiopia
        (52, NULL, 4, 'N/A', '1976-11-26', '2024-11-26'), -- Fiji
        (54, NULL, 4, 'N/A', '1976-11-26', '2024-11-26'), -- France
        (55, NULL, 4, 'N/A', '1976-11-26', '2024-11-26'), -- Gabon
        (56, NULL, 4, 'N/A', '1976-11-26', '2024-11-26'), -- Gambia
        (59, NULL, 4, 'N/A', '1976-11-26', '2024-11-26'), -- Ghana
        (61, NULL, 4, 'N/A', '1976-11-26', '2024-11-26'), -- Grenada
        (62, NULL, 4, 'N/A', '1976-11-26', '2024-11-26'), -- Guatemala
        (63, NULL, 4, 'N/A', '1976-11-26', '2024-11-26'), -- Guinea
        (68, NULL, 4, 'N/A', '1976-11-26', '2024-11-26'), -- Honduras
        (71, NULL, 4, 'N/A', '1976-11-26', '2024-11-26'), -- India
        (72, NULL, 4, 'N/A', '1976-11-26', '2024-11-26'), -- Indonesia
        (73, NULL, 4, 'N/A', '1976-11-26', '2024-11-26'), -- Iran
        (76, NULL, 4, 'N/A', '1976-11-26', '2024-11-26'), -- Italy
        (77, NULL, 4, 'N/A', '1976-11-26', '2024-11-26'), -- Jamaica
        (81, NULL, 4, 'N/A', '1976-11-26', '2024-11-26'), -- Kenya
        (82, NULL, 4, 'N/A', '1976-11-26', '2024-11-26'), -- Kiribati
        (86, NULL, 4, 'N/A', '1976-11-26', '2024-11-26'), -- Laos
        (97, NULL, 4, 'N/A', '1976-11-26', '2024-11-26'), -- Malaysia
        (98, NULL, 4, 'N/A', '1976-11-26', '2024-11-26'), -- Maldives
        (99, NULL, 4, 'N/A', '1976-11-26', '2024-11-26'), -- Mali
        (101, NULL, 4, 'N/A','1976-11-26','2024-11-26'), -- Marshall Islands
        (102, NULL, 4, 'N/A','1976-11-26','2024-11-26'), -- Martinique
        (103, NULL, 4, 'N/A','1976-11-26','2024-11-26'), -- Mauritania
        (104, NULL, 4, 'N/A','1976-11-26','2024-11-26'), -- Mauritius
        (105, NULL, 4, 'N/A','1976-11-26','2024-11-26'), -- Mexico
        (107, NULL, 4, 'N/A','1976-11-26','2024-11-26'), -- Monaco
        (110, NULL, 4, 'N/A','1976-11-26','2024-11-26'), -- Mozambique
        (111, NULL, 4, 'N/A','1976-11-26','2024-11-26'), -- Myanmar
        (115, NULL, 4, 'N/A','1976-11-26','2024-11-26'), -- Nepal
        (118, NULL, 4, 'N/A','1976-11-26','2024-11-26'), -- Nicaragua
        (119, NULL, 4, 'N/A','1976-11-26','2024-11-26'), -- Niger
        (120, NULL, 4, 'N/A','1976-11-26','2024-11-26'), -- Nigeria
        (124, NULL, 4, 'N/A','1976-11-26','2024-11-26'), -- Oman
        (125, NULL, 4, 'N/A','1976-11-26','2024-11-26'), -- Pakistan
        (128, NULL, 4, 'N/A','1976-11-26','2024-11-26'), -- Panama
        (130, NULL, 4, 'N/A','1976-11-26','2024-11-26'), -- Paraguay
        (131, NULL, 4, 'N/A','1976-11-26','2024-11-26'), -- Peru
        (132, NULL, 4, 'N/A','1976-11-26','2024-11-26'), -- Philippines
        (138, NULL, 4, 'N/A','1976-11-26','2024-11-26'), -- Rwanda
        (140, NULL, 4, 'N/A','1976-11-26','2024-11-26'), -- Saint Lucia
        (145, NULL, 4,'N/A','1976-11-26','2024-11-26'), -- Saudi Arabia
        (146, NULL, 4, 'N/A','1976-11-26','2024-11-26'), -- Senegal
        (148, NULL, 4, 'N/A','1976-11-26','2024-11-26'), -- Seychelles
        (150, NULL, 4, 'N/A','1976-11-26','2024-11-26'), -- Singapore
        (153, NULL, 4, 'N/A','1976-11-26','2024-11-26'), -- Solomon Islands
        (154, NULL, 4, 'N/A','1976-11-26','2024-11-26'), -- Somalia
        (159, NULL, 4, 'N/A','1976-11-26','2024-11-26'), -- Sri Lanka
        (160, NULL, 4, 'N/A','1976-11-26','2024-11-26'), -- Sudan
        (166, NULL, 4, 'N/A','1976-11-26','2024-11-26'), -- Tanzania
        (169, NULL, 4, 'N/A','1976-11-26','2024-11-26'), -- Togo
        (170, NULL, 4, 'N/A','1976-11-26','2024-11-26'), -- Tonga
        (171, NULL, 4, 'N/A','1976-11-26','2024-11-26'), -- Trinidad and Tobago
        (178, NULL, 4, 'N/A','1976-11-26','2024-11-26'), -- United Arab Emirates
        (180, 47, 4, 'In Hawaii Only','1976-11-26','2024-11-26'), -- United States of America
        (181, NULL, 4, 'N/A','1976-11-26','2024-11-26'), -- Uruguay
        (183, NULL, 4, 'N/A','1976-11-26','2024-11-26'), -- Vanuatu
        (184, NULL, 4, 'N/A','1976-11-26','2024-11-26'), -- Venezuela
        (185, NULL, 4, 'N/A','1976-11-26','2024-11-26'), -- Vietnam
        (186, NULL, 4, 'N/A','1976-11-26','2024-11-26'), -- Yemen
        (187, NULL, 4, 'N/A','1976-11-26','2024-11-26'); -- Zambia

INSERT INTO country_disease (country_id, region_id, disease_id, note, start_date, end_date) VALUES
        -- Zika Virus occurrences
        (5,  NULL, 5, 'N/A', '1976-11-26','2024-11-26'), -- Angola
        (6,  NULL, 5, 'N/A', '1976-11-26','2024-11-26'),  -- Argentina
        (11, NULL, 5, 'N/A','1976-11-26','2024-11-26'),  -- Bahamas
        (13, NULL, 5, 'N/A','1976-11-26','2024-11-26'),  -- Bangladesh
        (14, NULL, 5, 'N/A','1976-11-26','2024-11-26'),  -- Barbados
        (20, NULL, 5, 'N/A','1976-11-26','2024-11-26'),  -- Brunei
        (25, NULL, 5, 'N/A', '1976-11-26', '2024-11-26'), -- Cambodia
        (26, NULL, 5, 'N/A', '1976-11-26', '2024-11-26'), -- Cameroon
        (28, NULL, 5, 'N/A', '1976-11-26', '2024-11-26'), -- Central African Republic
        (30, NULL, 5, 'N/A', '1976-11-26', '2024-11-26'), -- Chile
        (32, NULL, 5, 'N/A', '1976-11-26', '2024-11-26'), -- Colombia
        (35, NULL, 5, 'N/A', '1976-11-26', '2024-11-26'), -- Costa Rica
        (42, NULL, 5, 'N/A','1976-11-26','2024-11-26'), -- Dominica
        (43, NULL, 5, 'N/A','1976-11-26','2024-11-26'), -- Dominican Republic
        (46, NULL, 5, 'N/A','1976-11-26','2024-11-26'), -- El Salvador
        (50, NULL, 5, 'N/A','1976-11-26','2024-11-26'), -- Eswatini
        (52, NULL, 5, 'N/A','1976-11-26','2024-11-26'), -- Fiji
        (55, NULL, 5, 'N/A','1976-11-26', '2024-11-26'), -- Gabon
        (61, NULL, 5, 'N/A','1976-11-26', '2024-11-26'), -- Grenada
        (62, NULL, 5, 'N/A','1976-11-26', '2024-11-26'), -- Guatemala
        (66, NULL, 5, 'N/A','1976-11-26', '2024-11-26'), -- Haiti
        (68, NULL, 5, 'N/A','1976-11-26', '2024-11-26'), -- Honduras
        (71, NULL, 5, 'N/A','1976-11-26', '2024-11-26'), -- India
        (72, NULL, 5, 'N/A','1976-11-26', '2024-11-26'), -- Indonesia
        (77, NULL, 5, 'N/A','1976-11-26', '2024-11-26'), -- Jamaica
        (81, NULL, 5, 'N/A','1976-11-26', '2024-11-26'), -- Kenya
        (86, NULL, 5,'N/A','1976-11-26', '2024-11-26'), -- Laos
        (97, NULL, 5,'N/A','1976-11-26', '2024-11-26'), -- Malaysia
        (98, NULL, 5,'N/A','1976-11-26', '2024-11-26'), -- Maldives
        (99, NULL, 5,'N/A','1976-11-26', '2024-11-26'), -- Mali
        (101, NULL, 5, 'N/A','1976-11-26','2024-11-26'), -- Marshall Islands
        (102, NULL, 5, 'N/A','1976-11-26','2024-11-26'), -- Martinique
        (105, NULL, 5, 'N/A','1976-11-26','2024-11-26'), -- Mexico
        (118, NULL, 5, 'N/A','1976-11-26','2024-11-26'), -- Nicaragua
        (120, NULL, 5, 'N/A','1976-11-26','2024-11-26'), -- Nigeria
        (126, NULL, 5, 'N/A','1976-11-26','2024-11-26'), -- Palau
        (128, NULL, 5, 'N/A','1976-11-26','2024-11-26'), -- Panama
        (129, NULL, 5, 'N/A','1976-11-26','2024-11-26'), -- Papua New Guinea
        (130, NULL, 5, 'N/A','1976-11-26','2024-11-26'), -- Paraguay
        (131, NULL, 5, 'N/A','1976-11-26','2024-11-26'), -- Peru
        (132, NULL, 5, 'N/A','1976-11-26','2024-11-26'), -- Philippines
        (139, NULL, 5, 'N/A','1976-11-26','2024-11-26'), -- Saint Kitts and Nevis
        (140, NULL, 5, 'N/A','1976-11-26','2024-11-26'), -- Saint Lucia
        (146, NULL, 5, 'N/A','1976-11-26','2024-11-26'), -- Senegal
        (150, NULL, 5, 'N/A','1976-11-26','2024-11-26'), -- Singapore
        (153, NULL, 5, 'N/A','1976-11-26','2024-11-26'), -- Solomon Islands
        (161, NULL, 5, 'N/A','1976-11-26','2024-11-26'), -- Suriname
        (170, NULL, 5, 'N/A','1976-11-26','2024-11-26'), -- Tonga
        (171, NULL, 5, 'N/A','1976-11-26','2024-11-26'), -- Trinidad and Tobago
        (176, NULL, 5, 'N/A','1976-11-26','2024-11-26'), -- Uganda
        (180, 48, 5, 'Zika virus in the US Virgin Islands (St Choix, St John and St Thomas in the Leeward Islands).','1976-11-26','2024-11-26'), -- United States of America
        (180, 49, 5, 'Zika virus in the US Virgin Islands (St Choix, St John and St Thomas in the Leeward Islands).','1976-11-26','2024-11-26'), -- United States of America
        (180, 50, 5, 'Zika virus in the US Virgin Islands (St Choix, St John and St Thomas in the Leeward Islands).','1976-11-26','2024-11-26'), -- United States of America

        (183, NULL, 5, 'N/A','1976-11-26','2024-11-26'), -- Vanuatu
        (184, NULL, 5, 'N/A','1976-11-26','2024-11-26'), -- Venezuela
        (185, NULL, 5, 'N/A','1976-11-26','2024-11-26'); -- Vietnam

INSERT INTO country_disease (country_id, region_id, disease_id, note, start_date, end_date) VALUES
                                                                                                -- Chikungunya occurrences
                                                                                                (6, NULL, 6, 'N/A', '1976-11-26','2024-11-26'),  -- Argentina
                                                                                                (13, NULL, 6, 'N/A','1976-11-26','2024-11-26'),  -- Bangladesh
                                                                                                (25, NULL, 6, 'N/A', '1976-11-26', '2024-11-26'), -- Cambodia
                                                                                                (26, NULL, 6, 'N/A', '1976-11-26', '2024-11-26'), -- Cameroon
                                                                                                (28, NULL, 6, 'N/A', '1976-11-26', '2024-11-26'), -- Central African Republic
                                                                                                (29, NULL, 6, 'N/A', '1976-11-26', '2024-11-26'), -- Chad
                                                                                                (31, NULL, 6, 'N/A', '1976-11-26', '2024-11-26'), -- China
                                                                                                (32, NULL, 6, 'N/A', '1976-11-26', '2024-11-26'), -- Colombia
                                                                                                (35, NULL, 6, 'N/A', '1976-11-26', '2024-11-26'), -- Costa Rica
                                                                                                (42, NULL, 6,'N/A','1976-11-26','2024-11-26'), -- Dominica
                                                                                                (43, NULL, 6,'N/A','1976-11-26','2024-11-26'), -- Dominican Republic
                                                                                                (46, NULL, 6, 'N/A','1976-11-26','2024-11-26'), -- El Salvador
                                                                                                (47, NULL, 6, 'N/A', '1976-11-26', '2024-11-26'), -- Equatorial Guinea
                                                                                                (50, NULL, 6,'N/A','1976-11-26','2024-11-26'), -- Eswatini
                                                                                                (51, NULL, 6,'N/A','1976-11-26','2024-11-26'), -- Ethiopia
                                                                                                (52, NULL, 6,'N/A','1976-11-26','2024-11-26'), -- Fiji
                                                                                                (55, NULL, 6,'N/A','1976-11-26', '2024-11-26'), -- Gabon
                                                                                                (56, NULL, 6,'N/A','1976-11-26', '2024-11-26'), -- Gambia
                                                                                                (61, NULL, 6, 'N/A','1976-11-26', '2024-11-26'), -- Grenada
                                                                                                (62, NULL, 6, 'N/A','1976-11-26', '2024-11-26'), -- Guatemala
                                                                                                (63, NULL, 6,'N/A','1976-11-26', '2024-11-26'), -- Guinea
                                                                                                (66, NULL, 6, 'N/A','1976-11-26', '2024-11-26'), -- Haiti

-- TEST
                                                                                                (68, NULL, 6, 'N/A','2024-07-26', '2024-8-26'), -- Honduras

                                                                                                (71, NULL, 6, 'N/A','1976-11-26', '2024-11-26'), -- India
                                                                                                (72, NULL, 6, 'N/A','1976-11-26', '2024-11-26'), -- Indonesia
                                                                                                (77, NULL, 6, 'N/A','1976-11-26', '2024-11-26'), -- Jamaica
                                                                                                (81, NULL, 6, 'N/A','1976-11-26', '2024-11-26'), -- Kenya
                                                                                                (82, NULL, 6, 'N/A','1976-11-26', '2024-11-26'), -- Kiribati
                                                                                                (86, NULL, 6, 'N/A','1976-11-26', '2024-11-26'), -- Laos
                                                                                                (90, NULL, 6, 'N/A','1976-11-26','2024-11-26'), -- Liberia
                                                                                                (95, NULL, 6, 'N/A','1976-11-26','2024-11-26'), -- Madagascar
                                                                                                (96, NULL, 6, 'N/A','1976-11-26','2024-11-26'), -- Malawi
                                                                                                (97, NULL, 6, 'N/A','1976-11-26','2024-11-26'), -- Malaysia
                                                                                                (98, NULL, 6, 'N/A','1976-11-26','2024-11-26'), -- Maldives
                                                                                                (99, NULL, 6, 'N/A','1976-11-26','2024-11-26'), -- Mali
                                                                                                (101, NULL, 6, 'N/A','1976-11-26','2024-11-26'), -- Marshall Islands
                                                                                                (102, NULL, 6, 'N/A','1976-11-26','2024-11-26'), -- Martinique
                                                                                                (104, NULL, 6, 'N/A','1976-11-26','2024-11-26'), -- Mauritius
                                                                                                (105, NULL, 6, 'N/A','1976-11-26','2024-11-26'), -- Mexico
                                                                                                (112, NULL, 6, 'N/A','1976-11-26','2024-11-26'), -- Myanmar
                                                                                                (114, NULL, 6, 'N/A','1976-11-26','2024-11-26'), -- Naura
                                                                                                (118, NULL, 6, 'N/A','1976-11-26','2024-11-26'), -- Nicaragua
                                                                                                (120, NULL, 6, 'N/A','1976-11-26','2024-11-26'), -- Nigeria
                                                                                                (125, NULL, 6, 'N/A','1976-11-26','2024-11-26'), -- Pakistan
                                                                                                (126, NULL, 6, 'N/A','1976-11-26','2024-11-26'), -- Palau
                                                                                                (128, NULL, 6, 'N/A','1976-11-26','2024-11-26'), -- Panama
                                                                                                (130, NULL, 6, 'N/A','1976-11-26','2024-11-26'), -- Paraguay
                                                                                                (131, NULL, 6, 'N/A','1976-11-26','2024-11-26'), -- Peru
                                                                                                (132, NULL, 6, 'N/A','1976-11-26','2024-11-26'), -- Philippines
                                                                                                (139, NULL, 6, 'N/A','1976-11-26','2024-11-26'), -- Saint Kitts and Nevis
                                                                                                (139, NULL, 6, 'N/A','1976-11-26','2024-11-26'), -- Saint Lucia
                                                                                                (146, NULL, 6, 'N/A','1976-11-26','2024-11-26'), -- Senegal
                                                                                                (148, NULL, 6, 'N/A','1976-11-26','2024-11-26'), -- Seychelles
                                                                                                (149, NULL, 6, 'N/A','1976-11-26','2024-11-26'), -- Sierra Leone
                                                                                                (150, NULL, 6, 'N/A','1976-11-26','2024-11-26'), -- Singapore
                                                                                                (154, NULL, 6, 'N/A','1976-11-26','2024-11-26'), -- Somalia
                                                                                                (155, NULL, 6, 'N/A','1976-11-26','2024-11-26'), -- South Africa
                                                                                                (157, NULL, 6, 'N/A','1976-11-26','2024-11-26'), -- South Sudan
                                                                                                (159, NULL, 6, 'N/A','1976-11-26','2024-11-26'), -- Sri Lanka
                                                                                                (160, NULL, 6, 'N/A','1976-11-26','2024-11-26'), -- Sudan
                                                                                                (161, NULL, 6, 'N/A','1976-11-26','2024-11-26'), -- Suriname
                                                                                                (166, NULL, 6, 'N/A','1976-11-26','2024-11-26'), -- Tanzania
                                                                                                (170, NULL, 6, 'N/A','1976-11-26','2024-11-26'), -- Tonga
                                                                                                (176, NULL, 6, 'N/A','1976-11-26','2024-11-26'), -- Uganda

                                                                                                (184, NULL, 6, 'N/A','1976-11-26','2024-11-26'), -- Venezuela
                                                                                                (185, NULL, 6, 'N/A','1976-11-26','2024-11-26'), -- Vietnam
                                                                                                (186, NULL, 6, 'N/A','1976-11-26','2024-11-26'), -- Yemen
                                                                                                (187, NULL, 6, 'N/A','1976-11-26','2024-11-26'); -- Zambia

INSERT INTO country_disease (country_id, region_id, disease_id, note, start_date, end_date) VALUES
        -- Yellow Fever occurrences
        (5,  NULL, 7, 'N/A', '1976-11-26','2024-11-26'), -- Angola
        (6,  NULL, 7, 'N/A', '1976-11-26','2024-11-26'),  -- Argentina
        (26, NULL, 7, 'N/A', '1976-11-26', '2024-11-26'), -- Cameroon
        (27, NULL, 7, 'N/A', '1976-11-26', '2024-11-26'), -- Central African Republic
        (29, NULL, 7, 'N/A', '1976-11-26', '2024-11-26'), -- Chad
        (32, NULL, 7, 'N/A', '1976-11-26', '2024-11-26'), -- Colombia
        (47, NULL, 7, 'N/A', '1976-11-26', '2024-11-26'), -- Equatorial Guinea

        -- TEST
        (51, NULL, 7, 'N/A', '2024-01-04', '2024-02-26'), -- Ethiopia

        (55, NULL, 7, 'N/A','1976-11-26', '2024-11-26'), -- Gabon
        (56, NULL, 7, 'N/A','1976-11-26', '2024-11-26'), -- Gambia
        (59, NULL, 7, 'N/A','1976-11-26', '2024-11-26'), -- Ghana
        (63, NULL, 7, 'N/A','1976-11-26', '2024-11-26'), -- Guinea
        (90, NULL, 7, 'N/A','1976-11-26','2024-11-26'), -- Liberia
        (103, NULL, 7,'N/A','1976-11-26','2024-11-26'), -- Mauritania
        (119, NULL, 7,'N/A','1976-11-26','2024-11-26'), -- Niger
        (120, NULL, 7,'N/A','1976-11-26','2024-11-26'), -- Nigeria
        (128, NULL, 7,'N/A','1976-11-26','2024-11-26'), -- Panama
        (130, NULL, 7,'N/A','1976-11-26','2024-11-26'), -- Paraguay
        (131, NULL, 7,'N/A','1976-11-26','2024-11-26'), -- Peru
        (146, NULL, 7, 'N/A','1976-11-26','2024-11-26'), -- Senegal
        (149, NULL, 7,'N/A','1976-11-26','2024-11-26'), -- Sierra Leone
        (157, NULL, 7,'N/A','1976-11-26','2024-11-26'), -- South Sudan
        (160, NULL, 7,'N/A','1976-11-26','2024-11-26'), -- Sudan
        (161, NULL, 7, 'N/A','1976-11-26','2024-11-26'), -- Suriname
        (169, NULL, 7, 'N/A','1976-11-26','2024-11-26'), -- Togo
        (171, NULL, 7, 'N/A','1976-11-26','2024-11-26'), -- Trinidad and Tobago
        (176, NULL, 7, 'N/A','1976-11-26','2024-11-26'), -- Uganda
        (184, NULL, 7, 'N/A','1976-11-26','2024-11-26'), -- Venezuela
        (3, 51, 7, 'is a mad one','1976-11-26','2024-11-26');






INSERT INTO donor_travelled_to_country (donor_id, country_id, region_id, end_date) VALUES
                                                                                        (1, 3, 1,  '2024-02-10'),  -- John traveled to Cameroon
                                                                                        -- (1, 3, null, '2018-07-15'),  -- John traveled to Bolivia
                                                                                        (2, 5, null, '2016-06-01'),  -- Jane traveled to Chad
                                                                                        (2, 7, null, '2019-04-01'),  -- Jane traveled to Colombia
                                                                                        (3, 15, null,  '2018-06-15'),  -- Mary traveled to Myanmar
                                                                                        (3, 10, null, '2020-01-15'),  -- Mary traveled to Guatemala

                                                                                         (3, 12, null, '2017-07-31'), -- Mary traveled to India
                                                                                        -- inserts for donor 5 alice
                                                                                        (5, 6, 2, '2024-03-08'), --
                                                                                        (5, 180,47 , '2024-09-26'); --




UPDATE country_disease
SET end_date = '2024-12-12'
WHERE end_date = '2024-11-26';

-- Adding the deferral period for each disease, different for each disease.
UPDATE country_disease
SET deferral_period_id = (SELECT deferral_period_id FROM deferral_period WHERE period_days = 120)
WHERE disease_id = 1;  -- Malaria

UPDATE country_disease
SET deferral_period_id = (SELECT deferral_period_id FROM deferral_period WHERE period_days = 120)
WHERE disease_id = 2;  -- Trypanosoma cruzi risk

UPDATE country_disease
SET deferral_period_id = (SELECT deferral_period_id FROM deferral_period WHERE period_days = 0)
WHERE disease_id = 3;  -- West Nile Virus

UPDATE country_disease
SET deferral_period_id = (SELECT deferral_period_id FROM deferral_period WHERE period_days = 0)
WHERE disease_id = 4;  -- Dengue Virus

UPDATE country_disease
SET deferral_period_id = (SELECT deferral_period_id FROM deferral_period WHERE period_days = 28)
WHERE disease_id = 5;  -- Zika Virus

UPDATE country_disease
SET deferral_period_id = (SELECT deferral_period_id FROM deferral_period WHERE period_days = 28)
WHERE disease_id = 6;  -- Chikungunya

UPDATE country_disease
SET deferral_period_id = (SELECT deferral_period_id FROM deferral_period WHERE period_days = 28)
WHERE disease_id = 7;  -- Yellow Fever


-- Adding the deferral note for each disease, so that the clinician receives a deferral recommendation for each specific disease.
UPDATE country_disease
SET deferral_note = 'Patients who have travelled to a Malaria endemic area should be deferred for 120 days.'
WHERE disease_id = 1;

UPDATE country_disease
SET deferral_note = 'Patients who have travelled to a Trypanosoma cruzi risk area could be deferred for 120 days, or undertake further testing.'
WHERE disease_id = 2;

UPDATE country_disease
SET deferral_note = 'Patients who have travelled to a West Nile Virus endemic area do not need to be deferred, unless they have symptoms, then a 180 day deferral is suggested.'
WHERE disease_id = 3;

UPDATE country_disease
SET deferral_note = 'Patients who have travelled to a Dengue Virus endemic area do not need to be deferred, unless they have symptoms, then a 180 day deferral is suggested.'
WHERE disease_id = 4;

UPDATE country_disease
SET deferral_note = 'Patients who have travelled to a Zika Virus endemic area should be deferred for 28 days, if symptoms then they should be deferred for 180 days.'
WHERE disease_id = 5;

UPDATE country_disease
SET deferral_note = 'Patients who have travelled to a Chikungunya endemic area should be deferred for 28 days, if symptoms then they should be deferred for 180 days.'
WHERE disease_id = 6;

UPDATE country_disease
SET deferral_note = 'Patients who have travelled to a Yellow Fever endemic area should be deferred for 28 days, if symptoms then they should be deferred for 180 days.'
WHERE disease_id = 7;






INSERT INTO questions (question) VALUES
                                                       ('Have you travelled to a country within the last 12 months?'),
                                                       ('Where have you travelled in the last 12 months?'),
                                                       ('When did you return from this country?'),
                                                       ('Have you lived abroad for more than 6 months?'),
                                                       ('Where have you lived abroad?'),
                                                       ('When did you return from this country?');




select * from country_disease where country_id= 3;
INSERT INTO travel_questionnaire (donor_id, question_id, answer)
VALUES
    (5, 1, 'Yes'),  -- Donor 5 travelled in the last 12 months.
    (5, 2, 'India'), -- Donor 5 travelled to India.
    (5, 3, '2020-01-01'), -- Donor 5 returned from India on 1st January 2020.
    (5, 4, 'Yes'), -- Donor 5 lived abroad for more than 6 months.
    (5, 5, 'India'), -- Donor 5 lived in India.
    (5, 6, '2020-01-01'), -- Donor 5 returned from India on 1st January 2020.
    (2, 1, 'No'),   -- Donor 2 did not travel in the last 12 months.
    (2, 4, 'No');   -- Donor 2 has not lived abroad for more than 6 months.


INSERT INTO users (username, password, enabled, role)
VALUES
    ('admin', '$2a$12$R4zbttj5T8epCIeJkUi36OAcU0pabLVIW8kryWCiOHHul99Wan2aO', true, 'ROLE_ADMIN'),
    ('clinician', '$2a$12$EeEjgdIHNbbs5tId6QR8i.dx1csuJVRrjOj6zxXLNxF7lf52.bibG' , true, 'ROLE_CLINICIAN');

SELECT * FROM users;


