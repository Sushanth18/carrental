package com.carrental.repository;

import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.carrental.dto.UserRegistrationDto;
import com.carrental.model.User;
import com.carrental.model.Vehicle;
import com.carrental.util.EncryptDecryptUtils;


public class UserRepositoryImpl implements UserRepositoryCustom {

	@PersistenceContext
	private EntityManager entityManager;


	@Autowired
	private UserRoleRepository userRoleRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public User getUserByLogin(String login) {
		User result = null;

		try {
			result = (User) entityManager
					.createQuery("SELECT u FROM User u LEFT JOIN FETCH u.userRolesList WHERE u.login=:loginp")
					.setParameter("loginp", login).getSingleResult();
		} catch (NoResultException nre) {
		}

		return result;
	}

	@Override
	public User getUserByEmail(String email) {
		User result = null;

		try {
			result = (User) entityManager
					.createQuery("SELECT u FROM User u  LEFT JOIN FETCH u.userRolesList WHERE u.email=:emailp")
					.setParameter("emailp", email).getSingleResult();
		} catch (NoResultException nre) {
		}

		return result;
	}

	@Override
	@Transactional
	public User addUser(UserRegistrationDto user) {
		User userToPersist = new User(user.getName(), user.getSurname(), user.getLogin(),
				EncryptDecryptUtils.encrypt(user.getPassword()), user.getEmail(), user.getPhone(), user.getBirthDate(),
				user.getPesel());

		long customerRoleId = 2;

		userToPersist.getUserRolesList().add(userRoleRepository.getUserRoleById(customerRoleId));

		entityManager.persist(userToPersist);
		return userToPersist;
	}

	@Override
	@Transactional
	public void addRoleToUser(Long userId, Long roleId) {
		User userToAddRole = userRepository.getUserById(userId);

		userToAddRole.getUserRolesList().add(userRoleRepository.getUserRoleById(roleId));

		entityManager.merge(userToAddRole);
	}

	@Override
	@Transactional
	public int updateUser(String userlogin, User userUpdate) {
		
		if(userUpdate.getPassword()==null) {
			System.out.println("test");
		}
		
		System.out.println(userlogin);
		
		int number = entityManager
				.createQuery("UPDATE User u SET " 
						+ "u.name=COALESCE(:name,u.name), "
						+ "u.surname=COALESCE(:surname,u.surname), "
						+ "u.password=COALESCE(:password,u.password), " 
						+ "u.email=COALESCE(:email,u.email), "
						+ "u.phone=COALESCE(:phone,u.phone), " 
						+ "u.birthDate=COALESCE(:birthDate,u.birthDate), "
						+ "u.pesel=COALESCE(:pesel,u.pesel) "
						+ "WHERE u.login=:login")
				.setParameter("name", userUpdate.getName())
				.setParameter("surname", userUpdate.getSurname())
				.setParameter("password",userUpdate.getPassword()!=null ? EncryptDecryptUtils.encrypt(userUpdate.getPassword()) : userUpdate.getPassword())
				.setParameter("email", userUpdate.getEmail())
				.setParameter("phone", userUpdate.getPhone())
				.setParameter("birthDate", userUpdate.getBirthDate())
				.setParameter("pesel", userUpdate.getPesel())
				.setParameter("login", userlogin)
				.executeUpdate();

		return number;
	}

}
