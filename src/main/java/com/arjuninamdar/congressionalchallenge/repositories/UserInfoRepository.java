package com.arjuninamdar.congressionalchallenge.repositories;

import com.arjuninamdar.congressionalchallenge.models.sql.UserInfo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepository extends CrudRepository<UserInfo, Integer> {
    UserInfo findByUsername(String username); 
}
