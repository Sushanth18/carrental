package com.carrental.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.carrental.model.Chat;
import com.carrental.model.Comment;

public interface ChatRepository extends JpaRepository<Chat, Long>{

	@Query(value="SELECT c FROM Chat c WHERE c.vehicleId=:vehicleId and (c.fromUserId=:fromUserId and c.toUserId=:toUserId) or (c.fromUserId=:toUserId and c.toUserId=:fromUserId)",
			countQuery="SELECT COUNT(c) FROM Chat c WHERE c.vehicleId=:vehicleId and c.fromUserId=:fromUserId and c.toUserId=:toUserId or (c.fromUserId=:toUserId and c.toUserId=:fromUserId)")
	public Page<Chat> getChatsForVehicle(@Param("vehicleId") Long vehicleId,@Param("fromUserId") Long fromUserId,@Param("toUserId") Long toUserId, Pageable pageable);
	
}
