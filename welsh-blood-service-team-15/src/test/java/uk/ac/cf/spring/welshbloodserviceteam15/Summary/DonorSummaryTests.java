package uk.ac.cf.spring.welshbloodserviceteam15.Summary;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import uk.ac.cf.spring.welshbloodserviceteam15.Donor.Donor;
import uk.ac.cf.spring.welshbloodserviceteam15.Donor.DonorRepository;

import java.util.List;

import static org.awaitility.Awaitility.given;

@SpringBootTest
@AutoConfigureMockMvc
public class DonorSummaryTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private DonorRepository donorRepository;

    @Test
    public void shouldGetDonorInfo() throws Exception {

    }
}
