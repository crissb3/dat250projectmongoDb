package AssignmentB.restfulwebapi.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import AssignmentB.restfulwebapi.entity.Poll;

public interface IPollRepository extends PagingAndSortingRepository<Poll, Integer> {

	Poll findByNameContainingIgnoreCase(@Param("name") String name);

}
