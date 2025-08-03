package org.com.dungeonpoc.chat.controller;

import lombok.RequiredArgsConstructor;
import org.com.dungeonpoc.chat.dto.ChatMessageResponseDto;
import org.com.dungeonpoc.chat.service.ChatMessageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/messages")
@RequiredArgsConstructor
public class ChatMessageController {

    private final ChatMessageService messageService;

    /**
     * 채팅방의 모든 메시지 조회 (최신순 정렬 or 필요한 경우 페이징 추가)
     */
    @GetMapping
    public Flux<ChatMessageResponseDto> getMessagesByRoom(@RequestParam Long roomId) {
        return messageService.getMessages(roomId);
    }

    /**
     * 특정 메시지 조회
     */
    @GetMapping("/{id}")
    public Mono<ChatMessageResponseDto> getMessageById(@PathVariable Long id) {
        return messageService.getMessageById(id);
    }

}
