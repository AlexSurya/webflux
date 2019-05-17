/**
 * 
 */
package com.learing.reactor.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

/**
 * @author alexsurya
 *
 */
@RunWith(SpringRunner.class)
@WebFluxTest
public class InfiniteController {

	@Autowired
	WebTestClient webTestClient;
	
	//Unit test for infinite streaming
	@Test
	public void infiniteTest() {
		
		Flux<Long> infiniteLong = webTestClient.get().uri("/api/infinite")
			.accept(MediaType.APPLICATION_STREAM_JSON)
			.exchange()
			.expectStatus().isOk()
			.returnResult(Long.class)
			.getResponseBody();
	
		StepVerifier.create(infiniteLong)
			.expectNext(0l)
			.expectNext(1l)
			.expectNext(2l)
			.thenCancel()
			.verify();
	
	}
	
}
