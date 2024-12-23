package com.aivle.mini7.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NavigationService {
    private static final String NAVER_MAPS_BASE_URL = "https://naveropenapi.apigw.ntruss.com/map-direction/v1/driving";

    @Value("${naver.api.client-id}")
    private String NAVER_CLIENT_ID;

    @Value("${naver.api.client-secret}")
    private String NAVER_CLIENT_SECRET;

    public String getNavigationData(double startLat, double startLng, double endLat, double endLng) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = String.format("%s?start=%f,%f&goal=%f,%f",
                    NAVER_MAPS_BASE_URL, startLng, startLat, endLng, endLat);

            HttpHeaders headers = new HttpHeaders();
            headers.set("X-NCP-APIGW-API-KEY-ID", NAVER_CLIENT_ID);
            headers.set("X-NCP-APIGW-API-KEY", NAVER_CLIENT_SECRET);

            HttpEntity<String> entity = new HttpEntity<>(headers);
            ResponseEntity<String> response = restTemplate.exchange(
                    url, HttpMethod.GET, entity, String.class);

            if (!response.getStatusCode().is2xxSuccessful()) {
                throw new RuntimeException("네이버 API 호출 실패: " + response.getStatusCode());
            }

            return response.getBody();
        } catch (Exception e) {
            throw new RuntimeException("네비게이션 데이터 조회 실패: " + e.getMessage(), e);
        }
    }
}