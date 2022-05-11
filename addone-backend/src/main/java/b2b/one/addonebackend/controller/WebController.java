package b2b.one.addonebackend.controller;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import b2b.one.addonebackend.models.PostRequest;

@RestController
public class WebController {

    @CrossOrigin
    @RequestMapping(value = "/validateToken", method = RequestMethod.POST)
    public ResponseEntity validateToken(@RequestBody PostRequest payload) {
        boolean valid = authWithOne(payload.getToken(), payload.getTenantKey(), payload.getPluginId());

        if (valid) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
    }

    private boolean authWithOne(String token, String tenantKey, String pluginId) {
        try {
            URL url = new URL("https://backoffice-price.one.unity.pl/api/v1/plugins/integrations/public/auth-with-one/"
                    + pluginId + "/validateToken");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            try {
                con.setRequestMethod("POST");
                con.setRequestProperty("Content-Type", "application/json; utf-8");
                con.setRequestProperty("Accept", "application/json");
                con.addRequestProperty("one-tenant", tenantKey);
                con.setDoOutput(true);

                String jsonInputString = "{\"token\": \"" + token + "\"}";
                OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream(), "UTF-8");
                out.write(jsonInputString);
                out.close();

                con.connect();

                if (con.getResponseCode() == 200) {
                    return true;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                con.disconnect();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

}