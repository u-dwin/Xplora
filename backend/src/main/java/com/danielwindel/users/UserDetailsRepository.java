package com.danielwindel.users;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDetailsRepository extends MongoRepository<UserDetails, String> {
    List<UserDetails> findAllByType(String type);

}

