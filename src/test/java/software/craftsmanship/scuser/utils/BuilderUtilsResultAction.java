package software.craftsmanship.scuser.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.MultiValueMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@AutoConfigureMockMvc
public class BuilderUtilsResultAction {

    @Autowired
    private MockMvc mockMvc;

    public ResultActions invokeGet(final String url, final String token, final MultiValueMap<String, String> parameters) throws Exception {
        return mockMvc.perform(get(url)
                .header("Authorization", "Bearer " + token)
                .params(parameters)
        );
    }

    public ResultActions invokeGet(final String url, final String token) throws Exception {
        return mockMvc.perform(get(url)
                .header("Authorization", "Bearer " + token)
        );
    }
}
