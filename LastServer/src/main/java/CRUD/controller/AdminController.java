package CRUD.controller;

import CRUD.model.User;
import CRUD.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/admin/*")
public class AdminController {
    @Autowired
    private UserService userService;

     @GetMapping("list")
    public ResponseEntity<List<User>> getCompanyList() {
         List list = userService.getAll();
        return new ResponseEntity<List<User>>(list,HttpStatus.OK);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Integer id) {
        User us = userService.getbyID(id);
         userService.delete(us);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
    @PostMapping("/save")
    public ResponseEntity<Void> saveOrUpdateCompany(@RequestBody User user) {
        userService.add(user);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getCompany(@PathVariable Integer id) {
        Object object = userService.getbyID(id);
         return new ResponseEntity<Object>(object, HttpStatus.OK);
    }

    @GetMapping("auth")
    public ResponseEntity getAuth() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName().toString();
        return new ResponseEntity(name,HttpStatus.OK);
    }

    @GetMapping("authRole")
    public ResponseEntity getRole() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User us = (User) userService.loadUserByUsername(auth.getName());
        String role = us.getRoles().toString();
        return new ResponseEntity(role,HttpStatus.OK);
    }

}
