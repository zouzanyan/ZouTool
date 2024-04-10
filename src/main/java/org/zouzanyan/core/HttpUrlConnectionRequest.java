package org.zouzanyan.core;

import org.zouzanyan.base.AbstractRequest;
import org.zouzanyan.util.StringTool;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

public class HttpUrlConnectionRequest extends AbstractRequest {
    @Override
    public String doRequest(String requestUrl, String requestMethod, String requestBody) {

        try {
            URL url = new URL(requestUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            String upperCaseRequestMethod = requestMethod.toUpperCase(Locale.ROOT);

            connection.setRequestMethod(upperCaseRequestMethod);
            connection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            connection.setConnectTimeout(20000);
            connection.setReadTimeout(20000);

            if (!StringTool.isWindy(requestBody) && "POST".equals(requestMethod) || "PUT".equals(requestMethod)) {
                connection.setDoOutput(true);
                try (OutputStream os = connection.getOutputStream()) {
                    String encodeStr = URLEncoder.encode(requestBody, "UTF-8");
                    byte[] input = encodeStr.getBytes(StandardCharsets.UTF_8);
                    os.write(input, 0, input.length);
                }
            }

            StringBuilder response = new StringBuilder();
            try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
            }

            return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
