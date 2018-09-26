
package com.naveen.reactive;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Mono;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;


@RestController
public class ReactiveController {

	private static final Logger logger = LoggerFactory.getLogger(ReactiveController.class);

	private final WebClient webClient = WebClient.create("http://localhost:8090");

	@GetMapping(value = "/reactiveService")
	public Mono<String> reactiveService(){
		logger.debug("reactiveService Request processing started");
		return webClient.get().uri("/sleep/1000")
				.retrieve().bodyToMono(Boolean.class)
				.doOnNext(response->{ logger.debug("reactive service");})
				.then(Mono.just("reactiveService"));

	}
}
