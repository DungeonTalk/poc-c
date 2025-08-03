package org.com.dungeonpoc.chat.service;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.com.dungeonpoc.chat.dto.ChatRoomRequestDto;
import org.com.dungeonpoc.chat.dto.ChatRoomResponseDto;
import org.com.dungeonpoc.chat.model.ChatRoom;
import org.com.dungeonpoc.chat.repository.ChatRoomRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;

    public Mono<ChatRoomResponseDto> createRoom(ChatRoomRequestDto chatRoomRequestDto) {
        ChatRoom newRoom = new ChatRoom(chatRoomRequestDto.getName());
        return chatRoomRepository.save(newRoom)
            .map(saved -> new ChatRoomResponseDto(saved.getId(), saved.getName()));
    }

    public Flux<ChatRoomResponseDto> findAllRooms() {
        return chatRoomRepository.findAll()
            .map(room -> new ChatRoomResponseDto(room.getId(), room.getName()));
    }

//    public Mono<ChatRoomResponseDto> getRoomById(String id) {
//        return chatRoomRepository.findById(id)
//            .map(room -> new ChatRoomResponseDto(room.getId(), room.getName()));
//    }

}
