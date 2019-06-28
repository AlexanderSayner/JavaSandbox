package sayner.sandbox.jsonmuster;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * This class describe model of response json
 */
@Component
public class ModelResponse {

    public ResponseEntity<Object> responseEntity(HttpStatus statusCode, String statusMessage, Object data, Object ex) throws IOException {
        Map<String, Object> map = new HashMap<>();

        /**
         * Add response labels
         */
        map.put("data", data);
        map.put("statusCode", statusCode);
        map.put("message", statusMessage);

        /**
         * Replace labels at beginning exception
         */
        if(ex != null) {
            map.put("exception", ex);
            map.remove("data", data);
        }

        return new ResponseEntity<>(map, statusCode);
    }


}
