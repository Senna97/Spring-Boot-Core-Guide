package com.springboot.hello.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

// lombok 사용: 컬럼을 추가하거나 제거해도 Constructor, Getter 를 추가로 만들거나 지우지 않아도 되어서 편함
@AllArgsConstructor // 모든 컬럼을 받는 생성자 추가
@Getter
@NoArgsConstructor // 아무것도 받지 않는 생성자 추가
@Setter
public class Hospital {
    private int id;
    private String openServiceName;
    private int openLocalGovernmentCode;
    private String managementNumber;
    private LocalDateTime licenseDate;
    private int businessStatus;
    private int businessStatusCode;
    private String phone;
    private String fullAddress;
    private String roadNameAddress;
    private String hospitalName;
    private String businessTypeName;
    private int healthcareProviderCount;
    private int patientRoomCount;
    private int totalNumberOfBeds;
    private float totalAreaSize;
}
