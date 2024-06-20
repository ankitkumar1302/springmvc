package com.example.springmvc.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@Entity
public class Complaint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false, length = 1000)
    private String description;

    private String imageUrl;

    @Column(nullable = false)
    private LocalDateTime registrationDateTime;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private String userId;

    // Getters and Setters
}
