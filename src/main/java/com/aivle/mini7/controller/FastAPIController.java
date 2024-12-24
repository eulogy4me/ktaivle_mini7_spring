package com.aivle.mini7.controller;

import com.aivle.mini7.client.api.FastApiClient;
import com.aivle.mini7.client.dto.FastApiResponse;
import com.aivle.mini7.service.NavigationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;

@Controller
public class FastAPIController {

    private final FastApiClient fastApiClient;
    private final NavigationService navigationService;

    @Value("${naver.api.client-id}")
    private String NAVER_CLIENT_ID;

    public FastAPIController(FastApiClient fastApiClient, NavigationService navigationService) {
        this.fastApiClient = fastApiClient;
        this.navigationService = navigationService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/recommend_hospital")
    public String recommendHospital(
            @RequestParam("filename") String filename,
            @RequestParam("hospitalCount") int hospitalCount,
            Model model) {
        try{
            // FastAPI 호출
            FastApiResponse response = fastApiClient.throughPipeLine(filename);

            // 병원 리스트 상위 hospitalCount 개로 제한
            List<FastApiResponse.HospitalResponse> limitedHospitals = response.getRecommendedHospitals()
                    .stream()
                    .limit(hospitalCount)
                    .toList();

            // 네비게이션 경로 데이터 가져오기
            String navigationData = navigationService.getNavigationData(
                    response.getAudioLatitude(),
                    response.getAudioLongitude(),
                    limitedHospitals.get(0).getHospitalLatitude(),
                    limitedHospitals.get(0).getHospitalLongitude()
            );


            //JSON 데이터를 Model에 추가
            model.addAttribute("summary", response.getSummary());
            model.addAttribute("keywords", response.getKeyword());
            model.addAttribute("dangerLevel", response.getDangerLevel());
            model.addAttribute("hospitals", limitedHospitals);
            model.addAttribute("navigationData", navigationData);
            model.addAttribute("naverClientId", NAVER_CLIENT_ID);

            System.out.println("Navigation Data" + navigationData);

            return "recommend_hospital"; // 추천 결과를 보여줄 HTML 파일 이름

        } catch (Exception e) {
            model.addAttribute("error", "FastAPI 호출 중 오류가 발생했습니다. " + e.getMessage());
            return "error"; // 오류 화면
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


