USE WelshBlood;

-- Adding missing indexes for better performance
CREATE INDEX IF NOT EXISTS idx_donor_travelled_donor_id ON donor_travelled_to_country(donor_id);
CREATE INDEX IF NOT EXISTS idx_donor_travelled_country_id ON donor_travelled_to_country(country_id);
CREATE INDEX IF NOT EXISTS idx_regions_country_id ON regions(country_id);
CREATE INDEX IF NOT EXISTS idx_donor_travelled_composite ON donor_travelled_to_country(donor_id, country_id);
CREATE INDEX IF NOT EXISTS idx_donor_travelled_end_date ON donor_travelled_to_country(end_date);
CREATE INDEX IF NOT EXISTS idx_country_disease_country ON country_disease(country_id);
CREATE INDEX IF NOT EXISTS idx_country_disease_region ON country_disease(region_id);

-- Analyse tables 
ANALYZE TABLE countries;
ANALYZE TABLE regions;
ANALYZE TABLE donor_travelled_to_country;
ANALYZE TABLE donor;
ANALYZE TABLE questions;
ANALYZE TABLE country_disease;

-- Show current index status
SELECT 
    table_name,
    index_name,
    column_name,
    seq_in_index,
    cardinality,
    sub_part,
    packed,
    null,
    index_type
FROM information_schema.statistics 
WHERE table_schema = 'WelshBlood'
AND table_name IN ('donor_travelled_to_country', 'regions', 'country_disease')
ORDER BY table_name, seq_in_index;

-- Test query performance
EXPLAIN SELECT country_id, country_name FROM countries;
EXPLAIN SELECT * FROM donor_travelled_to_country WHERE donor_id = 1;
EXPLAIN SELECT r.region_id, c.country_id, c.country_name, r.region_name
FROM regions r
LEFT JOIN countries c ON c.country_id = r.country_id
WHERE r.region_id = 1;

-- Create performance monitoring view
DROP VIEW IF EXISTS v_index_performance;
CREATE VIEW v_index_performance AS
SELECT 
    t.table_name,
    s.index_name,
    s.column_name,
    s.cardinality,
    t.table_rows,
    ROUND((s.cardinality / t.table_rows) * 100, 2) as selectivity_percent
FROM information_schema.tables t
JOIN information_schema.statistics s ON t.table_name = s.table_name
WHERE t.table_schema = 'WelshBlood'
AND s.table_schema = 'WelshBlood'
AND s.index_name != 'PRIMARY'
ORDER BY t.table_name, s.index_name;

SELECT * FROM v_index_performance; 