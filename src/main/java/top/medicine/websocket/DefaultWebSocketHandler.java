package top.medicine.websocket;

import com.alibaba.fastjson.JSON;
import io.micrometer.core.lang.NonNull;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.*;
import top.medicine.entity.ChannelHistory;
import top.medicine.entity.User;
import top.medicine.service.ChannelHistoryService;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class DefaultWebSocketHandler implements WebSocketHandler {

    @Getter
    @Setter
    private String channelId;

    @Getter
    @Setter
    private User user;

    @Autowired
    private ChannelHistoryService channelHistoryService;

    
    private final AtomicInteger connectionCount = new AtomicInteger(0);

    
    private final CopyOnWriteArraySet<WebSocketSession> sessions = new CopyOnWriteArraySet<>();


    
    @Override
    public void afterConnectionEstablished(@NonNull WebSocketSession session) {
        setChannelId(((String) session.getAttributes().get("channel-id")));
        sessions.add(session);
        connectionCount.incrementAndGet();
        setUser(((User) session.getAttributes().get("login-user")));
        log.info("新用户加入连接：{}", user);
    }

    
    @SneakyThrows
    @Override
    public void handleMessage(@NonNull WebSocketSession session, @NonNull WebSocketMessage<?> message0) {
        if (message0 instanceof PingMessage ping) {
            session.sendMessage(ping);
        }

        if (message0 instanceof TextMessage text) {
            String message = text.getPayload();

            ChannelHistory h = ChannelHistory.builder().channelId(Integer.parseInt(channelId)).userId(user.getId()).message(message).build();

            h = channelHistoryService.save(h);
            //向其他连接到此频道的人发送信息
            ChannelHistory finalH = h;
            sessions.stream().filter((a) ->
                    a.getAttributes().get("channel-id").equals(channelId)
                            //除自己以外
//                            && !((User) a.getAttributes().get("login-user")).getId().equals(user.getId())
            ).forEach(a -> {
                try {
                    a.sendMessage(new TextMessage(JSON.toJSONString(finalH)));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            //向数据库插入一条记录

            log.info("发现信息!");
        }
    }

    
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) {
        log.error("ws出错!", exception);
    }

    
    @Override
    public void afterConnectionClosed(@NonNull WebSocketSession session, @NonNull CloseStatus closeStatus) {
        sessions.remove(session);
        connectionCount.decrementAndGet();
        log.info("用户退出连接：{}", session.getAttributes().get("loginUser"));
    }

    
    @Override
    public boolean supportsPartialMessages() {
        return false;
    }


    public void broadCast(String message) throws IOException {
        for (WebSocketSession session : sessions) {
            if (!session.isOpen()) {
                continue;
            }
            session.sendMessage(new TextMessage(message));
        }
    }

}
