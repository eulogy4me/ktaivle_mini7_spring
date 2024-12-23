package com.aivle.mini7.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.util.List;

@Data
public class FastApiResponse {

    private String summary; // JSON 파싱 후 저장될 Summary 객체

    private List<String> keyword;


    @JsonProperty("danger_level")
    private String dangerLevel; // 응급 등급

    @JsonProperty("audio_latitude")
    private double audioLatitude; // 위도

    @JsonProperty("audio_longitude")
    private double audioLongitude; // 경도

    @JsonProperty("recommended_hospitals")
    private List<HospitalResponse> recommendedHospitals; // 추천 병원 리스트

    @Data
    public static class HospitalResponse {
        @JsonProperty("haversine_km")
        private double haversineKm;

        @JsonProperty("distance_km")
        private double distanceKm;

        @JsonProperty("hospital_name")
        private String hospitalName;

        private String address;

        @JsonProperty("emergency_type")
        private String emergencyType;

        private String phone1;
        private String phone3;
        @JsonProperty("hospital_latitude")
        private double hospitalLatitude;
        @JsonProperty("hospital_longitude")
        private double hospitalLongitude;
    }
}


