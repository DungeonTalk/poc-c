package org.com.dungeonpoc.chat.service;

import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.com.dungeonpoc.chat.dto.ChatMessageResponseDto;
import org.com.dungeonpoc.chat.model.ChatMessage;
import org.com.dungeonpoc.chat.repository.ChatMessageRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;

    public Mono<ChatMessageResponseDto> saveMessage(ChatMessage requestDto) {
        ChatMessage message = new ChatMessage();
        message.setSender(requestDto.getSender());
        message.setContent(requestDto.getContent());
        message.setRoomId(requestDto.getRoomId());
        message.setTimestamp(LocalDateTime.now());

        return chatMessageRepository.save(message)
            .map(saved -> new ChatMessageResponseDto(
                saved.getId(),
                saved.getSender(),
                saved.getContent(),
                saved.getRoomId(),
                saved.getTimestamp()
            ));
    }

    public Flux<ChatMessageResponseDto> getMessages(Long roomId) {
        return chatMessageRepository.findByRoomIdOrderByTimestampAsc(roomId)
            .map(msg -> new ChatMessageResponseDto(
                msg.getId(),
                msg.getSender(),
                msg.getContent(),
                msg.getRoomId(),
                msg.getTimestamp()
            ));
    }

    public Mono<ChatMessageResponseDto> getMessageById(Long id) {
        return chatMessageRepository.findById(id)
            .map(msg -> new ChatMessageResponseDto(
                msg.getId(),
                msg.getSender(),
                msg.getContent(),
                msg.getRoomId(),
                msg.getTimestamp()
            ));
    }

}
