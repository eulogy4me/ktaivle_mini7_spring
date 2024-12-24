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
    private String hospital1;

    @Column(nullable = false)
    private String addr1;

    @Column(nullable = false)
    private String tel1;

    @Column(nullable = false)
    private String hospital2;

    @Column(nullable = false)
    private String addr2;

    @Column(nullable = false)
    private String tel2;

    @Column(nullable = false)
    private String hospital3;

    @Column(nullable = false)
    private String addr3;

    @Column(nullable = false)
    private String tel3;
}