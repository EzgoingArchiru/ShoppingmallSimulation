import io.github.ezgoingarchiru.shoppingmall.simulation.users.UserService;
import io.github.ezgoingarchiru.shoppingmall.simulation.users.dto.UserCreateRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;

    @Test
    void register_success() throws Exception {
        given(userService.createUser(any(UserCreateRequest.class)))
                .willReturn(1L);
        String requestJson = """
                    {
                        "name": "rem",
                        "email": "@rem@test.com"
                    }
                """;
        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/users/1"))
                .andExpect(jsonPath("$.id").value(1L));
    }
}
