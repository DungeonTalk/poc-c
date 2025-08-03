package org.com.dungeonpoc.chat.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessageResponseDto {
    private Long id;
    private String sender;
    private String content;
    private Long roomId;
    private LocalDateTime timestamp;
}
