package software.craftsmanship.scuser.utils;

import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.craftsmanship.scuser.models.TokenStore;
import software.craftsmanship.scuser.singleton.SingletonBean;

import java.io.IOException;
import java.util.Objects;

@Service
public class BuilderUtilsKeycloak {

    @Autowired
    private SingletonBean singletonBean;

    public String getAccessToken() throws IOException {
        RequestBody formBody = new FormBody.Builder()
                .addEncoded("username", "test")
                .addEncoded("password", "1234")
                .addEncoded("client_id", "sc-user")
                .addEncoded("grant_type", "password")
                .build();

        Request request = new Request.Builder()
                .url("http://192.168.1.35:8099/auth/realms/sc-project/protocol/openid-connect/token")
                .header("ContentType", "application/x-www-form-urlencoded")
                .post(formBody)
                .build();

        try (final Response response = singletonBean.getHttpClient().newCall(request).execute()) {
            final TokenStore tokenStore = singletonBean.getObjectMapper().readValue(Objects.requireNonNull(response.body()).bytes(), TokenStore.class);
            return tokenStore.getAccess_token();
        }catch(IOException ex){

        }
        return null;
    }
}
