
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
                                         ('Burkina Faso'),  -- Not done, Country ID 22
                                         ('Burundi'),
                                         ('Cabo Verde'),
                                         ('Cambodia'), -- Country ID 25
                                         ('Cameroon'),
                                         ('Chad'),
                                         ('Chile'),
                                         ('China'),
                                         ('Clipperton Island'), -- Not done, Country ID 30
                                         ('Cocos Island'), -- Not done, Country ID 31
                                         ('Colombia'), -- Country ID 32
                                         ('Comoros'), -- Not done, Country ID 33
                                         ('Congo'), -- Not done, Country ID 34
                                         ('Costa Rica'),
                                         ('Croatia'), -- Not done, Country ID 36
                                         ('Cuba'), -- Not done, Country ID 37
                                         ('Cyprus'), -- Not done, Country ID 38
                                         ('Czechia (Czech Republic)'), -- Not done, Country ID 39
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
                                         ('Guinea-Bissau'), -- Not done, Country ID 62
                                         ('Guyana'),  -- Not done, Country ID 63
                                         ('Haiti'),  -- Country ID 66
                                         ('Holy See'),  -- Not done, Country ID 67
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
                                         ('Saint Lucia'),  -- Not done, Country ID 140
                                         ('Saint Vincent and the Grenadines'),  -- Not done, Country ID 141
                                         ('Samoa'), -- Not done, Country ID 142
                                         ('San Marino'),  -- Not done, Country ID 143
                                         ('Sao Tome and Principe'),  -- Not done, Country ID 144
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
                                         ('Af'),
                                         ('Adf'),
                                         ('Afsf');



;


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
                                                               (1,1, 'Yes whole country below 2000m (May-Nov) until Sept 2015','1996-02-01','2015-09-30'),  -- Afghanistan
                                                               (3,1, 'From March 1996 to May 2019: desert oasis of Ihrir (Illizi Department) only','1996-03-01','2019-05-30'),  -- Algeria
                                                               (5,1, 'All year in the country','1974-11-01','2024-11-26'), -- Angola
                                                               (6,1, 'Until February 1996, All year in rural areas','1996-02-01','2024-11-24'),  -- Argentina
                                                               (11,1, 'Yes Great Exuma Island only Jul-Oct 2006 and Sept 2007- Sept 2015','2006-07-01','2015-09-30'),  -- Bahamas
                                                               (13,1,'Yes all year whole country until Sept 2015','1976-09-01','2015-11-30'),  -- Bangladesh
                                                               (18,1,'Risk exists throughout the year but especially from November to June in the northern parts of the country','1996-02-01','2024-11-26'),  -- Botswana
                                                               (19,1, 'All year in Amazonia, urban areas (e.g., Manaus), Amazon cruises, rural areas near Iguaçu Falls.', '1996-02-01', '2024-11-26'), -- Brazil
                                                               (20,1, 'All year round','1976-11-01','2024-11-26'), -- Brunei
                                                               (25,1, 'All year in most of the country, except Phnom Penh, Siem Reap, and Angkor Wat.', '1996-02-01', '2024-11-26'), -- Cambodia
                                                               (26,1, 'all year round', '1976-11-26', '2024-11-26'), -- Cameroon
                                                               (28,1, 'all year in the whole country', '1976-11-26', '2024-11-26'), -- Central African Republic
                                                               (29,1, 'all year in the whole country', '1976-11-26', '2024-11-26'), -- Chad
                                                               (31,1, 'Was at previous risk in multiple provinces ', '1996-02-01', '2021-06-30'), -- China
                                                               (32,1,'Yes all year whole country until Feb 2015','2015-01-01','2015-02-28'), -- Colombia
                                                               (35,1,'All year in whole country','1976-11-26','2024-11-26'), -- Costa Rica
                                                               (41,1,'All year in whole country','1976-11-26','2024-11-26'), -- Djibouti
                                                               (43,1,'All year in whole country','1976-11-26','2024-11-26'), -- Dominican Republic
                                                               (44,1, 'All year in most of the country. Visitors to Guayaquil, Quito, and other cities in the inter-Andean region and the Galapagos Islands, accept.', '1976-11-26', '2024-11-26'), -- Ecuador
                                                               (45,1,'During the months Jun-Oct, in El Faiyum region until May 2012','1996-02-001','2012-05-30'), -- Egypt
                                                               (46,1,'All Year whole country up until September 2006','2006-01-01','2006-09-30'), -- El Salvador
                                                               (47,1, 'all year in the whole country', '1976-11-26', '2024-11-26'), -- Equatorial Guinea
                                                               (48,1, 'all year in the whole country, Only accept visitors from Asmara and the central highlands', '1976-11-26', '2024-11-26'), -- Eritrea
                                                               (50,1,'All year in most of the country, Visitors to airport of Manzine and western half of country only accept','1976-11-26','2024-11-26'), -- Eswatini
                                                               (51,1,'All year in whole country. Visitors to city of Addis Ababa only accept','1976-11-26','2024-11-26'), -- Ethiopia
                                                               (55,1,'All year in the whole country','1976-11-26', '2024-11-26'), -- Gabon
                                                               (56,1,'All year in the whole country','1976-11-26', '2024-11-26'), -- Gambia
                                                               (57,1,'June - October in the South-East Of the Country','2024-06-01','2024-10-30'), -- Georgia
                                                               (59,1,'All year in the whole country','1976-11-26', '2024-11-26'), -- Ghana
                                                               (62,1, 'All year in the whole country up until December 2011','2011-01-01','2011-12-31'), -- Guatemala
                                                               (63,1,'All year in the whole country','1976-11-26', '2024-11-26'), -- Guinea
                                                               (66,1,'All year in the whole country','1976-11-26', '2024-11-26'), -- Haiti
                                                               (68,1,'All year in the whole country','1976-11-26', '2024-11-26'), -- Honduras
                                                               (71,1,'All year in the whole country until May 2012','2012-01-01','2012-05-30'), -- India
                                                               (72,1,'Only Accept visitors of the islands of Bali, Java and the city of Jakarta','1976-11-26', '2024-11-26'), -- Indonesia
                                                               (73,1,'All year in the whole country up until February 1996','1976-02-01','1996-02-28'), -- Iran
                                                               (74,1,'All year in the whole country up until February 1996','1976-02-01','1996-02-28'), -- Iraq
                                                               (77,1,'In the whole country during Dec 2006 - May 2012','2006-12-01','2012-05-30'),  -- Jamaica
                                                               (81,1,'All year in most of the country','1976-11-01','2024-11-26'), -- Kenya
                                                               (85,1,'All year in the whole country up until December 2016','2016-01-01','2016-12-31'), -- Kyrgyzstan
                                                               (86,1,'All year in most of the country, Visitors to Vientiane, Bokeo, Houaphanh only accept','1976-11-26','2024-11-26'), -- Laos
                                                               (90,1,'All year in the whole country','1976-11-26','2024-11-26'), -- Liberia
                                                               (91,1,'South-West of the country during February 1996 up until September 2006','1996-02-01','2006-09-30'), -- Libya,
                                                               (95,1,'All year in the whole country','1976-11-26','2024-11-26'), -- Madagascar
                                                               (96,1,'All year in the whole country','1976-11-26','2024-11-26'), -- Malawi
                                                               (97,1, 'All year in central Peninsular Malaysia and the Taman Negara National Park and all provinces of Sabah and Sarawak (on the island of Borneo)','1976-11-26','2024-11-26'), -- Malaysia
                                                               (98,1,'All years in whole country up until October 2009','1976-10-01', '2009-10-31'), -- Maldives
                                                               (99,1,'All year in the whole country','1976-11-26','2024-11-26'), -- Mali
                                                               (103,1,'All year in the whole country','1976-11-26','2024-11-26'), -- Mauritania
                                                               (104,1,'All year in the whole country up until September 2006','2006-01-01','2006-09-30'), -- Mauritius
                                                               (105,1,'Yes widespread (Except Cancun) up until December 2011','2011-01-01','2011-12-31'), -- Mexico
                                                               (110,1,'Yes May-Oct in provinces of Al Hoceima, Taounate, and Taza until Dec 2011','2011-05-01','2011-12-31'), -- Morocco
                                                               (111,1,'All year in the whole country','1976-11-26','2024-11-26'), -- Mozambique
                                                               (112,1,'All year in the whole country','1976-11-26','2024-11-26'), -- Myanmar
                                                               (113,1,'Yes all year northern regions until May 2015','2015-01-01','2015-05-31'), -- Namibia
                                                               (115,1,'Yes all year whole country until Sept 2006','2006-01-01','2006-09-30'), -- Nepal
                                                               (118,1,'All year in most of the country, visitors to Managua only accept','1976-11-26','2024-11-26'), -- Nicaragua
                                                               (119,1,'All year in the whole country','1976-11-26','2024-11-26'), -- Niger
                                                               (120,1,'All year in the whole country','1976-11-26','2024-11-26'), -- Nigeria
                                                               (121,1,'All year in in the de-militarized zone between North and South Korea (Kyunggi-do and Gangwon-do provinces)','1976-11-26','2024-11-26'), -- North Korea
                                                               (124,1,'All year in the whole country up until September 2006', '2006-01-01', '2006-09-30'), -- Oman
                                                               (125,1,'All year in the whole country', '1976-11-26', '2024-11-26'), -- Pakistan
                                                               (128,1,'Yes all year whole country until Dec 2011', '2011-01-01', '2011-12-31'), -- Panama
                                                               (129,1,'All year in the whole country', '1976-11-26', '2024-11-26'), -- Papua New Guinea
                                                               (130,1,'All year in the whole country up until February 1996','1996-02-01','1996-02-28'), -- Paraguay
                                                               (131,1,'All year in the country up until February 1996','1996-02-01','1996-02-28'), -- Peru
                                                               (132,1,'All year in the whole country up until February 1996','1996-02-01','1996-02-28'), -- Philippines
                                                               (138,1,'All year in the whole country','1976-11-26','2024-11-26'), -- Rwanda
                                                               (145,1,'Yes all year whole country (except urban Jeddah, Mecca, Medina and Taif) until Sept 2006','2006-01-01','2006-09-30'), -- Saudi Arabia
                                                               (146,1,'All year in the whole country','1976-11-26','2024-11-26'), -- Senegal
                                                               (149,1,'All year in the whole country','1976-11-26','2024-11-26'), -- Sierra Leone
                                                               (153,1,'All year in the whole country','1976-11-26','2024-11-26'), -- Solomon Islands
                                                               (154,1,'All year in the whole country','1976-11-26','2024-11-26'), -- Somalia
                                                               (155,1,'All year in the whole country up until February 1996','1996-02-01','1996-02-28'), -- South Africa
                                                               (156,1,'All year in in the de-militarized zone between North and South Korea (Kyunggi-do and Gangwon-do provinces)','1976-11-26','2024-11-26'), -- South Korea
                                                               (157,1,'All year in the whole country','1976-11-26','2024-11-26'), -- South Sudan
                                                               (159,1,'Yes all year whole country until Dec 2011','2011-01-01','2011-12-31'), -- Sri Lanka
                                                               (160,1,'All year in the whole country','1976-11-26','2024-11-26'), -- Sudan
                                                               (161,1,'All year in the whole country up until December 2011','2011-01-01','2011-12-31'), -- Suriname
                                                               (164,1,'Must be considered to be all year in whole country in view of current situation.','1976-11-26','2024-11-26'), -- Syria
                                                               (166,1,'All year in the whole country','1976-11-26','2024-11-26'), -- Tanzania
                                                               (167,1,'All year in the whole country up until May 2012','2012-01-01','2012-05-30'), -- Thailand
                                                               (169,1,'All year in the whole country','1976-11-26','2024-11-26'), -- Togo
                                                               (173,1,'Yes all year whole country until Feb 1996','1996-02-01','1996-02-28'), -- Turkey
                                                               (174,1,'Yes Jun – Oct in South Eastern border regions until Sept 2015','2015-06-01','2015-09-30'), -- Turkmenistan
                                                               (176,1,'All year in the whole country','1976-11-26','2024-11-26'), -- Uganda
                                                               (178,1,'Yes in East of country until Dec 2011 with no risk in the cities of Abu Dhabi, Dubai, Sharjah, Ajman and Umm al Qaiwain','1976-11-26','2024-11-26'), -- United Arab Emirates
                                                               (183,1,'All year in the whole country','1976-11-26','2024-11-26'), -- Vanuatu
                                                               (184,1,'All year in the whole country','1976-11-26','2024-11-26'), -- Venezuela
                                                               (185,1,'Yes all year whole country until May 2012','2012-01-01','2012-05-30'), -- Vietnam
                                                               (186,1,'Yes all year whole country until May 2012','2012-01-01','2012-05-30'); -- Yemen

INSERT INTO country_disease (country_id, disease_id, note, start_date, end_date) VALUES
                                                                -- Trypanosoma Cruzi Risk occurrences
                                                                (6, 2, 'N/A', '1976-11-26', '2024-11-26'), -- Argentina
                                                                (11, 2, 'N/A', '1976-11-26', '2024-11-26'), -- Bahamas
                                                                (19, 2, 'N/A', '1976-11-26', '2024-11-26'), -- Brazil
                                                                (30, 2, 'N/A', '1976-11-26', '2024-11-26'), -- Chile
                                                                (32, 2, 'N/A', '1976-11-26', '2024-11-26'), -- Colombia
                                                                (46, 2, 'N/A', '1976-11-26','2024-11-26'), -- El Salvador
                                                                (68, 2, 'N/A', '1976-11-26', '2024-11-26'), -- Honduras
                                                                (118, 2, 'N/A', '1976-11-26', '2024-11-26'), -- Nicaragua
                                                                (130, 2, 'N/A', '1976-11-26', '2024-11-26'), -- Paraguay
                                                                (131, 2, 'N/A', '1976-11-26', '2024-11-26'), -- Peru
                                                                (172, 2, 'N/A', '1976-11-26', '2024-11-26'), -- Tunisia
                                                                (181, 2, 'N/A', '1976-11-26', '2024-11-26'); -- Uruguay

INSERT INTO country_disease (country_id, disease_id, note, start_date, end_date) VALUES
                                                                -- West Nile Virus Risk occurrences
                                                                (2, 3,'Donors who have visited between 1st May to 30th November must have the WNV Donor Selection Guideline applied.','2024-05-01', '2024-11-30'), -- Albania
                                                                (3, 3,'Donors who have visited between 1st May to 30th November must have the WNV Donor Selection Guideline applied.','2024-05-01', '2024-11-30'), -- Algeria
                                                                (7, 3,'Donors who have visited between 1st May to 30th November must have the WNV Donor Selection Guideline applied.','2024-05-01', '2024-11-30'), -- Armenia
                                                                (9, 3,'Donors who have visited between 1st May to 30th November must have the WNV Donor Selection Guideline applied.','2024-05-01', '2024-11-30'), -- Austria
                                                                (17, 3,'Donors who have visited between 1st May to 30th November must have the WNV Donor Selection Guideline applied.','2024-05-01', '2024-11-30'), -- Bosnia and Herzegovina
                                                                (21, 3,'Donors who have visited between 1st May to 30th November must have the WNV Donor Selection Guideline applied.','2024-05-01', '2024-11-30'), -- Bulgaria
                                                                (27, 3,'Donors who have visited between 1st May to 30th November must have the WNV Donor Selection Guideline applied.','2024-05-01', '2024-11-30'), -- Canada
                                                                (54, 3,'Donors who have visited between 1st May to 30th November must have the WNV Donor Selection Guideline applied.','2024-05-01', '2024-11-30'), -- France
                                                                (58, 3,'Donors who have visited between 1st May to 30th November must have the WNV Donor Selection Guideline applied.','2024-05-01', '2024-11-30'), -- Germany
                                                                (60, 3,'Donors who have visited between 1st May to 30th November must have the WNV Donor Selection Guideline applied.','2024-05-01', '2024-11-30'), -- Greece
                                                                (69, 3,'Donors who have visited between 1st May to 30th November must have the WNV Donor Selection Guideline applied.','2024-05-01', '2024-11-30'), -- Hungary
                                                                (76, 3,'Donors who have visited between 1st May to 30th November must have the WNV Donor Selection Guideline applied.', '2024-05-01', '2024-11-30'), -- Italy
                                                                (83, 3,'Donors who have visited between 1st May to 30th November must have the WNV Donor Selection Guideline applied.','2024-05-01', '2024-11-30'), -- Italy
                                                                (107,3,'N/A','1976-11-26','2024-11-26'), -- Monaco
                                                                (109,3,'Donors who have visited between 1st May to 30th November must have the WNV Donor Selection Guideline applied.','2024-05-01', '2024-11-30'), -- Montenegro
                                                                (122,3,'Donors who have visited between 1st May to 30th November must have the WNV Donor Selection Guideline applied.','2024-05-01', '2024-11-30'), -- North Macedonia (formerly Macedonia)
                                                                (136,3,'Donors who have visited between 1st May to 30th November must have the WNV Donor Selection Guideline applied.','2024-05-01', '2024-11-30'), -- Romania
                                                                (137,3,'Donors who have visited between 1st May to 30th November must have the WNV Donor Selection Guideline applied.','2024-05-01', '2024-11-30'), -- Russia
                                                                (147,3,'Donors who have visited between 1st May to 30th November must have the WNV Donor Selection Guideline applied.','2024-05-01', '2024-11-30'), -- Serbia
                                                                (152,3,'Donors who have visited between 1st May to 30th November must have the WNV Donor Selection Guideline applied.','2024-05-01', '2024-11-30'), -- Slovenia
                                                                (158,3,'Donors who have visited between 1st May to 30th November must have the WNV Donor Selection Guideline applied.','2024-05-01', '2024-11-30'), -- Spain
                                                                (173,3,'Donors who have visited between 1st May to 30th November must have the WNV Donor Selection Guideline applied.','2024-05-01', '2024-11-30'), -- Turkey
                                                                (177,3,'Donors who have visited between 1st May to 30th November must have the WNV Donor Selection Guideline applied.','2024-05-01', '2024-11-30'), -- Ukraine
                                                                (180,3,'Donors who have visited between 1st May to 30th November must have the WNV Donor Selection Guideline applied.','2024-05-01', '2024-11-30'); -- United States of America

INSERT INTO country_disease (country_id, disease_id, note, start_date, end_date) VALUES
                                                                -- Dengue Virus occurrences
                                                                (1, 4, 'N/A', '1976-11-26','2024-11-26'), -- Afghanistan
                                                                (5, 4, 'N/A', '1976-11-26', '2024-11-26'), -- Angola
                                                                (6, 4, 'N/A', '1976-11-26', '2024-11-26'), -- Argentina
                                                                (13, 4, 'N/A', '1976-11-26','2024-11-26'), -- Bangladesh
                                                                (14, 4, 'N/A', '1976-11-26', '2024-11-26'), -- Barbados
                                                                (25, 4, 'N/A', '1976-11-26', '2024-11-26'), -- Cambodia
                                                                (29, 4, 'N/A', '1976-11-26', '2024-11-26'), -- Chad
                                                                (31, 4, 'N/A', '1976-11-26', '2024-11-26'), -- China
                                                                (32, 4, 'N/A', '1976-11-26', '2024-11-26'), -- Colombia
                                                                (35, 4, 'N/A', '1976-11-26','2024-11-26'), -- Costa Rica
                                                                (41, 4, 'N/A', '1976-11-26', '2024-11-26'), -- Djibouti
                                                                (42, 4, 'N/A', '1976-11-26', '2024-11-26'), -- Dominica
                                                                (43, 4, 'N/A', '1976-11-26','2024-11-26'), -- Dominican Republic
                                                                (45, 4, 'N/A', '1976-11-26', '2024-11-26'), -- Egypt
                                                                (46, 4, 'N/A', '1976-11-26','2024-11-26'), -- El Salvador
                                                                (48, 4, 'N/A', '1976-11-26', '2024-11-26'), -- Eritrea
                                                                (50, 4, 'N/A', '1976-11-26', '2024-11-26'), -- Eswatini
                                                                (51, 4, 'N/A', '1976-11-26', '2024-11-26'), -- Ethiopia
                                                                (52, 4, 'N/A', '1976-11-26', '2024-11-26'), -- Fiji
                                                                (54, 4, 'N/A', '1976-11-26', '2024-11-26'), -- France
                                                                (55, 4, 'N/A', '1976-11-26', '2024-11-26'), -- Gabon
                                                                (56, 4, 'N/A', '1976-11-26', '2024-11-26'), -- Gambia
                                                                (59, 4, 'N/A', '1976-11-26', '2024-11-26'), -- Ghana
                                                                (61, 4, 'N/A', '1976-11-26', '2024-11-26'), -- Grenada
                                                                (62, 4, 'N/A', '1976-11-26', '2024-11-26'), -- Guatemala
                                                                (63, 4, 'N/A', '1976-11-26', '2024-11-26'), -- Guinea
                                                                (68, 4, 'N/A', '1976-11-26', '2024-11-26'), -- Honduras
                                                                (71, 4, 'N/A', '1976-11-26', '2024-11-26'), -- India
                                                                (72, 4, 'N/A', '1976-11-26', '2024-11-26'), -- Indonesia
                                                                (73, 4, 'N/A', '1976-11-26', '2024-11-26'), -- Iran
                                                                (76, 4, 'N/A', '1976-11-26', '2024-11-26'), -- Italy
                                                                (77, 4, 'N/A', '1976-11-26', '2024-11-26'), -- Jamaica
                                                                (81, 4, 'N/A', '1976-11-26', '2024-11-26'), -- Kenya
                                                                (82, 4, 'N/A', '1976-11-26', '2024-11-26'), -- Kiribati
                                                                (86, 4, 'N/A', '1976-11-26', '2024-11-26'), -- Laos
                                                                (97, 4, 'N/A', '1976-11-26', '2024-11-26'), -- Malaysia
                                                                (98, 4, 'N/A', '1976-11-26', '2024-11-26'), -- Maldives
                                                                (99, 4, 'N/A', '1976-11-26', '2024-11-26'), -- Mali
                                                                (101, 4, 'N/A','1976-11-26','2024-11-26'), -- Marshall Islands
                                                                (102, 4, 'N/A','1976-11-26','2024-11-26'), -- Martinique
                                                                (103, 4, 'N/A','1976-11-26','2024-11-26'), -- Mauritania
                                                                (104, 4, 'N/A','1976-11-26','2024-11-26'), -- Mauritius
                                                                (105, 4, 'N/A','1976-11-26','2024-11-26'), -- Mexico)
                                                                (107, 4, 'N/A','1976-11-26','2024-11-26'), -- Monaco
                                                                (110, 4, 'N/A','1976-11-26','2024-11-26'), -- Mozambique
                                                                (111, 4, 'N/A','1976-11-26','2024-11-26'), -- Myanmar
                                                                (115, 4, 'N/A','1976-11-26','2024-11-26'), -- Nepal
                                                                (118, 4, 'N/A','1976-11-26','2024-11-26'), -- Nicaragua
                                                                (119, 4, 'N/A','1976-11-26','2024-11-26'), -- Niger
                                                                (120, 4, 'N/A','1976-11-26','2024-11-26'), -- Nigeria
                                                                (124, 4, 'N/A','1976-11-26','2024-11-26'), -- Oman
                                                                (125, 4, 'N/A','1976-11-26','2024-11-26'), -- Pakistan
                                                                (128, 4, 'N/A','1976-11-26','2024-11-26'), -- Panama
                                                                (130, 4, 'N/A','1976-11-26','2024-11-26'), -- Paraguay
                                                                (131, 4, 'N/A','1976-11-26','2024-11-26'), -- Peru
                                                                (132, 4, 'N/A','1976-11-26','2024-11-26'), -- Philippines
                                                                (138, 4, 'N/A','1976-11-26','2024-11-26'), -- Rwanda
                                                                (140, 4, 'N/A','1976-11-26','2024-11-26'), -- Saint Lucia
                                                                (145, 4,'N/A','1976-11-26','2024-11-26'), -- Saudi Arabia
                                                                (146, 4, 'N/A','1976-11-26','2024-11-26'), -- Senegal)
                                                                (148, 4, 'N/A','1976-11-26','2024-11-26'), -- Seychelles
                                                                (150, 4, 'N/A','1976-11-26','2024-11-26'), -- Singapore
                                                                (153, 4, 'N/A','1976-11-26','2024-11-26'), -- Solomon Islands
                                                                (154, 4, 'N/A','1976-11-26','2024-11-26'), -- Somalia
                                                                (159, 4, 'N/A','1976-11-26','2024-11-26'), -- Sri Lanka
                                                                (160, 4, 'N/A','1976-11-26','2024-11-26'), -- Sudan
                                                                (166, 4, 'N/A','1976-11-26','2024-11-26'), -- Tanzania
                                                                (169, 4, 'N/A','1976-11-26','2024-11-26'), -- Togo
                                                                (170, 4, 'N/A','1976-11-26','2024-11-26'), -- Tonga
                                                                (171, 4, 'N/A','1976-11-26','2024-11-26'), -- Trinidad and Tobago
                                                                (178, 4, 'N/A','1976-11-26','2024-11-26'), -- United Arab Emirates
                                                                (180, 4, 'In Hawaii Only','1976-11-26','2024-11-26'), -- United States of America
                                                                (181, 4, 'N/A','1976-11-26','2024-11-26'), -- Uruguay
                                                                (183, 4, 'N/A','1976-11-26','2024-11-26'), -- Vanuatu
                                                                (184, 4, 'N/A','1976-11-26','2024-11-26'), -- Venezuela
                                                                (185, 4, 'N/A','1976-11-26','2024-11-26'), -- Vietnam
                                                                (186, 4, 'N/A','1976-11-26','2024-11-26'), -- Yemen
                                                                (187, 4, 'N/A','1976-11-26','2024-11-26'); -- Zambia

INSERT INTO country_disease (country_id, disease_id, note, start_date, end_date) VALUES
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
                                                               (42, 5, 'N/A','1976-11-26','2024-11-26'), -- Dominica
                                                               (43, 5, 'N/A','1976-11-26','2024-11-26'), -- Dominican Republic
                                                               (46, 5, 'N/A','1976-11-26','2024-11-26'), -- El Salvador
                                                               (50, 5, 'N/A','1976-11-26','2024-11-26'), -- Eswatini
                                                               (52, 5, 'N/A','1976-11-26','2024-11-26'), -- Fiji
                                                               (55, 5, 'N/A','1976-11-26', '2024-11-26'), -- Gabon
                                                               (61, 5, 'N/A','1976-11-26', '2024-11-26'), -- Grenada
                                                               (62, 5, 'N/A','1976-11-26', '2024-11-26'), -- Guatemala
                                                               (66, 5, 'N/A','1976-11-26', '2024-11-26'), -- Haiti
                                                               (68, 5, 'N/A','1976-11-26', '2024-11-26'), -- Honduras
                                                               (71, 5, 'N/A','1976-11-26', '2024-11-26'), -- India
                                                               (72, 5, 'N/A','1976-11-26', '2024-11-26'), -- Indonesia
                                                               (77, 5, 'N/A','1976-11-26', '2024-11-26'), -- Jamaica
                                                               (81, 5, 'N/A','1976-11-26', '2024-11-26'), -- Kenya
                                                               (86, 5,'N/A','1976-11-26', '2024-11-26'), -- Laos
                                                               (97, 5,'N/A','1976-11-26', '2024-11-26'), -- Malaysia
                                                               (98, 5,'N/A','1976-11-26', '2024-11-26'), -- Maldives
                                                               (99, 5,'N/A','1976-11-26', '2024-11-26'), -- Mali
                                                               (101, 5, 'N/A','1976-11-26','2024-11-26'), -- Marshall Islands
                                                               (102, 5, 'N/A','1976-11-26','2024-11-26'), -- Martinique
                                                               (105, 5, 'N/A','1976-11-26','2024-11-26'), -- Mexico
                                                               (118, 5, 'N/A','1976-11-26','2024-11-26'), -- Nicaragua
                                                               (120, 5, 'N/A','1976-11-26','2024-11-26'), -- Nigeria
                                                               (126, 5, 'N/A','1976-11-26','2024-11-26'), -- Palau
                                                               (128, 5, 'N/A','1976-11-26','2024-11-26'), -- Panama
                                                               (129, 5, 'N/A','1976-11-26','2024-11-26'), -- Papua New Guinea
                                                               (130, 5, 'N/A','1976-11-26','2024-11-26'), -- Paraguay
                                                               (131, 5, 'N/A','1976-11-26','2024-11-26'), -- Peru
                                                               (132, 5, 'N/A','1976-11-26','2024-11-26'), -- Philippines
                                                               (139, 5, 'N/A','1976-11-26','2024-11-26'), -- Saint Kitts and Nevis
                                                               (140, 5, 'N/A','1976-11-26','2024-11-26'), -- Saint Lucia
                                                               (146, 5, 'N/A','1976-11-26','2024-11-26'), -- Senegal
                                                               (150, 5, 'N/A','1976-11-26','2024-11-26'), -- Singapore
                                                               (153, 5, 'N/A','1976-11-26','2024-11-26'), -- Solomon Islands
                                                               (161, 5, 'N/A','1976-11-26','2024-11-26'), -- Suriname
                                                               (170, 5, 'N/A','1976-11-26','2024-11-26'), -- Tonga
                                                               (171, 5, 'N/A','1976-11-26','2024-11-26'), -- Trinidad and Tobago
                                                               (176, 5, 'N/A','1976-11-26','2024-11-26'), -- Uganda
                                                               (180, 5, 'Zika virus in the US Virgin Islands (St Choix, St John and St Thomas in the Leeward Islands).','1976-11-26','2024-11-26'), -- United States of America
                                                               (183, 5, 'N/A','1976-11-26','2024-11-26'), -- Vanuatu
                                                               (184, 5, 'N/A','1976-11-26','2024-11-26'), -- Venezuela
                                                               (185, 5, 'N/A','1976-11-26','2024-11-26'); -- Vietnam

INSERT INTO country_disease (country_id, disease_id, note, start_date, end_date) VALUES
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
                                                               (55, 6,'N/A','1976-11-26', '2024-11-26'), -- Gabon
                                                               (56, 6,'N/A','1976-11-26', '2024-11-26'), -- Gambia
                                                               (61, 6, 'N/A','1976-11-26', '2024-11-26'), -- Grenada
                                                               (62, 6, 'N/A','1976-11-26', '2024-11-26'), -- Guatemala
                                                               (63, 6,'N/A','1976-11-26', '2024-11-26'), -- Guinea
                                                               (66, 6, 'N/A','1976-11-26', '2024-11-26'), -- Haiti
                                                               (68, 6, 'N/A','1976-11-26', '2024-11-26'), -- Honduras
                                                               (71, 6, 'N/A','1976-11-26', '2024-11-26'), -- India
                                                               (72, 6, 'N/A','1976-11-26', '2024-11-26'), -- Indonesia
                                                               (77, 6, 'N/A','1976-11-26', '2024-11-26'), -- Jamaica
                                                               (81, 6, 'N/A','1976-11-26', '2024-11-26'), -- Kenya
                                                               (82, 6, 'N/A','1976-11-26', '2024-11-26'), -- Kiribati
                                                               (86, 6, 'N/A','1976-11-26', '2024-11-26'), -- Laos
                                                               (90, 6, 'N/A','1976-11-26','2024-11-26'), -- Liberia
                                                               (95, 6, 'N/A','1976-11-26','2024-11-26'), -- Madagascar
                                                               (96, 6, 'N/A','1976-11-26','2024-11-26'), -- Malawi
                                                               (97, 6, 'N/A','1976-11-26','2024-11-26'), -- Malaysia
                                                               (98, 6, 'N/A','1976-11-26','2024-11-26'), -- Maldives
                                                               (99, 6, 'N/A','1976-11-26','2024-11-26'), -- Mali
                                                               (101, 6, 'N/A','1976-11-26','2024-11-26'), -- Marshall Islands
                                                               (102, 6, 'N/A','1976-11-26','2024-11-26'), -- Martinique
                                                               (104, 6, 'N/A','1976-11-26','2024-11-26'), -- Mauritius
                                                               (105, 6, 'N/A','1976-11-26','2024-11-26'), -- Mexico
                                                               (112, 6, 'N/A','1976-11-26','2024-11-26'), -- Myanmar
                                                               (114, 6, 'N/A','1976-11-26','2024-11-26'), -- Naura
                                                               (118, 6, 'N/A','1976-11-26','2024-11-26'), -- Nicaragua
                                                               (120, 6, 'N/A','1976-11-26','2024-11-26'), -- Nigeria
                                                               (125, 6, 'N/A','1976-11-26','2024-11-26'), -- Pakistan
                                                               (126, 6, 'N/A','1976-11-26','2024-11-26'), -- Palau
                                                               (128, 6, 'N/A','1976-11-26','2024-11-26'), -- Panama
                                                               (130, 6, 'N/A','1976-11-26','2024-11-26'), -- Paraguay
                                                               (131, 6, 'N/A','1976-11-26','2024-11-26'), -- Peru
                                                               (132, 6, 'N/A','1976-11-26','2024-11-26'), -- Philippines
                                                               (139 ,6, 'N/A','1976-11-26','2024-11-26'), -- Saint Kitts and Nevis
                                                               (139 ,6, 'N/A','1976-11-26','2024-11-26'), -- Saint Lucia
                                                               (146, 6, 'N/A','1976-11-26','2024-11-26'), -- Senegal
                                                               (148, 6, 'N/A','1976-11-26','2024-11-26'), -- Seychelles
                                                               (149, 6, 'N/A','1976-11-26','2024-11-26'), -- Sierra Leone
                                                               (150, 6, 'N/A','1976-11-26','2024-11-26'), -- Singapore
                                                               (154, 6, 'N/A','1976-11-26','2024-11-26'), -- Somalia
                                                               (155, 6, 'N/A','1976-11-26','2024-11-26'), -- South Africa
                                                               (157, 6, 'N/A','1976-11-26','2024-11-26'), -- South Sudan
                                                               (159, 6, 'N/A','1976-11-26','2024-11-26'), -- Sri Lanka
                                                               (160, 6, 'N/A','1976-11-26','2024-11-26'), -- Sudan
                                                               (161, 6, 'N/A','1976-11-26','2024-11-26'), -- Suriname
                                                               (166, 6, 'N/A','1976-11-26','2024-11-26'), -- Tanzania
                                                               (170, 6, 'N/A','1976-11-26','2024-11-26'), -- Tonga
                                                               (176, 6, 'N/A','1976-11-26','2024-11-26'), -- Uganda
                                                               (180, 6, 'Chikungunya Virus in the US Virgin Islands (St Choix, St John and St Thomas in the Leeward Islands)','1976-11-26','2024-11-26'), -- United States of America
                                                               (184, 6, 'N/A','1976-11-26','2024-11-26'), -- Venezuela
                                                               (185, 6, 'N/A','1976-11-26','2024-11-26'), -- Vietnam
                                                               (186, 6, 'N/A','1976-11-26','2024-11-26'), -- Yemen
                                                               (187, 6, 'N/A','1976-11-26','2024-11-26'); -- Zambia

INSERT INTO country_disease (country_id, disease_id, note, start_date, end_date) VALUES
                                                                -- Yellow Fever occurrences
                                                                (5,  7, 'N/A', '1976-11-26','2024-11-26'), -- Angola
                                                                (6,  7, 'N/A', '1976-11-26','2024-11-26'),  -- Argentina
                                                                (26, 7, 'N/A', '1976-11-26', '2024-11-26'), -- Cameroon
                                                                (27, 7, 'N/A', '1976-11-26', '2024-11-26'), -- Central African Republic
                                                                (29, 7, 'N/A', '1976-11-26', '2024-11-26'), -- Chad
                                                                (32, 7, 'N/A', '1976-11-26', '2024-11-26'), -- Colombia
                                                                (47, 7, 'N/A', '1976-11-26', '2024-11-26'), -- Equatorial Guinea
                                                                (51, 7, 'N/A', '1976-11-26', '2024-11-26'), -- Ethiopia
                                                                (55, 7, 'N/A','1976-11-26', '2024-11-26'), -- Gabon
                                                                (56, 7, 'N/A','1976-11-26', '2024-11-26'), -- Gambia
                                                                (59, 7, 'N/A','1976-11-26', '2024-11-26'), -- Ghana
                                                                (63, 7, 'N/A','1976-11-26', '2024-11-26'), -- Guinea
                                                                (90, 7, 'N/A','1976-11-26','2024-11-26'), -- Liberia
                                                                (103, 7,'N/A','1976-11-26','2024-11-26'), -- Mauritania
                                                                (119, 7,'N/A','1976-11-26','2024-11-26'), -- Niger
                                                                (120, 7,'N/A','1976-11-26','2024-11-26'), -- Nigeria
                                                                (128, 7,'N/A','1976-11-26','2024-11-26'), -- Panama
                                                                (130, 7,'N/A','1976-11-26','2024-11-26'), -- Paraguay
                                                                (131, 7,'N/A','1976-11-26','2024-11-26'), -- Peru
                                                                (146, 7, 'N/A','1976-11-26','2024-11-26'), -- Senegal
                                                                (149, 7,'N/A','1976-11-26','2024-11-26'), -- Sierra Leone
                                                                (157, 7,'N/A','1976-11-26','2024-11-26'), -- South Sudan
                                                                (160,7,'N/A','1976-11-26','2024-11-26'), -- Sudan
                                                                (161, 7, 'N/A','1976-11-26','2024-11-26'), -- Suriname
                                                                (169,7, 'N/A','1976-11-26','2024-11-26'), -- Togo
                                                                (171,7, 'N/A','1976-11-26','2024-11-26'), -- Trinidad and Tobago
                                                                (176,7, 'N/A','1976-11-26','2024-11-26'), -- Uganda
                                                                (184,7, 'N/A','1976-11-26','2024-11-26'); -- Venezuela

SELECT * FROM country_disease;


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

