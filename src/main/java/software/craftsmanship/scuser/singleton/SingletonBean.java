package software.craftsmanship.scuser.singleton;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import okhttp3.OkHttpClient;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Data
@Component
@Scope("singleton")
public class SingletonBean {

    private ModelMapper modelMapper;
    private OkHttpClient httpClient;
    private ObjectMapper objectMapper;

    public SingletonBean(){
        modelMapper=new ModelMapper();
        httpClient=new OkHttpClient();
        objectMapper=new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

}
