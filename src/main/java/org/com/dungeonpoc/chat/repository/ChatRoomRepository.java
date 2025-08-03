package org.com.dungeonpoc.chat.repository;

import org.com.dungeonpoc.chat.model.ChatRoom;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ChatRoomRepository extends ReactiveCrudRepository<ChatRoom, Long> {

}
