package com.ozruit.ozruit.fruit;

import com.ozruit.fruit.Fruit;
import com.ozruit.fruit.FruitController;
import com.ozruit.fruit.FruitService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(FruitController.class)
public class FruitControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FruitService fruitService;

    @Test
    public void test_fruits() throws Exception {
        given(fruitService.findByName(anyString())).willReturn(new Fruit("kiwi", "brown"));
        mockMvc.perform(get("/fruits/strawberry"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("brown"));
    }
}