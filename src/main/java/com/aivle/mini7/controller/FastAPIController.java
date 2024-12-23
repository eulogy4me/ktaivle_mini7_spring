package com.aivle.mini7.controller;

import com.aivle.mini7.client.api.FastApiClient;
import com.aivle.mini7.client.dto.FastApiResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FastAPIController {

    private final FastApiClient fastApiClient;

    public FastAPIController(FastApiClient fastApiClient) {
        this.fastApiClient = fastApiClient;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/recommend_hospital")
    public String recommendHospital(@RequestParam("filename") String filename, Model model) {
        try{
            // FastAPI 호출
            FastApiResponse response = fastApiClient.throughPipeLine(filename);

            //JSON 데이터를 Model에 추가
            model.addAttribute("summary", response.getSummary());
            model.addAttribute("keywords", response.getKeyword());
            model.addAttribute("dangerLevel", response.getDangerLevel());
            model.addAttribute("hospitals", response.getRecommendedHospitals());

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


