package com.social.Socail.Media.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String chatName;
    private String chatImage;
    @ManyToMany
    private List<User> users= new ArrayList<>();
    private LocalDateTime timestamps;

    @OneToMany(mappedBy = "chat")
    private List<Message> messages= new ArrayList<>();
}
