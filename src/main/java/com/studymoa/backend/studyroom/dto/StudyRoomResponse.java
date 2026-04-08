package com.studymoa.backend.studyroom.dto;

import com.studymoa.backend.studyroom.entity.StudyRoom;
import lombok.Getter;

import java.time.LocalTime;

@Getter
public class StudyRoomResponse {
    private Long id;
    private String name;
    private String address;
    private String phone;
    private LocalTime openTime;
    private LocalTime closeTime;
    private int totalSeats;
    private int currentSeats;
    private int remainingSeats;
    private String description;

    public StudyRoomResponse(StudyRoom studyRoom, int currentSeats) {
        this.id = studyRoom.getId();
        this.name = studyRoom.getName();
        this.address = studyRoom.getAddress();
        this.phone = studyRoom.getPhone();
        this.openTime = studyRoom.getOpenTime();
        this.closeTime = studyRoom.getCloseTime();
        this.totalSeats = studyRoom.getTotalSeats();
        this.currentSeats = currentSeats;
        this.remainingSeats = studyRoom.getTotalSeats() - currentSeats;
        this.description = studyRoom.getDescription();
    }
}
