package com.studymoa.backend.reservation.dto;

import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
public class ReservationRequest {
    private Long studyRoomId;
    private String loginId;
    private LocalDate reservedDate;
    private LocalTime startTime;
    private LocalTime endTime;
}
