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

INSERT INTO diseases (disease_name) VALUES
                                        ('Malaria'),
                                        ('Dengue Fever'),
                                        ('Zika Virus'),
                                        ('Chikungunya');


INSERT INTO country_disease (country_id, disease_id, note) VALUES

-- Malaria occurrences
(4, 1, 'Whole country before 2006'),
(13, 1, 'Western region 2008-2012'),
(17, 1, 'Rural areas 2005-2020'),
(8, 1, 'Highland regions 2010-2015'),
(21, 1, 'Southern regions before 2000'),
(22, 1, 'Yunnan province 2000-2010'),

-- Dengue Fever occurrences
(2, 2, 'Coastal areas 2015-2020'),
(6, 2, 'Northern regions 2018-2021'),
(10, 2, 'Lowland regions 2010-2015'),
(15, 2, 'Urban areas 2012-2020'),
(21, 2, 'Urban areas in Alexandria 2012-2015'),
(22, 2, 'Southern China 2010-2018'),

-- Zika Virus occurrences
(6, 3, 'Entire country 2015-2017'),
(11, 3, 'Eastern provinces 2016-2018'),
(14, 3, 'Capital city 2015-2016'),
(9, 3, 'Southern region 2017-2019'),
(21, 3, 'Northern Egypt 2016-2017'),
(22, 3, 'Guangxi region 2015-2017'),

-- Chikungunya occurrences
(12, 4, 'Central India 2006-2012'),
(19, 4, 'Darfur region 2005-2010'),
(5, 4, 'Northern Chad 2007-2015'),
(20, 4, 'Lake Victoria region 2010-2015'),
(21, 4, 'Rural Nile Delta 2010-2013'),
(22, 4, 'Eastern provinces 2014-2018');


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



