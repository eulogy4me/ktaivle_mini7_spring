package com.aivle.mini7.service;

import com.aivle.mini7.client.dto.FastApiResponse;
import com.aivle.mini7.dto.LogDto;
import com.aivle.mini7.model.InputLog;
import com.aivle.mini7.model.OutputLog;
import com.aivle.mini7.repository.LogRepository;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class LogService {

    private final LogRepository logRepository;

    @Transactional(readOnly = true)
    public Page<LogDto.ResponseInputList> getLogList(Pageable pageable) {
        Page<InputLog> inputlogs = logRepository.findAll(pageable);

        return inputlogs.map(LogDto.ResponseInputList::of);
    }

    @Transactional(readOnly = true)
    public Page<LogDto.ResponseOutputList> getLogList(Pageable pageable) {
        Page<OutputLog> outputlogs = logRepository.findAll(pageable);

        return outputlogs.map(LogDto.ResponseOutputList::of);
    }

    /**
     * 원래 이렇게 나쁜 모듈로 구현하면 안된다.
     * 현재 프로젝트 완료를 위해 급급한 소스이다.
     * @param Responses
     * @param request
     * @param latitude
     * @param longitude
     */
    public void saveLog(List<FastApiResponse> Responses, String request, double latitude, double longitude, int emClass) {
        Log InputLogger = Log.builder()
                .inputText(request)
                .inputLatitude(latitude)
                .inputLongitude(longitude)
                .emClass(emClass)
                .datetime(String.valueOf(new Date()))
                .build();

        Log OutputLogger = Log.builder()
                .build();

        int count = 1;

        for(FastApiResponse Hospitals : Responses) {
            log.info("Hospitals: {}", Hospitals);
            switch (count) {
                case 1:
                    // OutputLogger
                    break;
                case 2:

                    break;
                case 3:
                    // Logg.setHospital3(Hospitals.getHospitalName());
                    // Logg.setAddr3(Hospitals.getAddress());
                    // Logg.setTel3(Hospitals.getPhoneNumber1());
                    break;
            }
            count++;

        }

        logRepository.save(Logg);
    }

}
