package org.com.dungeonpoc.chat.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.com.dungeonpoc.chat.dto.ChatMessageResponseDto;
import org.com.dungeonpoc.chat.model.ChatMessage;
import org.com.dungeonpoc.chat.service.ChatMessageService;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;
import reactor.core.publisher.Sinks.Many;

import java.net.URI;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@RequiredArgsConstructor
public class ChatWebSocketHandler implements WebSocketHandler {

    private final ObjectMapper mapper = new ObjectMapper();
    private final ChatMessageService messageService;

    // Key: roomId(Long), Value: Sinks
    private final Map<Long, Many<String>> roomSinkMap = new ConcurrentHashMap<>();

    @Override
    public Mono<Void> handle(WebSocketSession session) {
        Long roomId = getRoomId(session);

        // Sink 생성 또는 조회
        Sinks.Many<String> sink = roomSinkMap.computeIfAbsent(roomId,
            key -> Sinks.many().multicast().onBackpressureBuffer());

        // 들어오는 메시지 처리
        Mono<Void> input = session.receive()
            .map(WebSocketMessage::getPayloadAsText)
            .flatMap(json -> {
                try {
                    ChatMessage msg = mapper.readValue(json, ChatMessage.class);
                    msg.setRoomId(roomId); // DB에서는 String으로 저장되므로 변환
                    return messageService.saveMessage(msg)
                        .doOnNext(saved -> sink.tryEmitNext(toJson(saved)))
                        .then();
                } catch (Exception e) {
                    return Mono.error(e);
                }
            }).then();

        // 나가는 메시지 처리
        Flux<WebSocketMessage> output = sink.asFlux()
            .map(session::textMessage);

        return session.send(output).and(input);
    }

    private String toJson(ChatMessageResponseDto msg) {
        try {
            return mapper.writeValueAsString(msg);
        } catch (Exception e) {
            return "{}";
        }
    }

    /**
     * WebSocketSession의 URI 쿼리 파라미터에서 roomId=값 추출
     */
    private Long getRoomId(WebSocketSession session) {
        try {
            URI uri = session.getHandshakeInfo().getUri();
            String query = uri.getQuery();              // 예: roomId=6
            if (query != null && query.startsWith("roomId=")) {
                return Long.parseLong(query.substring(7));
            }
        } catch (Exception e) {
            // 로그를 찍거나 기본값 사용 가능
        }
        return -1L; // 예외 처리: 유효하지 않은 roomId
    }
}
