package com.carrental.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carrental.model.Chat;
import com.carrental.repository.ChatRepository;

@Service("chatService")
@Transactional
public class ChatServiceImpl implements ChatService {

	@Autowired
	private ChatRepository chatRepository;

	@Override
	public void addChat(Chat chat) {
		chatRepository.save(chat);
	}

	public Page<Chat> getChatsForVehicle(Long vehicleId, Long fromUserId, Long toUserId, Pageable pageable) {
		return chatRepository.getChatsForVehicle(vehicleId, fromUserId, toUserId, pageable);
	}
}
