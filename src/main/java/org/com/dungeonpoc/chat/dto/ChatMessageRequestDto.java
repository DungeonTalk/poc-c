package org.com.dungeonpoc.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessageRequestDto {
    private String sender;
    private String content;
    private Long roomId;
}
