package AssignmentB.restfulwebapi.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
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
	public ResponseEntity<String> setVotes(@RequestBody Poll poll, @PathVariable int id, @PathVariable String uname) {
		
		if (!pollRepository.findById(id).isPresent()) {
			return ResponseEntity.notFound().build();
		}
		if (!userRepository.findById(uname).isPresent()) {
			return ResponseEntity.notFound().build();
		}

		Poll pollOld = pollRepository.findById(id).get();
		User user = userRepository.findById(uname).get();
		
		for(User u : pollOld.getUsersVoted()) {
			if(u.equals(user)) {
				return new ResponseEntity<>("You can only vote once!!!", HttpStatus.CONFLICT);
			}
		}
		
		pollOld.setVoteGreen(poll.getVoteGreen());
		pollOld.setVoteRed(poll.getVoteRed());
		pollOld.setUsersVoted(user);
		pollRepository.save(pollOld);
		userRepository.save(user);
		return ResponseEntity.ok().build();
	}

	@PutMapping("/polls/{id}")
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
		return ResponseEntity.ok().build();

	}

	@DeleteMapping("polls/{id}")
	public ResponseEntity<Poll> deletePoll(@PathVariable int id){
		Optional<Poll> optPoll = pollRepository.findById(id);
		if(!optPoll.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		while (optPoll.isPresent()){
			Poll poll = optPoll.get();
			poll.getUser().removePoll(poll);
			pollRepository.save(poll);
			optPoll = pollRepository.findById(id);
		}
		return ResponseEntity.ok().build();
	}
}
