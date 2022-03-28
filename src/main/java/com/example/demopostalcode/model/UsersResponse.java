package com.example.demopostalcode.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

import java.util.List;

@Data
@Setter
public class UsersResponse {
    List<String> users;
    int countOfCharacters;
}
