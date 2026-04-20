package com.scheduleprojectdevelop.controller;


import com.scheduleprojectdevelop.dto.AuthDto.SessionUser;
import com.scheduleprojectdevelop.dto.commentDto.*;
import com.scheduleprojectdevelop.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private CommentService commentService;

    @PostMapping("/schedules/{scheduleId}/comments")
    public ResponseEntity<CreateCommentResponse> createComment(@PathVariable Long scheduleId,
                                                               @SessionAttribute(name = "loginUser") SessionUser sessionUser,
                                                               @RequestBody CreateCommentRequest request) {
        CreateCommentResponse result = commentService.create(scheduleId, sessionUser.getUserId(), request);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }


    @GetMapping("/schedules/{scheduleId}/comments/{commentId}")
    public ResponseEntity<GetOneCommentResponse> getOneComment(@PathVariable Long scheduleId,
                                                               @PathVariable Long commentId) {
        GetOneCommentResponse result = commentService.getOneComment(scheduleId, commentId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/schedules/{scheduleId}/comments")
    public ResponseEntity<List<GetOneCommentResponse>> getAllComment(@PathVariable Long scheduleId) {
        List<GetOneCommentResponse> results = commentService.getAllComment(scheduleId);
        return ResponseEntity.status(HttpStatus.OK).body(results);
    }

    @PatchMapping("/schedules/{scheduleId}/comments/{commentId}")
    public ResponseEntity<UpdateCommentResponse> updateComment(@PathVariable Long scheduleId,
                                                               @PathVariable Long commentId,
                                                               @RequestBody UpdateCommentRequest request) {
        UpdateCommentResponse result = commentService.updateComment(scheduleId, commentId, request);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @DeleteMapping("/schedules/{scheduleId}/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long scheduleId,
                                              @PathVariable Long commentId) {
        commentService.deleteComment(scheduleId, commentId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
