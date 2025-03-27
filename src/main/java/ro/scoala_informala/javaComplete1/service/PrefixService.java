package ro.scoala_informala.javaComplete1.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ro.scoala_informala.javaComplete1.model.Idd;
import ro.scoala_informala.javaComplete1.model.Name;
import ro.scoala_informala.javaComplete1.model.Prefix;

import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class PrefixService {

    private static final String PREFIX_API_URL = "https://restcountries.com/v3.1/all?fields=name,idd";
    private static final String PREFIX_API_BY_COUNTRY_URL = "https://restcountries.com/v3.1/name/";

    public List<Prefix> getAllPrefixes() {
        RestTemplate restTemplate = new RestTemplate();

        try {
            ResponseEntity<Prefix[]> response
                    = restTemplate.getForEntity(PREFIX_API_URL, Prefix[].class);

            return Arrays.asList(response.getBody());
        } catch (Exception e) {
            log.info("Could not retrieve prefixes from the external api.", e);
            return List.of(new Prefix() {{
                setIdd(new Idd() {{
                    setRoot("+40");
                }});
                setName(new Name() {{
                    setCommon("Romania");
                }});
            }});
        }
    }

    public List<Prefix> getAllPrefixes(String country) {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Prefix[]> response
                = restTemplate.getForEntity(PREFIX_API_BY_COUNTRY_URL + country, Prefix[].class);

        return Arrays.asList(response.getBody());
    }
}
