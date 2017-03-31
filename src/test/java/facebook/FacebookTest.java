package facebook;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by rizt on 3/30/2017.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class FacebookTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void homePageRedirects() throws Exception {
        mockMvc.perform(get("/")).andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/connect/facebook"));
    }

    @Test
    public void connectPageHasLink() throws Exception {
        mockMvc.perform(get("/connect/facebook")).andExpect(status().isOk())
                .andExpect(content().string(containsString("Connect to Facebook")));
    }

    @Test
    public void connectHandlerRedirects() throws Exception {
        mockMvc.perform(post("/connect/facebook"))
                .andExpect(redirectedUrlPattern("https://www.facebook.com/**"));
    }
}
