package org.com.dungeonpoc.chat.repository;

import org.com.dungeonpoc.chat.model.ChatMessage;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ChatMessageRepository extends ReactiveCrudRepository<ChatMessage, Long> {
    Flux<ChatMessage> findByRoomIdOrderByTimestampAsc(Long roomId);
}
