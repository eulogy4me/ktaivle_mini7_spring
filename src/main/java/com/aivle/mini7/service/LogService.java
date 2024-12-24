package com.aivle.mini7.service;

import com.aivle.mini7.client.dto.FastApiResponse;
import com.aivle.mini7.dto.LogDto;
import com.aivle.mini7.model.InputLog;
import com.aivle.mini7.model.OutputLog;
import com.aivle.mini7.repository.InputLogRepository;
import com.aivle.mini7.repository.OutputLogRepository;
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

    private final InputLogRepository inputLogRepository;
    private final OutputLogRepository outputLogRepository;

    @Transactional(readOnly = true)
    public Page<LogDto.ResponseInputList> getInputLogList(Pageable pageable) {
        Page<InputLog> inputLogs = inputLogRepository.findAll(pageable);
        return inputLogs.map(LogDto.ResponseInputList::of);
    }

    @Transactional(readOnly = true)
    public Page<LogDto.ResponseOutputList> getOutputLogList(Pageable pageable) {
        Page<OutputLog> outputLogs = outputLogRepository.findAll(pageable);
        return outputLogs.map(LogDto.ResponseOutputList::of);
    }


    @param responses
    @param request
    @param latitude
    @param longitude
    @param emClass
    public void saveLog(List<FastApiResponse> responses, String request, double latitude, double longitude, int emClass) {
        InputLog inputLog = InputLog.builder()
                .inputText(request)
                .inputLatitude(latitude)
                .inputLongitude(longitude)
                .emClass(emClass)
                .datetime(new Date().toString())
                .build();

        inputLogRepository.save(inputLog);

        OutputLog outputLog = new OutputLog();
        int count = 1;

        for (FastApiResponse response : responses) {
            log.info("Hospital Response: {}", response);

            switch (count) {
                case 1:
                    outputLog.setHospital1(response.getHospitalName());
                    outputLog.setAddr1(response.getAddress());
                    outputLog.setTel1(response.getPhoneNumber1());
                    break;
                case 2:
                    outputLog.setHospital2(response.getHospitalName());
                    outputLog.setAddr2(response.getAddress());
                    outputLog.setTel2(response.getPhoneNumber1());
                    break;
                case 3:
                    outputLog.setHospital3(response.getHospitalName());
                    outputLog.setAddr3(response.getAddress());
                    outputLog.setTel3(response.getPhoneNumber1());
                    break;
            }
            count++;
        }

        outputLogRepository.save(outputLog);
    }
}
