package org.com.dungeonpoc.chat.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("chat_rooms")
public class ChatRoom {

    @Id
    private Long id;
    private String name;

    public ChatRoom(String name) {
        this.name = name;
    }
}
