package com.aivle.mini7.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "responses")
public class ResponseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "request_id")
    private RequestEntity request;

    private String summary;              // FastAPI 분석 요약
    private String keywords;             // 추출된 키워드들
    private String dangerLevel;          // 위험 등급
    private Double userLatitude;         // 신고자 위도
    private Double userLongitude;        // 신고자 경도
    private String recommendedHospitals;  // 추천 병원 목록
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime processedAt;
}