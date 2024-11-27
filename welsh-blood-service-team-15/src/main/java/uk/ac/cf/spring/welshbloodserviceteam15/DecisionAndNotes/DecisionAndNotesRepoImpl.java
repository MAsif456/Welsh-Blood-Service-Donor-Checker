package uk.ac.cf.spring.welshbloodserviceteam15.DecisionAndNotes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public class DecisionAndNotesRepoImpl implements DecisionAndNotesRepository {
 private JdbcTemplate jdbcTemplate;


    @Autowired
    public DecisionAndNotesRepoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<DonorDetails> getDonorRecord(int donorId) {
        String sql = "SELECT * FROM donor WHERE donor_id = ?";
        try {
            DonorDetails donorDetails = jdbcTemplate.queryForObject(sql, new Object[]{donorId}, donorRowMapper());
            return Optional.of(donorDetails);  // Return an Optional containing the donorDetails if found
        } catch (EmptyResultDataAccessException e) {
            // Log the case when no donor is found
            System.err.println("No donor found for donorId=" + donorId);
            return Optional.empty();  // Return an empty Optional if no donor found
        }
    }

    @Transactional
    @Override
    public void updateDonorRecord(int donorId, String decision, String notes) {
        String sql = "UPDATE donor SET donor_decision = ?, donor_note = ? WHERE donor_id = ?";
        System.out.println("Executing SQL: " + sql);
        System.out.println("Parameters: donorId=" + donorId + ", decision=" + decision + ", notes=" + notes);
        try {
            int rowsUpdated = jdbcTemplate.update(sql, decision, notes, donorId);
            System.out.println("Rows updated: " + rowsUpdated);
            if (rowsUpdated == 0) {
                throw new RuntimeException("No record found for donorId=" + donorId);
            }
        } catch (DataAccessException e) {
            System.err.println("DataAccessException: " + e.getMessage());
            throw new RuntimeException("Failed to update donor record", e);
        }
    }

    public RowMapper<DonorDetails> donorRowMapper() {
        return (rs, rowNum) -> {
            DonorDetails donorDetails = new DonorDetails();
            donorDetails.setDonorId(rs.getInt("donor_id"));
            donorDetails.setFirstName(rs.getString("first_name"));
            donorDetails.setLastName(rs.getString("last_name"));
            donorDetails.setDonorDob(rs.getDate("donor_dob").toLocalDate());
            donorDetails.setDonorRisk(rs.getString("donor_risk"));
            donorDetails.setDonorDecision(rs.getString("donor_decision"));
            donorDetails.setDonorNote(rs.getString("donor_note"));
            return donorDetails;
        };
    }

    //This method is not needed as we are using queryForObject in getDonorRecord
    //This method is kept here for reference

   /* @Override
    public Optional<DonorDetails> findDonorById(int donorId) {
        String sql = "SELECT * FROM donor WHERE donor_id = ?";
        return jdbcTemplate.query(sql, new Object[]{donorId}, rs -> {
            //Checks if the result set has a record
            if (rs.next()) {
                //Then it maps the record to a DonorDetails object if one is found
                return Optional.of(new DonorDetails(
                        rs.getInt("donor_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getDate("donor_dob").toLocalDate(),
                        rs.getString("donor_risk"),
                        rs.getString("donor_decision"),
                        rs.getString("donor_note")
                ));
            }
            //If no record is found, it returns an empty optional
            return Optional.empty();
        });
    }*/


}


