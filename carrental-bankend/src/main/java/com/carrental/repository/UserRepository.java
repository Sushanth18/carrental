package com.carrental.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.carrental.model.User;
import com.carrental.model.Vehicle;

public interface UserRepository extends PagingAndSortingRepository<User, Long>, UserRepositoryCustom {

	@Query("SELECT u FROM User u LEFT JOIN FETCH u.userRolesList")
	public List<User> getAllUsers();

	@Query("SELECT u FROM User u LEFT JOIN FETCH u.userRolesList WHERE u.id=:id")
	public User getUserById(@Param("id") Long id);

	@Query("SELECT u FROM User u LEFT JOIN FETCH u.userRolesList WHERE u.login=:login")
	public User getUserByLogin(@Param("login") String login);

	@Query("SELECT u FROM User u WHERE u.login=:login and u.password=:password")
	public User getUserByLoginAndPassword(@Param("login") String login, @Param("password") String password);

	@Query(value = "SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.userRolesList", countQuery = "SELECT COUNT(u) FROM User u")
	public Page<User> getUsersForPage(Pageable pageable);
	
	@Query(value = "select distinct u.* from users u join chat c on c.from_user_id = u.id where c.id_vehicle=:vehicle_id and c.from_user_name!=:login",nativeQuery = true)
	public List<User> getUsersForChat(@Param("vehicle_id") Long vehicleId,@Param("login") String login);

}
