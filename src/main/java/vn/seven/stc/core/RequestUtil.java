package vn.seven.stc.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class RequestUtil {

    private static final Logger logger = LoggerFactory.getLogger(RequestUtil.class);

    public static String coreGETRequest(String sUrl, String apiKey){
        StringBuilder result = new StringBuilder();
        try {
            URL url = new URL(sUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json");
            if(apiKey != null){
                conn.setRequestProperty("Authorization", apiKey);
            }

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            while ((output = br.readLine()) != null) {
                result.append(output);
            }
            //System.out.println("result = " + result);
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    private static <T> T objectMapper(String json, Class<?> type){
        try{
            ObjectMapper mapper = new ObjectMapper();
            Object o =  mapper.readValue(json, type);
            return (T) o;
        }catch (Exception ex){
            ex.printStackTrace();
            logger.error(ex.toString());
        }

        return null;
    }

    private static <T> List<T> listMapper(String json, Class<?> type){
        try{
            ObjectMapper mapper = new ObjectMapper();
            List<Object> o = mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(List.class, type));
            return (List<T>) o;
        }catch (Exception ex){
            ex.printStackTrace();
            logger.error(ex.toString());
        }
        return null;
    }
}
