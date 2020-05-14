package CRUD.controller;

import CRUD.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@RestController
@RequestMapping("/admin/*")
public class AdminController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("list")
    public ResponseEntity<List<User>> getUserList() {
        String url = "http://localhost:8083/admin/list";
        List users = restTemplate.getForObject(url, List.class);

        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Integer id) {
        String url = "http://localhost:8083/admin/delete/" + id;
        restTemplate.delete(url);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Void> saveOrUpdateCompany(@RequestBody User user) {
        String url = "http://localhost:8083/admin/save";
        restTemplate.postForObject(url, user, User.class);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getCompany(@PathVariable Integer id) {
        String url = "http://localhost:8083/admin/" + id;
        User user = (User) restTemplate.getForObject(url, Object.class);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @GetMapping("auth")
    public ResponseEntity getAuth() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String loginUN = auth.getName();//get logged in username
        return new ResponseEntity(loginUN, HttpStatus.OK);
    }

    @GetMapping("authRole")
    public ResponseEntity getRole() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String loginUN = auth.getAuthorities().toString();//get logged in username
        return new ResponseEntity(loginUN, HttpStatus.OK);
    }

}
