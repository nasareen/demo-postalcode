package com.example.demopostalcode.controller;

import com.example.demopostalcode.model.User;
import com.example.demopostalcode.model.UsersResponse;
import com.example.demopostalcode.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Tuple;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
public class BaseController {

    @Autowired
    UserRepo repo;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody List<User> users){
        System.out.println("save");
        repo.saveAll(users);
        return ResponseEntity.ok().body(users.size()+" Users Saved!");
    }

    @GetMapping("/users")
    public ResponseEntity<UsersResponse> getUsers(@RequestParam int from, @RequestParam int to){
        List<User> users = repo.findUsersByPOstalCodeRange(from,to);
        UsersResponse response = new UsersResponse();
        response.setUsers(users.stream().sorted(Comparator.comparing(User::getName)).map(User::getName).collect(Collectors.toList()));
        int result = users.stream()
                .reduce(0, (partialAgeResult, user) -> partialAgeResult + user.getName().length(), Integer::sum);
        System.out.println(result);
        response.setCountOfCharacters(users.stream()
                .reduce(0, (partialAgeResult, user) -> partialAgeResult + user.getName().length(), Integer::sum));
        return ResponseEntity.ok().body(response);
    }


}
