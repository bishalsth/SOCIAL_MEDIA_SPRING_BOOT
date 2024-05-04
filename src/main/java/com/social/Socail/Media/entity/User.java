package com.social.Socail.Media.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "First Name  cannot be blank")
    private String firstName;

    @NotBlank(message = "Last Name  cannot be blank")
    private String lastName;

    @NotBlank(message = "Email  cannot be blank")
    @Email
    private String email;

    @NotBlank(message = "Password  cannot be blank")
    private String password;

    private String gender;
    private List<Long> followers = new ArrayList<>();
    private List<Long> following = new ArrayList<>();

    @JsonIgnore
    @ManyToMany
    private List<Post> savedPost = new ArrayList<>();
}
