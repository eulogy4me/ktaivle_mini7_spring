package com.aivle.mini7.client.api;


import com.aivle.mini7.client.dto.FastApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * FastApiClient
 * FastAPI의 "/recommend_hospital/{filename}") 엔드포인트를 호출한다.
 */
@FeignClient(name = "fastApiClient", url = "${hospital.api.host}")
public interface FastApiClient {


     @GetMapping("/fastapi_hospital/{filename}")
     FastApiResponse throughPipeLine(@PathVariable("filename") String filename);


     @GetMapping("/test") // FastAPI의 엔드포인트와 매핑
     String testFastApi(); // JSON 응답을 String으로 받아 확인
}


