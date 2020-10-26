package assignmentB.restfulwebapi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import assignmentB.restfulwebapi.entity.Poll;

public interface mongoRepository extends MongoRepository<Poll, Integer>, PagingAndSortingRepository<Poll, Integer> {

    Poll findByNameContainingIgnoreCase(@Param("name") String name);

}