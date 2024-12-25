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
// import java.util.stream.Collectors;

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
        return outputLogs.map(outputLog -> {
            List<LogDto.HospitalInfo> hospitals = List.of(LogDto.HospitalInfo.of(outputLog));
            return LogDto.ResponseOutputList.builder().hospitals(hospitals).build();
        });
    }

    public void saveLog(List<FastApiResponse> responses, String request, double latitude, double longitude, int emClass) {
        // Save input log
        InputLog inputLog = InputLog.builder()
                .inputText(request)
                .inputLatitude(latitude)
                .inputLongitude(longitude)
                .emClass(emClass)
                .datetime(new Date().toString())
                .build();

        inputLogRepository.save(inputLog);

        // Save output logs
        for (FastApiResponse response : responses) {
            for (FastApiResponse.HospitalResponse hospital : response.getRecommendedHospitals()) {
                OutputLog outputLog = new OutputLog();
                outputLog.setHospital(hospital.getHospitalName());
                outputLog.setAddr(hospital.getAddress());
                outputLog.setTel(hospital.getPhone1());
                outputLog.setHaversineKm(hospital.getHaversineKm());
                outputLog.setDistanceKm(hospital.getDistanceKm());
                outputLog.setLatitude(hospital.getHospitalLatitude());
                outputLog.setLongitude(hospital.getHospitalLongitude());
                outputLog.setEmergencyType(hospital.getEmergencyType());

                outputLogRepository.save(outputLog);
            }
        }
    }
}
