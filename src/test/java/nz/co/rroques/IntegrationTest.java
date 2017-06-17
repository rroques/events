package nz.co.rroques;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
@ActiveProfiles("dev")
public class IntegrationTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void before() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void shouldCreateEvent() throws Exception {
        mockMvc.perform(
                post("/events")
                        .content("{ \"name\": \"myName\" }")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andDo(MockMvcResultHandlers.print()).andExpect(status().isCreated());
    }

    @Test
    public void shouldFailCreateEventWithEmptyName() throws Exception {
        mockMvc.perform(
                post("/events")
                        .content("{ \"name\": \"\" }")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andDo(MockMvcResultHandlers.print()).andExpect(status().isBadRequest());
    }

    @Test
    public void shouldFailCreateEventWithNullName() throws Exception {
        mockMvc.perform(
                post("/events")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andDo(MockMvcResultHandlers.print()).andExpect(status().isBadRequest());
    }

    @Test
    public void shouldGetEventsPaginated() throws Exception {
        mockMvc.perform(
                get("/events")
                        .param("page", "1")
                        .param("size", "10")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andDo(MockMvcResultHandlers.print()).andExpect(status().isOk());
    }

    @Test
    public void shouldGetAllEvents() throws Exception {
        mockMvc.perform(
                get("/events")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andDo(MockMvcResultHandlers.print()).andExpect(status().isOk());
    }

}
