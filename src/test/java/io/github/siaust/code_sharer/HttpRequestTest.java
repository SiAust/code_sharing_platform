package io.github.siaust.code_sharer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class HttpRequestTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnStatus200() throws Exception {
        this.mockMvc.perform(get("/code/new")).andDo(print()).andExpect(status().isOk());
        this.mockMvc.perform(get("/code/latest")).andDo(print()).andExpect(status().isOk());

    }

    @Test
    public void shouldReturnStatus404() throws Exception {
        UUID uuid =  UUID.randomUUID();
        this.mockMvc.perform(get("/api/code/" + uuid)).andDo(print()).andExpect(status().isNotFound());
    }

    @Test
    public void shouldReturnUUID() throws Exception { // todo check for UUID key in JSON
        this.mockMvc.perform(post("/api/code/new")
                .content("{\n" +
                        "    \"code\":\"public static void main(String[] args) {}\",\n" +
                        "    \"time\": 200,\n" +
                        "    \"views\": 3\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }


}
