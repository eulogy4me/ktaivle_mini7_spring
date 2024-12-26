package com.aivle.mini7.controller;

import com.aivle.mini7.client.api.FastApiClient;
import com.aivle.mini7.client.dto.FastApiResponse;
import com.aivle.mini7.entity.RequestEntity;
import com.aivle.mini7.entity.ResponseEntity;
import com.aivle.mini7.service.NavigationService;
import com.aivle.mini7.service.RequestResponseService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class FastAPIController {

    private final FastApiClient fastApiClient;
    private final NavigationService navigationService;
    private final RequestResponseService requestResponseService; // 추가

    @Value("${naver.api.client-id}")
    private String NAVER_CLIENT_ID;


    public FastAPIController(FastApiClient fastApiClient,
                             NavigationService navigationService,
                             RequestResponseService requestResponseService) {
        this.fastApiClient = fastApiClient;
        this.navigationService = navigationService;
        this.requestResponseService = requestResponseService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/recommend_hospital")
    public String recommendHospital(
            @RequestParam("filename") String filename,
            @RequestParam(required = false) String summary,
            @RequestParam(required = false) Double latitude,
            @RequestParam(required = false) Double longitude,
            @RequestParam("hospitalCount") int hospitalCount,
            Model model) {
        try {
            // 1. 요청 데이터 DB 저장
            RequestEntity savedRequest = requestResponseService.saveRequest(
                    filename, summary, latitude, longitude
            );

            // 2. FastAPI 호출
            FastApiResponse response = fastApiClient.throughPipeLine(filename);

            // 3. 병원 리스트 제한
            List<FastApiResponse.HospitalResponse> limitedHospitals = response.getRecommendedHospitals()
                    .stream()
                    .limit(hospitalCount)
                    .toList();

            // 4. 응답 저장 (FastAPI 응답 전체를 전달)
            ResponseEntity savedResponse = requestResponseService.saveResponse(
                    savedRequest,
                    response
            );

            // 5. 네비게이션 경로 데이터 가져오기
            String navigationData = navigationService.getNavigationData(
                    response.getAudioLatitude(),
                    response.getAudioLongitude(),
                    limitedHospitals.get(0).getHospitalLatitude(),
                    limitedHospitals.get(0).getHospitalLongitude()
            );

            // 6. Model에 데이터 추가
            model.addAttribute("summary", response.getSummary());
            model.addAttribute("keywords", response.getKeyword());
            model.addAttribute("dangerLevel", response.getDangerLevel());
            model.addAttribute("hospitals", limitedHospitals);
            model.addAttribute("navigationData", navigationData);
            model.addAttribute("naverClientId", NAVER_CLIENT_ID);
            model.addAttribute("userLatitude", response.getAudioLatitude());
            model.addAttribute("userLongitude", response.getAudioLongitude());

            return "recommend_hospital";

        } catch (Exception e) {
            model.addAttribute("error", "FastAPI 호출 중 오류가 발생했습니다. " + e.getMessage());
            return "error";
        }
    }


    @GetMapping("/test-fastapi")
    public String testFastApi() {
        try {
            String response = fastApiClient.testFastApi();
            System.out.println("FastAPI Response: " + response);
            return response; // JSON 응답을 그대로 반환
        } catch (Exception e) {
            e.printStackTrace();
            return "FastAPI 호출 실패: " + e.getMessage();
        }
    }
}