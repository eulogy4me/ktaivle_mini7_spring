package com.aivle.mini7.dto;

import com.aivle.mini7.model.InputLog;
import com.aivle.mini7.model.OutputLog;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

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
    public static class HospitalInfo {
        private String hospital;
        private String addr;
        private String tel;
        private double haversineKm;
        private double distanceKm;
        private double latitude;
        private double longitude;
        private String emergencyType;

        public static HospitalInfo of(OutputLog log) {
            return HospitalInfo.builder()
                    .hospital(log.getHospital())
                    .addr(log.getAddr())
                    .tel(log.getTel())
                    .haversineKm(log.getHaversineKm())
                    .distanceKm(log.getDistanceKm())
                    .latitude(log.getLatitude())
                    .longitude(log.getLongitude())
                    .emergencyType(log.getEmergencyType())
                    .build();
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ResponseOutputList {
        private List<HospitalInfo> hospitals;

        public static LogDto.ResponseOutputList of(List<OutputLog> logs) {
            List<HospitalInfo> hospitalInfos = logs.stream()
                    .map(HospitalInfo::of)
                    .collect(Collectors.toList());
            return ResponseOutputList.builder()
                    .hospitals(hospitalInfos)
                    .build();
        }
    }
}
