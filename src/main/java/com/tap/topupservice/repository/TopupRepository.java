package com.tap.topupservice.repository;

import com.tap.topupservice.modal.TopupRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopupRepository extends MongoRepository<TopupRequest, String> {

}
