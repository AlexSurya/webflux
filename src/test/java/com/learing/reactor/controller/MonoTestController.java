/**
 * 
 */
package com.learing.reactor.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import reactor.core.publisher.Mono;

/**
 * @author alexsurya
 *
 */
@RunWith(SpringRunner.class)
@WebFluxTest
public class MonoTestController {

	@Autowired
	WebTestClient webTestClient;
	
	//MonoTesting
	@Test
	public void monoTest() {
		
		Integer expectValue = new Integer(1);
		
		webTestClient.get().uri("/api/mono")
				.accept(MediaType.APPLICATION_JSON_UTF8)
				.exchange()
				.expectStatus().isOk()
				.expectBody(Integer.class)
				.consumeWith((response) -> {
					assertEquals(expectValue, response.getResponseBody());
				});
	}
}
