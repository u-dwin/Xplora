package com.danielwindel.users;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProfileDetailsRepository extends MongoRepository<ProfileDetails, String> {
}

