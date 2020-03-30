package software.craftsmanship.scuser.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;
import software.craftsmanship.scuser.utils.BuilderUtilsKeycloak;
import software.craftsmanship.scuser.utils.BuilderUtilsResultAction;
;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Initialize spring context before the class
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("dev-local")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class UserControllerContextTest extends BuilderUtilsResultAction {

    @Autowired
    private BuilderUtilsKeycloak builderUtilsKeycloak;

    @Test
    public void testGetKeycloakAuthenticationToken() throws Exception {
        final String accessToken = builderUtilsKeycloak.getAccessToken();
        final MvcResult mvcResult = invokeGet("/api/users/connected-user", accessToken)
                .andExpect(status().isOk())
                .andExpect(jsonPath("email", is("test@gmail.com")))
                .andExpect(jsonPath("space.name", is("test_space")))
                .andReturn();
    }

}