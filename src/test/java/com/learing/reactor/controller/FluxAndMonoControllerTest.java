/**
 * 
 */
package com.learing.reactor.controller;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

/**
 * @author alexsurya
 *
 */
@RunWith(SpringRunner.class)
@WebFluxTest
public class FluxAndMonoControllerTest {

	@Autowired
	// non blocking test client
	WebTestClient webTestClient;

	@Test
	public void testIntegerApproch() {
		
		Flux<Integer> fluxTest = webTestClient.get().uri("/api/flux2")
								.accept(MediaType.APPLICATION_JSON)
								.exchange()
								.expectStatus().isOk()
								.returnResult(Integer.class)
								.getResponseBody();
		
		StepVerifier.create(fluxTest)
								.expectSubscription()
								.expectNext(1)
								.expectNext(2)
								.expectNext(3)
								.expectNext(4)
								.verifyComplete();
	}
	
	
	@Test
	public void testStringApproch() {
		
		Flux<String> stringFluxTest = webTestClient.get().uri("/api/flux3")
										.accept(MediaType.APPLICATION_JSON_UTF8)
										.exchange()
										.expectStatus().isOk()
										.returnResult(String.class)
										.getResponseBody();
		
		StepVerifier.create(stringFluxTest)
										.expectSubscription()
										.expectNext("alex")
										.expectNext("surya")
										.expectNext("baba")
										.verifyComplete();
	}
	
	@Test
	public void testSizeApproch() {
		
		webTestClient.get().uri("/api/flux2")
							.accept(MediaType.APPLICATION_JSON_UTF8)
							.exchange()
							.expectStatus().isOk()
							.expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
							.expectBodyList(Integer.class)
							.hasSize(4);
	}
	
	@Test
	public void assetCheck() {
		
		
		List<Integer> expectResult = Arrays.asList(1,2,3,4);
		
		
		EntityExchangeResult<List<Integer>> actualResult =	webTestClient.get().uri("/api/flux2")
							.accept(MediaType.APPLICATION_JSON_UTF8)
							.exchange()
							.expectStatus().isOk()
							.expectBodyList(Integer.class)
							.returnResult();
	
		assertEquals(expectResult, actualResult.getResponseBody());
	}
	
	@Test
	public void assetCheck1() {
		
		
		List<Integer> expectResult = Arrays.asList(1,2,3,4);
		
		webTestClient.get().uri("/api/flux2")
							.accept(MediaType.APPLICATION_JSON_UTF8)
							.exchange()
							.expectStatus().isOk()
							.expectBodyList(Integer.class)
							.consumeWith(((response) -> {
								assertEquals(expectResult, response.getResponseBody());
							}));
	}
}
