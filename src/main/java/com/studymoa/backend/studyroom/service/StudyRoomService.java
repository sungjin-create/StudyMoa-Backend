package com.studymoa.backend.studyroom.service;

import com.studymoa.backend.reservation.entity.ReservationStatus;
import com.studymoa.backend.reservation.repository.ReservationRepository;
import com.studymoa.backend.studyroom.dto.StudyRoomResponse;
import com.studymoa.backend.studyroom.entity.StudyRoom;
import com.studymoa.backend.studyroom.repository.StudyRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudyRoomService {

    private final StudyRoomRepository studyRoomRepository;
    private final ReservationRepository reservationRepository;

    public Page<StudyRoomResponse> getList(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<StudyRoom> roomPage = studyRoomRepository.findAll(pageable);
        LocalDate today = LocalDate.now();

        List<StudyRoomResponse> responses = roomPage.getContent().stream()
                .map(room -> {
                    int currentSeats = reservationRepository
                            .countByStudyRoomIdAndReservedDateAndStatus(room.getId(), today, ReservationStatus.ACTIVE);
                    return new StudyRoomResponse(room, currentSeats);
                })
                .collect(Collectors.toList());

        return new PageImpl<>(responses, pageable, roomPage.getTotalElements());
    }
}
