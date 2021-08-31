package com.carrental.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.carrental.model.Chat;

public interface ChatService {
	public void addChat(Chat chat);
	
	public Page<Chat> getChatsForVehicle(Long vehicleId, Long fromUserId,Long toUserId,
			Pageable pageable);
}
