package com.scheduleprojectdevelop.controller;


import com.scheduleprojectdevelop.dto.AuthDto.SessionUser;
import com.scheduleprojectdevelop.dto.commentDto.CreateCommentRequest;
import com.scheduleprojectdevelop.dto.commentDto.CreateCommentResponse;
import com.scheduleprojectdevelop.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private CommentService commentService;

    @PostMapping("/schedules/{scheduleId}/comments")
    public ResponseEntity<CreateCommentResponse> createComment(@PathVariable Long scheduleId,
                                                               @SessionAttribute(name = "loginUser") SessionUser sessionUser,
                                                               @RequestBody CreateCommentRequest request){
        CreateCommentResponse result = commentService.create(scheduleId, sessionUser.getUserId(), request);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }



}
