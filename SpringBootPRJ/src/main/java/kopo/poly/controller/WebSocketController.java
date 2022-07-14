package kopo.poly.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;


@Slf4j

@Controller
@ServerEndpoint(value = "/chatt")
public class WebSocketController {

    public WebSocketController() {

        System.out.println("웹소켓 서버 객체 생성");
    }

    private static Set<Session> clients =
            Collections.synchronizedSet(new HashSet<Session>());


    @OnOpen
    public void onOpen(Session s) {
        System.out.println("open session : " + s.toString());
        log.info("Open session id:" + s.getId());
        if (!clients.contains(s)) {
            clients.add(s);
            System.out.println("session open : " + s);
        } else {
            System.out.println("이미 연결된 session 임!!!");
        }
    }


    @OnMessage
    public void onMessage(String msg, Session session) throws Exception {
        System.out.println("receive message : " + msg);

        for (Session s : clients) {

            System.out.println("send data : " + msg);

            s.getBasicRemote().sendText(msg);

        }

    }

    @OnError
    public void onError(Throwable e, Session session) {

    }

    @OnClose
    public void onClose(Session s) {
        System.out.println("session close : " + s);
        clients.remove(s);
    }
}