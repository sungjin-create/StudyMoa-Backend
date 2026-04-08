package com.studymoa.backend.reservation.service;

import com.studymoa.backend.reservation.dto.ReservationRequest;
import com.studymoa.backend.reservation.dto.ReservationResponse;
import com.studymoa.backend.reservation.entity.Reservation;
import com.studymoa.backend.reservation.entity.ReservationStatus;
import com.studymoa.backend.reservation.repository.ReservationRepository;
import com.studymoa.backend.studyroom.entity.StudyRoom;
import com.studymoa.backend.studyroom.repository.StudyRoomRepository;
import com.studymoa.backend.user.entity.User;
import com.studymoa.backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final StudyRoomRepository studyRoomRepository;
    private final UserRepository userRepository;

    @Transactional
    public ReservationResponse reserve(ReservationRequest request) {
        StudyRoom studyRoom = studyRoomRepository.findByIdWithLock(request.getStudyRoomId())
                .orElseThrow(() -> new IllegalArgumentException("스터디룸을 찾을 수 없습니다."));

        User user = userRepository.findByLoginId(request.getLoginId())
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));

        int currentSeats = reservationRepository.countByStudyRoomIdAndReservedDateAndStatus(
                studyRoom.getId(), request.getReservedDate(), ReservationStatus.ACTIVE);

        if (currentSeats >= studyRoom.getTotalSeats()) {
            throw new IllegalStateException("남은 좌석이 없습니다.");
        }

        Reservation reservation = Reservation.builder()
                .studyRoom(studyRoom)
                .user(user)
                .reservedDate(request.getReservedDate())
                .startTime(request.getStartTime())
                .endTime(request.getEndTime())
                .build();

        return new ReservationResponse(reservationRepository.save(reservation));
    }

    @Transactional
    public void cancel(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new IllegalArgumentException("예약을 찾을 수 없습니다."));
        reservation.setStatus(ReservationStatus.CANCELLED);
    }

    public List<ReservationResponse> getMyReservations(Long userId) {
        return reservationRepository.findByUserId(userId).stream()
                .map(ReservationResponse::new)
                .collect(Collectors.toList());
    }

    public int getCurrentSeats(Long studyRoomId, LocalDate date) {
        return reservationRepository.countByStudyRoomIdAndReservedDateAndStatus(
                studyRoomId, date, ReservationStatus.ACTIVE);
    }
}
