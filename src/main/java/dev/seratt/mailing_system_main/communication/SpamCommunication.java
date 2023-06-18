package dev.seratt.mailing_system_main.communication;

import dev.seratt.mailing_system_main.entity.Spam;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class SpamCommunication {
    private static RestTemplate restTemplate = new RestTemplate();

    private final String URL = "http://localhost:8080/api/spam";

    public List<Spam> getAllSpam(){
        ResponseEntity<List<Spam>> responseEntity =
                restTemplate.exchange(URL, HttpMethod.GET,
                        null, new ParameterizedTypeReference<List<Spam>>() {});
        List<Spam> spams = responseEntity.getBody();
        return spams;
    }

    public Spam getSpam(int id) {
        Spam spam = restTemplate.getForObject(URL + "/" + id,
                Spam.class);
        return spam;
    }

    public void saveSpam(Spam spam, int groupId){
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(URL+"/group/"+groupId, spam,
                    String.class);
        System.out.println(responseEntity.getBody());
    }
}
