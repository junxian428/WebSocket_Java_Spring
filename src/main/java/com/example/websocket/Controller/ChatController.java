package com.example.websocket.Controller;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.handler.TextWebSocketHandler;


import com.example.websocket.Config.SocketTextHandler;

@RestController
public class ChatController {
    private final SocketTextHandler webSocketHandler;

    @Autowired
    public ChatController (SocketTextHandler  webSocketHandler) {
        this.webSocketHandler = webSocketHandler;
    }

    @PostMapping("/messages")
    public void sendMessageToWebSocket(@RequestBody String message)throws IOException {
       System.out.println(webSocketHandler.getSessionIds());
    	String payload = message;
		JSONObject jsonObject = new JSONObject(payload);
        System.out.println(payload);
		//session.sendMessage(new TextMessage("Hi " + jsonObject.get("user") + " how may we help you?"));
       for (WebSocketSession session : webSocketHandler.getSessionsFromIds(webSocketHandler.getSessionIds())) {
            session.sendMessage(new TextMessage(message));
        }

        //

        //System.out.println("Send Messages to all sessions....");
        //for (WebSocketSession eachsession : webSocketHandler.getSessionIds()) {
        //    if (eachsession.isOpen()) {
		//        eachsession.sendMessage(new TextMessage(jsonObject.get("user") + "\n"));
        //    } else{
        //        System.out.println("not sending to all sessions");
        //    }
        //}

    }
}
