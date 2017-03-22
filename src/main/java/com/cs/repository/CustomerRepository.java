package com.cs.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.cs.models.Customers;

public interface CustomerRepository extends PagingAndSortingRepository<Customers, Long> {

	@Query("select count(*) from Customers cust where cust.userContact =:userContact")
	int doesUserExists(@Param("userContact") long userContact);
	
	@Query("select pushToken from Customers cust where cust.userContact =:userContact")
	String getPushToken(@Param("userContact") long userContact);

	@Transactional
    @Modifying(clearAutomatically = true)
	@Query("update Customers cust set cust.pushToken =:pushToken where cust.userContact =:userContact")
	int addPushToken(@Param("userContact") long userContact, @Param("pushToken") String pushToken);
}
