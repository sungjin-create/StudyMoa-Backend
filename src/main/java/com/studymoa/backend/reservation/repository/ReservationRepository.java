package com.studymoa.backend.reservation.repository;

import com.studymoa.backend.reservation.entity.Reservation;
import com.studymoa.backend.reservation.entity.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    // 특정 스터디룸의 특정 날짜 활성 예약 수 (현재 좌석)
    int countByStudyRoomIdAndReservedDateAndStatus(Long studyRoomId, LocalDate date, ReservationStatus status);

    // 특정 유저의 예약 목록
    List<Reservation> findByUserId(Long userId);

    // 특정 스터디룸의 특정 날짜 예약 목록
    List<Reservation> findByStudyRoomIdAndReservedDate(Long studyRoomId, LocalDate date);
}
