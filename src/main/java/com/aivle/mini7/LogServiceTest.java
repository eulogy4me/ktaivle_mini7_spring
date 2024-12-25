package com.aivle.mini7;

import org.springframework.stereotype.Service;

import com.aivle.mini7.model.InputLog;
import com.aivle.mini7.repository.InputLogRepository;
// import com.aivle.mini7.repository.OutputLogRepository;

import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LogServiceTest {

    private final InputLogRepository inputLogRepository;
    // private final OutputLogRepository outputLogRepository;

    public void testSaveAndRetrieve() {
        InputLog inputLog = new InputLog();
        inputLog.setInputText("Sample Request");
        inputLog.setDatetime(LocalDateTime.now().toString());
        inputLog.setInputLatitude(37.5665);
        inputLog.setInputLongitude(126.9780);
        inputLog.setEmClass(1);
        inputLogRepository.save(inputLog);

        System.out.println("Saved InputLog: " + inputLogRepository.findAll());
    }
}
