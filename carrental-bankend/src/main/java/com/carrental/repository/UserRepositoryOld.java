package com.carrental.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.carrental.entity.UserEntity;

@Repository
public interface UserRepositoryOld extends JpaRepository<UserEntity, Integer> {
	@Query(value = "SELECT u from UserEntity u WHERE u.id != :user_id")
	List<UserEntity> fetchAllUser(@Param(value = "user_id") Integer userId);

	UserEntity findByFirstName(String firstName);

}
