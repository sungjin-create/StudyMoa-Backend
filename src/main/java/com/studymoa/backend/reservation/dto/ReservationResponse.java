package com.studymoa.backend.reservation.dto;

import com.studymoa.backend.reservation.entity.Reservation;
import com.studymoa.backend.reservation.entity.ReservationStatus;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
public class ReservationResponse {
    private Long id;
    private Long studyRoomId;
    private String studyRoomName;
    private Long userId;
    private String userName;
    private LocalDate reservedDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private ReservationStatus status;

    public ReservationResponse(Reservation reservation) {
        this.id = reservation.getId();
        this.studyRoomId = reservation.getStudyRoom().getId();
        this.studyRoomName = reservation.getStudyRoom().getName();
        this.userId = reservation.getUser().getId();
        this.userName = reservation.getUser().getName();
        this.reservedDate = reservation.getReservedDate();
        this.startTime = reservation.getStartTime();
        this.endTime = reservation.getEndTime();
        this.status = reservation.getStatus();
    }
}
