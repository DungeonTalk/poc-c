package org.com.dungeonpoc.chat.controller;

import lombok.RequiredArgsConstructor;
import org.com.dungeonpoc.chat.dto.ChatRoomRequestDto;
import org.com.dungeonpoc.chat.dto.ChatRoomResponseDto;
import org.com.dungeonpoc.chat.model.ChatRoom;
import org.com.dungeonpoc.chat.service.ChatRoomService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rooms")
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    @PostMapping
    public Mono<ChatRoomResponseDto> createRoom(@RequestBody ChatRoomRequestDto request) {
        return chatRoomService.createRoom(request)
            .map(room -> new ChatRoomResponseDto(room.getId(), room.getName()));
    }

    @GetMapping
    public Flux<ChatRoomResponseDto> listRooms() {
        return chatRoomService.findAllRooms()
            .map(room -> new ChatRoomResponseDto(room.getId(), room.getName()));
    }

}
