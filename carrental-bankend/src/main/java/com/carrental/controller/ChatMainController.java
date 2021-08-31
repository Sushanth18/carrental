package com.carrental.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.carrental.dto.ChatMessage;
import com.carrental.model.Chat;
import com.carrental.model.User;
import com.carrental.service.ChatService;
import com.carrental.service.UserService;

@RestController
@RequestMapping(value = "/chat")
public class ChatMainController {

	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;

	@Autowired
	private ChatService chatService;

	@Autowired
	private UserService userService;

	@MessageMapping("/sendPrivateMessage")
	public void sendPrivateMessage(@Payload ChatMessage chatMessage) {
		Chat chat = addChat(chatMessage);
		simpMessagingTemplate.convertAndSendToUser(chatMessage.getReceiver().trim(), "/reply", chat);
	}

	@RequestMapping(value = { "/{vehicleID}/{fromUserId}/{toUserId}" }, method = RequestMethod.GET)
	public Page<Chat> getVehicleChatForPage(@PathVariable Long vehicleID, @PathVariable Long fromUserId,
			 @PathVariable Long toUserId,
			@RequestParam(value = "page") int page, @RequestParam(value = "number") int number) {
		return chatService.getChatsForVehicle(vehicleID, fromUserId,toUserId, PageRequest.of(page, number));
	}

	private Chat addChat(ChatMessage chatMessage) {
		String pattern = "dd/MM/yyyy HH:mm:ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(new Date());
		User senderByLogin = userService.getUserByLogin(chatMessage.getSender());
		User receiverByLogin = userService.getUserByLogin(chatMessage.getReceiver());
		Chat chat = new Chat();
		chat.setFromUserId(senderByLogin.getId());
		chat.setMessage(chatMessage.getContent());
		chat.setToUserId(receiverByLogin.getId());
		chat.setVehicleId(chatMessage.getVechileId());
		chat.setFromUserName(senderByLogin.getLogin());
		chat.setToUserName(receiverByLogin.getLogin());
		chat.setMessageTime(date);
		chat.setToName(receiverByLogin.getName());
		chat.setFromName(senderByLogin.getName());
		chatService.addChat(chat);
		return chat;
	}

}
