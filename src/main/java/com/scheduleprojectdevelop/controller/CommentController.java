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
@RequestMapping("/schedules/{scheduleId}/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CreateCommentResponse> createComment(@PathVariable Long scheduleId,
                                                               @SessionAttribute(name = "loginUser") SessionUser sessionUser,
                                                               @RequestBody CreateCommentRequest request) {
        CreateCommentResponse result = commentService.create(scheduleId, sessionUser.getUserId(), request);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }


    @GetMapping("/{commentId}")
    public ResponseEntity<GetOneCommentResponse> getOneComment(@PathVariable Long scheduleId,
                                                               @PathVariable Long commentId) {
        GetOneCommentResponse result = commentService.getOneComment(scheduleId, commentId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping
    public ResponseEntity<List<GetOneCommentResponse>> getAllComment(@PathVariable Long scheduleId) {
        List<GetOneCommentResponse> results = commentService.getAllComment(scheduleId);
        return ResponseEntity.status(HttpStatus.OK).body(results);
    }

    @PatchMapping("/{commentId}")
    public ResponseEntity<UpdateCommentResponse> updateComment(@PathVariable Long scheduleId,
                                                               @PathVariable Long commentId,
                                                               @RequestBody UpdateCommentRequest request,
                                                               @SessionAttribute(name = "loginUser") SessionUser sessionUser) {
        UpdateCommentResponse result = commentService.updateComment(scheduleId, commentId, sessionUser.getUserId(), request);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long scheduleId,
                                              @PathVariable Long commentId,
                                              @SessionAttribute(name = "loginUser") SessionUser sessionUser) {
        commentService.deleteComment(scheduleId, commentId, sessionUser.getUserId());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
