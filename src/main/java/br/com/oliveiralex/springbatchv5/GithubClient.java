package br.com.oliveiralex.springbatchv5;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class GithubClient {

	private final WebClient webclient;

	public GithubClient(WebClient.Builder builder) {
		webclient = builder
							.baseUrl("https://api.github.com")
							.defaultHeader("Authorization", "Bearer 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")
							.build();
	}

	public Mono<UserResponse> findUserByName(String name) {
		log.info("Buscando usuário por nome [{}]", name);
		return webclient
				.get()
				.uri("/users/" + name)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(UserResponse.class);
	}

	public Flux<UserLoginResponse> ListUserByStarredRepo(String owner, String repo) {
		log.info("Lists the people that have starred [{}] repository mantained by [{}]", repo, owner);
		return webclient
				.get()
				.uri("/repos/" + owner + "/" + repo + "/stargazers")
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToFlux(UserLoginResponse.class);
	}
}
