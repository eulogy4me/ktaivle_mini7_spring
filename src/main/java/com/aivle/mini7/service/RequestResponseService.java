package com.aivle.mini7.service;

import com.aivle.mini7.client.dto.FastApiResponse;
import com.aivle.mini7.entity.RequestEntity;
import com.aivle.mini7.entity.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Service
@Transactional
public class RequestResponseService {

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private ResponseRepository responseRepository;

    public RequestEntity saveRequest(String filename, String summary, Double latitude, Double longitude) {
        RequestEntity request = new RequestEntity();
        request.setFilename(filename);
        request.setSummary(summary);
        request.setLatitude(latitude);
        request.setLongitude(longitude);
        request.setRequestedAt(LocalDateTime.now());

        return requestRepository.save(request);
    }

    public ResponseEntity saveResponse(RequestEntity request, FastApiResponse fastApiResponse) {
        ResponseEntity response = new ResponseEntity();
        response.setRequest(request);
        response.setSummary(fastApiResponse.getSummary());

        // 키워드 리스트를 쉼표로 구분된 문자열로 변환
        String keywordsStr = String.join(", ", fastApiResponse.getKeyword());
        response.setKeywords(keywordsStr);

        response.setDangerLevel(fastApiResponse.getDangerLevel());
        response.setUserLatitude(fastApiResponse.getAudioLatitude());
        response.setUserLongitude(fastApiResponse.getAudioLongitude());

        // 병원 목록을 문자열로 변환하여 저장
        String hospitalListStr = fastApiResponse.getRecommendedHospitals().stream()
                .map(h -> h.getHospitalName())
                .collect(Collectors.joining(", "));
        response.setRecommendedHospitals(hospitalListStr);

        response.setProcessedAt(LocalDateTime.now());

        return responseRepository.save(response);
    }
}

