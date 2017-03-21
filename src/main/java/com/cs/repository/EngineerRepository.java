package com.cs.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.cs.models.Engineers;

public interface EngineerRepository extends PagingAndSortingRepository<Engineers, Long> {

	@Query("select count(*) from Engineers engineer where engineer.userName =:userName")
	int doesUserExists(@Param("userName") String userName);

	@Query("select count(*) from Engineers engineer where engineer.userName =:userName and engineer.userPassword =:userPassword")
	int loginUser(@Param("userName") String userName, @Param("userPassword") String userPassword);

	@Query("select pushToken from Engineers engineer where engineer.userName = :userName")
	String getPushToken(@Param("userName") String userName);

	@Transactional
    @Modifying(clearAutomatically = true)
	@Query("update Engineers engineer set engineer.pushToken =:pushToken where engineer.userName =:userName")
	int addPushToken(@Param("userName") String userName, @Param("pushToken") String pushToken);
}
