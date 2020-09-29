package AssignmentB.restfulwebapi.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import AssignmentB.restfulwebapi.entity.Poll;

public interface IPollRepository extends PagingAndSortingRepository<Poll, Integer> {

	Poll findByNameContainingIgnoreCase(@Param("name") String name);
	
}
