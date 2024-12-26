package com.aivle.mini7.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "requests")
public class RequestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String filename;
    private String summary;
    private Double latitude;
    private Double longitude;
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime requestedAt;

    @OneToOne(mappedBy = "request")
    private ResponseEntity response;
}