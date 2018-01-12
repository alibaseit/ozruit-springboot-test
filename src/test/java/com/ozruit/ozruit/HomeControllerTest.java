package com.ozruit.ozruit;

import com.ozruit.HomeController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(HomeController.class)
public class HomeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getHome() throws Exception {
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/home"))
                .andExpect(status().isOk());
        assertThat(resultActions.andReturn()
                .getResponse()
                .getContentAsString()
        ).isEqualToIgnoringCase("Welcome to ozru it");
    }

}