package com.aivle.mini7.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "outputlog")
public class OutputLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String hospital;

    @Column(nullable = false)
    private String addr;

    @Column(nullable = false)
    private String tel;

    @Column(nullable = false)
    private double HaversineKm;

    @Column(nullable = false)
    private double DistanceKm;

    @Column(nullable = false)
    private double Latitude;

    @Column(nullable = false)
    private double Longitude;

    @Column(nullable = false)
    private String EmergencyType;
}