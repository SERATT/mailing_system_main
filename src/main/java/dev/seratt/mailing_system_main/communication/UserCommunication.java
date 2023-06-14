package dev.seratt.mailing_system_main.communication;

import dev.seratt.mailing_system_main.entity.User;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class UserCommunication {
    private static RestTemplate restTemplate = new RestTemplate();

    private final String URL = "http://localhost:8080/api/users";
    public List<User> getAllUsers(){
        ResponseEntity<List<User>> responseEntity =
                restTemplate.exchange(URL, HttpMethod.GET,
                        null, new ParameterizedTypeReference<List<User>>() {});
        List<User> users = responseEntity.getBody();
        return users;
    }

    public User getUser(int id){
        User user = restTemplate.getForObject(URL + "/" + id,
         User.class);
        return user;
    }

    public void saveUser(User user){
        int id = user.getId();

        if(id == 0){
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(URL, user,
             String.class);
            System.out.println("New user added");
            System.out.println(responseEntity.getBody());
            return;
        }
        restTemplate.put(URL, user);
        System.out.println("User with id " + id + " was updated");

    }

    public void deleteUser(int id){
        restTemplate.delete(URL+"/"+id);
        System.out.println("User with id " + id + " was deleted");
    }

    public List<User> findUsersBySearch(String searchText) {
        ResponseEntity<List<User>> responseEntity =
                restTemplate.exchange(URL+"/search/"+searchText, HttpMethod.GET,
                        null, new ParameterizedTypeReference<List<User>>() {});
        List<User> users = responseEntity.getBody();
        return users;
    }
}
