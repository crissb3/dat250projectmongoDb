package AssignmentB.restfulwebapi.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import AssignmentB.restfulwebapi.entity.Poll;
import AssignmentB.restfulwebapi.entity.User;
import AssignmentB.restfulwebapi.repository.IPollRepository;
import AssignmentB.restfulwebapi.repository.IUserRepository;

@Controller
public class PollRestController {

	@Autowired
	private IPollRepository pollRepository;
	@Autowired
	private IUserRepository userRepository;

	@PutMapping("/polls/{id}/{uname}/setVotes")
	public ResponseEntity<Poll> setVotes(@RequestBody Poll poll, @PathVariable int id, @PathVariable String uname) {
		Optional<Poll> pollOpt = pollRepository.findById(id);
		Optional<User> userOpt = userRepository.findById(uname);
		if (!pollOpt.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		if (!userOpt.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		Poll pollOld = pollOpt.get();
		User user = userOpt.get();

		pollOld.setVoteGreen(poll.getVoteGreen());
		pollOld.setVoteRed(poll.getVoteRed());
		pollOld.setUsersVoted(user);
		pollRepository.save(pollOld);
		return ResponseEntity.ok().build();
	}

	@PutMapping("/polls/{id}/update")
	public ResponseEntity<Poll> updatePoll(@RequestBody Poll poll, @PathVariable int id) {
		Optional<Poll> pollOpt = pollRepository.findById(id);
		if (!pollOpt.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Poll pollOld = pollOpt.get();
		if (poll.getName() != null)
			pollOld.setName(poll.getName());
		if (poll.getDescription() != null)
			pollOld.setDescription(poll.getDescription());
		if (poll.isPublic() != pollOld.isPublic())
			pollOld.setPublic(poll.isPublic());
		if (poll.getStatus() != null)
			pollOld.setStatus(poll.getStatus());

		pollRepository.save(pollOld);
		return ResponseEntity.ok(pollOld);

	}

}
