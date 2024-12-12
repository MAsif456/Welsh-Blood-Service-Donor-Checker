package uk.ac.cf.spring.welshbloodserviceteam15.CountryDisease;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.validation.BindingResult;
import uk.ac.cf.spring.welshbloodserviceteam15.Country.CountryDisease;
import uk.ac.cf.spring.welshbloodserviceteam15.Country.CountryDiseaseController;
import uk.ac.cf.spring.welshbloodserviceteam15.Country.CountryDiseaseRepository;
import uk.ac.cf.spring.welshbloodserviceteam15.Country.CountryDiseaseService;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import uk.ac.cf.spring.welshbloodserviceteam15.Disease.DiseaseService;


@WebMvcTest(CountryDiseaseController.class)
public class CountryDiseaseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CountryDiseaseService countryDiseaseService;

    @MockBean
    private CountryDiseaseRepository countryDiseaseRepository;

    @MockBean
    private DiseaseService diseaseService;

    private CountryDisease validCountryDisease;


    @BeforeEach
    public void setUp() {
        validCountryDisease = new CountryDisease();
        validCountryDisease.setStartDate(LocalDate.now().minusMonths(1));
        validCountryDisease.setEndDate(LocalDate.now().plusDays(10)); // Valid end date
    }


    /**
     * This method checks whether the edit-disease form displays validation errors when the user inputs an invalid end date.
     *
     * @throws Exception if there is an error during the mock MVC request
     */
    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void testInvalidEndDate() throws Exception {
        // Given the admin inputs an invalid end date in the form
        MvcResult result = mockMvc.perform(post("/edit-disease/save")
                        .param("startDate", LocalDate.now().minusMonths(1).toString())
                        .param("endDate", LocalDate.now().minusMonths(2).toString()) // Invalid, as end date is before start date.
                        .with(csrf()))
                .andExpect(status().isOk())
                .andReturn();

        // When the user submits the form with an invalid "End Date"
        BindingResult bindingResult = (BindingResult) result.getModelAndView().getModel().get("org.springframework.validation.BindingResult.countryDisease");

        // Then an error message should be displayed indicating that the "End Date" must be a valid date after the "Start Date".
        assertTrue(bindingResult.hasFieldErrors("endDate"), "Expected validation error for endDate");
        assertFalse(bindingResult.hasFieldErrors("startDate"), "Did not expect validation error for startDate");
    }

    /**
     * This method checks whether the edit-disease form displays validation errors when the user inputs an invalid start date.
     *
     * @throws Exception if there is an error during the mock MVC request
     */
    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void testInvalidStartDate() throws Exception {
        // Given the admin inputs an invalid start date in the form
        MvcResult result = mockMvc.perform(post("/edit-disease/save")
                        .param("startDate", LocalDate.now().plusDays(1).toString()) // Invalid, as start date is in the future.
                        .param("endDate", LocalDate.now().plusMonths(1).toString()) // Valid end date.
                        .with(csrf()))
                .andExpect(status().isOk())
                .andReturn();

        // When the admin submits the form,
        BindingResult bindingResult = (BindingResult) result.getModelAndView().getModel().get("org.springframework.validation.BindingResult.countryDisease");


        // Then an error message should be displayed indicating that the "Start Date" must be a valid date in the past or present.
        assertTrue(bindingResult.hasFieldErrors("startDate"), "Expected validation error for startDate");
        assertFalse(bindingResult.hasFieldErrors("endDate"), "Did not expect validation error for endDate");
    }


    /**
     * This method checks whether the existing disease information is updated when the admin edits the edit-disease form.
     *
     * @throws Exception if there is an error during the mock MVC request
     */
    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void testEditDiseaseFormUpdatesExistingEntry() throws Exception {
        // Given the admin edits the edit-disease form with new information
        doNothing().when(countryDiseaseService).save(any(CountryDisease.class));

        // When the admin presses save
        mockMvc.perform(post("/edit-disease/save")
                        .param("startDate", LocalDate.now().minusMonths(2).toString())
                        .param("endDate", LocalDate.now().minusMonths(1).toString())
                        .param("country.countryId", "1")

                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/edit-disease/SuccessfulEdit"))
                .andReturn();

        // Then the existing Country-Disease entry should be updated with the new information
        verify(countryDiseaseService, times(1)).save(any(CountryDisease.class));
    }


    /**
     * This method checks whether the admin is redirected to the "Successful Edit" page after editing the edit-disease form.
     *
     * @throws Exception if there is an error during the mock MVC request
     */
    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void testRedirectToSuccessPageAfterEdit() throws Exception {
        // Given the admin inputs new data to edit and the new data has no validation errors,
        doNothing().when(countryDiseaseService).save(any(CountryDisease.class));

        // When the admin saves the form
        mockMvc.perform(post("/edit-disease/save")
                        .param("startDate", LocalDate.now().minusDays(10).toString()) // valid start date
                        .param("endDate", LocalDate.now().minusDays(2).toString()) // Valid end date
                        .param("country.countryId", "1")
                        .with(csrf()))
                .andDo(print())
                .andExpect(status().is3xxRedirection())

                // Then the admin should be redirected to the "Successful Edit" page for the selected country
                .andExpect(redirectedUrl("/edit-disease/SuccessfulEdit"));
    }

    /**
     * This method checks whether the admin stays on the same page when the admin inputs invalid data to edit.
     * as binding errors are present.
     *
     * @throws Exception if there is an error during the mock MVC request
     */
    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void testInvalidDataDoesNotRedirect() throws Exception {
        // Given the user inputs new data to edit and the new data has validation errors,
        MvcResult result = mockMvc.perform(post("/edit-disease/save")
                        .param("startDate", LocalDate.now().plusDays(1).toString()) // Invalid start date
                        .param("endDate", LocalDate.now().plusMonths(1).toString()) // Valid end date
                        .with(csrf()))
                .andExpect(status().isOk())

                .andExpect(MockMvcResultMatchers.view().name("CountryDisease/EditDiseaseForm"))
                .andReturn();

        // When the admin submits the form with invalid data
        BindingResult bindingResult = (BindingResult) result.getModelAndView().getModel().get("org.springframework.validation.BindingResult.countryDisease");

        // Then the admin should stay on the same page and sees validation errors
        assertTrue(bindingResult.hasFieldErrors("startDate"), "Expected validation error for startDate");
        assertFalse(bindingResult.hasFieldErrors("endDate"), "Did not expect validation error for endDate");
    }
}
