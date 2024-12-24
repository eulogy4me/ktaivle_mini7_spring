package com.aivle.mini7.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "inputlog")
public class InputLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String datetime;

    @Column(name = "input_text", nullable = false)
    private String inputText;

    @Column(name = "input_latitude", nullable = false)
    private Double inputLatitude;

    @Column(name = "input_longitude", nullable = false)
    private Double inputLongitude;


    @Column(name = "em_class", nullable = false)
    private Integer emClass;
}