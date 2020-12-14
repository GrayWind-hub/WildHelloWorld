package wild.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpTest {
    public static void main(String[] args) {
        String url = "http://127.0.0.1:8080/helloworld/rest/hello/json";
        HttpTest t = new HttpTest();
        System.out.println(t.get(url));
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
}
