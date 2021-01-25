package wild.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.google.gson.*;

public class HttpTest {
    public static void main(String[] args) {
        String url = "http://127.0.0.1:8080/helloworld/rest/hello/json";
        HttpTest t = new HttpTest();
        for (int i =0; i<100; i++) {
            String json = t.get(url);
            //System.out.println(json);
            String sid = t.getId(json);
            System.out.println(sid);
        }
    }
    private String get(String request) {
        try {
            URL url = new URL(request);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setInstanceFollowRedirects(false);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "text/plain");
            connection.setRequestProperty("charset", "utf-8");
            connection.connect();

            try (final BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String inputLine;
                final StringBuilder content = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                return content.toString();
            } catch (final Exception ex) {
                ex.printStackTrace();
                return "";
            }
        }catch (Exception e){e.printStackTrace();}
        return "Error";
    }

    private String getId(String json){
        JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
        String val = jsonObject.get("value").getAsString();

//        // 1. JSON file to Java object
//        Object object = gson.fromJson(new FileReader("C:\\fileName.json"), Object.class);
//
//        JsonParser jsonParser = new JsonParser();
//        JsonObject jo = (JsonObject)jsonParser.parse(json);
//
//
//
//        ObjectMapper mapper = new ObjectMapper();
//        // convert from json
//        Id newId = mapper.readValue(jsonString, Id.class);
        return val;//newId.getValue(); // print "Hi , World!"
    }

    private class Id{
        private String key;
        private String value;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
