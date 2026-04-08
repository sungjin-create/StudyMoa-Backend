package com.studymoa.backend.studyroom.controller;

import com.studymoa.backend.common.response.ApiResponse;
import com.studymoa.backend.studyroom.dto.StudyRoomResponse;
import com.studymoa.backend.studyroom.service.StudyRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/studyroom")
@RequiredArgsConstructor
public class StudyRoomController {

    private final StudyRoomService studyRoomService;

    @GetMapping("/list")
    public ResponseEntity<ApiResponse<Page<StudyRoomResponse>>> getStudyRoomList(
            @RequestParam(defaultValue = "0") int page) {
        return ResponseEntity.ok(ApiResponse.ok("조회 성공", studyRoomService.getList(page)));
    }
}
