package dev.seratt.mailing_system_main.communication;

import dev.seratt.mailing_system_main.entity.Group;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;


@Component
public class GroupCommunication {
    private static RestTemplate restTemplate = new RestTemplate();

    private final String URL = "http://localhost:8080/api/groups";
    public List<Group> getAllGroups(){
        ResponseEntity<List<Group>> responseEntity =
                restTemplate.exchange(URL, HttpMethod.GET,
                        null, new ParameterizedTypeReference<List<Group>>() {});
        List<Group> groups = responseEntity.getBody();
        return groups;
    }

    public Group getGroup(int id){
        Group group = restTemplate.getForObject(URL + "/" + id,
         Group.class);
        return group;
    }

    public void saveGroup(Group group){
        int id = group.getId();

        if(id == 0){
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(URL, group,
             String.class);
            System.out.println("New group added");
            System.out.println(responseEntity.getBody());
            return;
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Group> entity = new HttpEntity<Group>(group, headers);
        restTemplate.exchange(URL, HttpMethod.PUT, entity, String.class).getBody();
        System.out.println("Group with id " + id + " was updated");

    }

    public void deleteGroup(int id){
        restTemplate.delete(URL+"/"+id);
    }

    public List<Group> findGroupsBySearch(String searchText) {
        ResponseEntity<List<Group>> responseEntity =
                restTemplate.exchange(URL+"/search/"+searchText, HttpMethod.GET,
                        null, new ParameterizedTypeReference<List<Group>>() {});
        List<Group> groups = responseEntity.getBody();
        return groups;
    }

    public void addUserToGroup(int userId, int groupId) {
        restTemplate.put(URL+"/"+groupId+"/users/"+userId, null);
    }

    public void removeUserFromGroup(int userId, int groupId) {
        restTemplate.delete(URL+"/"+groupId+"/users/"+userId);
    }
}
