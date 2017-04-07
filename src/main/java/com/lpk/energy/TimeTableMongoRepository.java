package com.lpk.energy;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Minwoo on 2017. 4. 7..
 */
public interface TimeTableMongoRepository extends MongoRepository<ClassDo, String> {
}
