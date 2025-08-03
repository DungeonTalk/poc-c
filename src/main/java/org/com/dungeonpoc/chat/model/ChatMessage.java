package org.com.dungeonpoc.chat.model;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table("chat_messages")
@Builder
public class ChatMessage {

    @Id
    private Long id;

    @Column(nullable = false)
    private Long roomId;

    @Column
    private String sender;

    @Column
    private String content;

    @Column
    private LocalDateTime timestamp = LocalDateTime.now();

}
