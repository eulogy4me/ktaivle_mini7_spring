package com.aivle.mini7.dto;
import com.aivle.mini7.model.InputLog;
import com.aivle.mini7.model.OutputLog;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LogDto {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ResponseInputList {
        private String inputText;
        private String datetime;
        private Double inputLatitude;
        private Double inputLongitude;
        private Integer emClass;

        public static LogDto.ResponseInputList of(InputLog log) {
            return ResponseInputList.builder()
                .emClass(log.getEmClass())
                .inputText(log.getInputText())
                .datetime(log.getDatetime())
                .inputLatitude(log.getInputLatitude())
                .inputLongitude(log.getInputLongitude())
                .build();
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ResponseOutputList {
        private String hospital1;
        private String addr1;
        private String tel1;
        private String hospital2;
        private String addr2;
        private String tel2;
        private String hospital3;
        private String addr3;
        private String tel3;

        public static LogDto.ResponseOutputList of(OutputLog log) {
            return ResponseOutputList.builder()
            .hospital1(log.getHospital1())
            .addr1(log.getAddr1())
            .tel1(log.getTel1())
            .hospital2(log.getHospital2())
            .addr2(log.getAddr2())
            .tel2(log.getTel2())
            .hospital3(log.getHospital3())
            .addr3(log.getAddr3())
            .tel3(log.getTel3())
            .build();
        }
    }
}