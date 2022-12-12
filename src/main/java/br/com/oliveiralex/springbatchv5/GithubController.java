package br.com.oliveiralex.springbatchv5;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
@RequestMapping("/webclient")
public class GithubController {
	
	private GithubClient githubClient;
	
	@GetMapping("/user/{name}")
	public Mono<UserResponse> getUserByName(@PathVariable String name) {
		return githubClient.findUserByName(name);
	}

}
