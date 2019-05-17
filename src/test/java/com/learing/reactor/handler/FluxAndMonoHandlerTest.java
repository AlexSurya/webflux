/**
 * 
 */
package com.learing.reactor.handler;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
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
//@WebFluxTest
@SpringBootTest
@AutoConfigureWebTestClient
public class FluxAndMonoHandlerTest {

	@Autowired
	WebTestClient webTestClient;

	@Test
	public void handlerTest() {

		Flux<Integer> fluxTest = webTestClient.get().uri("/functional/flux").accept(MediaType.APPLICATION_JSON_UTF8)
				.exchange().expectStatus().isOk().returnResult(Integer.class).getResponseBody();

		StepVerifier.create(fluxTest).expectSubscription().expectNext(1).expectNext(2).expectNext(3).expectNext(4).verifyComplete();
	}
	
	@Test
	public void assetTest() {
		
		List<Integer> expectList = Arrays.asList(1, 2, 3, 4);
		
		EntityExchangeResult<List<Integer>> actualList = webTestClient.get().uri("/functional/flux")
														 .accept(MediaType.APPLICATION_JSON_UTF8)
														 .exchange()
														 .expectStatus().isOk()
														 .expectBodyList(Integer.class)
														 .returnResult();
		
		assertEquals(expectList, actualList.getResponseBody());
	}
	
	@Test
	public void assetTest1() {
		
		List<Integer> expectResult = Arrays.asList(1, 2, 3, 4, 6);
		
		webTestClient.get().uri("/functional/flux").accept(MediaType.APPLICATION_JSON_UTF8)
			.exchange()
			.expectStatus().isOk()
			.expectBodyList(Integer.class)
			.consumeWith((response) -> {
				assertEquals(expectResult, response.getResponseBody());
			});
	}
	
	@Test
	public void monoAssertTest() {
		
		List<Integer> expectResult = Arrays.asList(4);
		
		EntityExchangeResult<List<Integer>> actualResult = webTestClient.get().uri("/functional/mono").accept(MediaType.APPLICATION_JSON_UTF8)
			.exchange()
			.expectStatus().isOk()
			.expectBodyList(Integer.class)
			.returnResult();
		
		assertEquals(expectResult, actualResult.getResponseBody());
	}
	
	@Test
	public void monoAssertTest1() {
		
		List<Integer> expectResult = Arrays.asList(4);
		
		webTestClient.get().uri("/functional/mono").accept(MediaType.APPLICATION_JSON_UTF8)
			.exchange()
			.expectStatus().isOk()
			.expectBodyList(Integer.class)
			.consumeWith((actualResult) -> {
				assertEquals(expectResult, actualResult.getResponseBody());
			});
	}
}
