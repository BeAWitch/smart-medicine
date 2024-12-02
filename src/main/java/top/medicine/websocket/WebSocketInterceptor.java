package top.medicine.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.lang.NonNull;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;
import top.medicine.entity.User;
import top.medicine.service.ChannelService;

import java.util.Map;

@Slf4j
public class WebSocketInterceptor implements HandshakeInterceptor {
    @Autowired
    protected ChannelService channelService;


    @Override
    public boolean beforeHandshake(@NonNull ServerHttpRequest request, @NonNull ServerHttpResponse response, @NonNull WebSocketHandler wsHandler, @NonNull Map<String, Object> attributes) throws Exception {
        if (request instanceof ServletServerHttpRequest servletServerHttpRequest) {
            User user = ((User) servletServerHttpRequest.getServletRequest().getSession().getAttribute("loginUser"));
            if (user == null) {
                log.info("user为空，断开链接");
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                return false;
            }
            String id = servletServerHttpRequest.getServletRequest().getParameter("channel-id");

            if (id == null || channelService.get(Integer.parseInt(id)) == null) {
                log.info("channel为空，断开链接");
                response.setStatusCode(HttpStatus.NOT_FOUND);
                return false;
            }
            attributes.put("channel-id",id);
            attributes.put("login-user",user);
            return true;
        }
        return true;
    }

    @Override
    public void afterHandshake(@NonNull ServerHttpRequest request, @NonNull ServerHttpResponse response, @NonNull WebSocketHandler wsHandler, Exception exception) {
        log.info("qqq");
    }
}

