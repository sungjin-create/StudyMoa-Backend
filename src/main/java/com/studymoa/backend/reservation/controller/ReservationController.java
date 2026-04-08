package com.studymoa.backend.reservation.controller;

import com.studymoa.backend.common.response.ApiResponse;
import com.studymoa.backend.reservation.dto.ReservationRequest;
import com.studymoa.backend.reservation.dto.ReservationResponse;
import com.studymoa.backend.reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservation")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    public ResponseEntity<ApiResponse<ReservationResponse>> reserve(@RequestBody ReservationRequest request) {
        long longStudyRoomId = request.getStudyRoomId();
        String strLoginId = request.getLoginId();
        return ResponseEntity.ok(ApiResponse.ok("예약이 완료되었습니다.", reservationService.reserve(request)));
    }

    @DeleteMapping("/{reservationId}")
    public ResponseEntity<ApiResponse<Void>> cancel(@PathVariable Long reservationId) {
        reservationService.cancel(reservationId);
        return ResponseEntity.ok(ApiResponse.ok("예약이 취소되었습니다.", null));
    }

    @GetMapping("/my/{userId}")
    public ResponseEntity<ApiResponse<List<ReservationResponse>>> getMyReservations(@PathVariable Long userId) {
        return ResponseEntity.ok(ApiResponse.ok("조회 성공", reservationService.getMyReservations(userId)));
    }
}
