package AssignmentB.restfulwebapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import AssignmentB.restfulwebapi.entity.User;
import AssignmentB.restfulwebapi.repository.IUserRepository;

import java.util.Optional;

@Controller
public class UserRestController {

	@Autowired
	private IUserRepository userRepository;

	@PutMapping("/users/{uname}")
	public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable String uname) {
		Optional<User> userOpt = userRepository.findById(uname);
		if (!userOpt.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		User userOld = userOpt.get();
		if (user.getFname() != null)
			userOld.setFname(user.getFname());
		if (user.getLname() != null)
			userOld.setLname(user.getLname());
		if (user.getEmail() != null)
			userOld.setEmail(user.getEmail());
		if (user.getPassword() != null)
			userOld.setPassword(user.getPassword());
		if (user.isAdmin() != userOld.isAdmin())
			userOld.setAdmin(user.isAdmin());

		userRepository.save(userOld);
		return ResponseEntity.ok().build();

	}

}
