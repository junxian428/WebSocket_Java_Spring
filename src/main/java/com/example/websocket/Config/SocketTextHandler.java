package com.example.websocket.Config;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.json.JSONObject;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class SocketTextHandler extends TextWebSocketHandler {

    private List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();


	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message)
			throws InterruptedException, IOException {

		String payload = message.getPayload();
		JSONObject jsonObject = new JSONObject(payload);
        System.out.println(payload);
		//session.sendMessage(new TextMessage("Hi " + jsonObject.get("user") + " how may we help you?"));


        //

        System.out.println("Send Messages to all sessions....");
        for (WebSocketSession eachsession : sessions) {
            if (eachsession.isOpen()) {
		        eachsession.sendMessage(new TextMessage("Hi " + jsonObject.get("user") + " NODEJS?"));
            } else{
                System.out.println("not sending to all sessions");
            }
        }
	}

    


    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session);
    }


    
}