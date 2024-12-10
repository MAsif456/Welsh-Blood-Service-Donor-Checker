package uk.ac.cf.spring.welshbloodserviceteam15.Home;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class HomeControllerTests {

    @Autowired
    private MockMvc mvc;


    @Test
    public void shouldRedirectToHome() throws Exception {
        // Given
        // When
        MvcResult result = mvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/home"))
                .andReturn();

        // Then
        String redirectedUrl = result.getResponse().getRedirectedUrl();
        assertTrue(redirectedUrl.equals("/home"));
    }

    @Test
    public void shouldGetHome() throws Exception {
        // Given
        // When
        MvcResult result = mvc.perform(get("/home"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        // Then
        assertTrue(content.contains("<title>Welsh Blood Home Page</title>"));
    }
}
