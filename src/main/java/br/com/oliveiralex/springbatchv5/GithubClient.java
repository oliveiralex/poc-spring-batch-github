package br.com.oliveiralex.springbatchv5;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class GithubClient {

	private final WebClient webclient;

	public GithubClient(WebClient.Builder builder) {
		webclient = builder.baseUrl("https://api.github.com").build();
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
}
