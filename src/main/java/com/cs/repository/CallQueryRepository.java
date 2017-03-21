package com.cs.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.cs.models.CallQuery;

public interface CallQueryRepository extends PagingAndSortingRepository<CallQuery, Long> {	

}
