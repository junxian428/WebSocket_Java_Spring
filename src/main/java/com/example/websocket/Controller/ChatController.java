package com.example.websocket.Controller;

import java.io.IOException;

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
@RequestMapping("/api")
public class ChatController {

    @Autowired
    private SocketTextHandler socketTextHandler;




    @PostMapping("/sendMessage")
    public ResponseEntity<String> sendMessage(@RequestBody String message) {
        System.out.println("API______________________________-");
        System.out.println(message);
        try {
            // Send the message through the WebSocket connection
            //WebSocketClient webSocketClient = new StandardWebSocketClient();
           // WebSocketSession session = webSocketClient.doHandshake(new SocketTextHandler(), "ws://localhost:8088/user").get();
            //String payload = "Hello, WebSocket!";
            //TextMessage payload = new TextMessage(message);
            // or
            //TextMessage message = TextMessage.create(payload);
            //socketTextHandler.handleTextMessage(session, payload);

            System.out.println(message);
            return ResponseEntity.ok("Message sent successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while sending the message.");
        }
    }
}
