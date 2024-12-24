package com.aivle.mini7.controller;

import com.aivle.mini7.service.NavigationService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/navigation")
public class NavigationController {
    private final NavigationService navigationService;

    public NavigationController(NavigationService navigationService) {
        this.navigationService = navigationService;
    }

    @GetMapping
    public ResponseEntity<String> getNavigation(
            @RequestParam("startLat") double startLat,
            @RequestParam("startLng") double startLng,
            @RequestParam("endLat") double endLat,
            @RequestParam("endLng") double endLng) {
        try {
            String navigationData = navigationService.getNavigationData(
                    startLat, startLng, endLat, endLng);
            return ResponseEntity.ok(navigationData);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("네비게이션 데이터 조회 실패: " + e.getMessage());
        }
    }
}

