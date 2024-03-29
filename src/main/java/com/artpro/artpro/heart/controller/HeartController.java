package com.artpro.artpro.heart.controller;

import com.artpro.artpro.heart.service.HeartService;
import com.artpro.artpro.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/boards/{boardId}/heart")
public class HeartController {

    private final HeartService heartService;

    @PostMapping
    public void create(@PathVariable long boardId, @AuthenticationPrincipal Member member) {
        heartService.create(boardId, member);
    }

    @DeleteMapping("/{heartId}")
    public void delete(@PathVariable long boardId, @PathVariable long heartId) {
        heartService.delete(heartId);
    }


    @GetMapping
    public boolean isHeart(@PathVariable long boardId, @AuthenticationPrincipal Member member) {
        return heartService.isHeart(boardId, member);
    }
}
