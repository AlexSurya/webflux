/**
 * 
 */
package com.learing.reactor.test;

import org.junit.Test;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

/**
 * @author alexsurya
 *
 */
public class FluxAndMonoPlayGround {

	@Test
	public void fluxTest() {

		Flux<String> fluxString = Flux.just("alex", "Surya", "baba").log();
		fluxString.subscribe(System.out::println);
	}

	// Exception Handler
	@Test
	public void testExceptionOnFlux() {

		Flux<String> exceptionHandlerFlux = Flux.just("alex", "arun", "raj")
				.concatWith(Flux.error(new RuntimeException("Error has been occured"))).log();
		exceptionHandlerFlux.subscribe(System.out::println, (e) -> System.err.print(e));
	}

	// verifying the unittesting
	@Test
	public void unitTest_Verify() {

		Flux<String> unitTest = Flux.just("test1", "test2", "test3")
				.concatWith(Flux.error(new RuntimeException("exception occurred"))).log();

		StepVerifier.create(unitTest).expectNext("test1").expectNext("test2").expectNext("test3")
//		.expectError(RuntimeException.class)
				.expectErrorMessage("exception occurred").verify();
	}

	@Test
	public void unitTest_Count() {

		Flux<String> unitCount = Flux.just("one", "two", "three")
				.concatWith(Flux.error(new RuntimeException("exception occurred"))).log();
		StepVerifier.create(unitCount).expectNext("one", "two", "three")
//		.expectError(RuntimeException.class)
				.expectErrorMessage("exception occurred").verify();
	}

	// Mono
	@Test
	public void monoUnitTest() {

		Mono<String> testMono = Mono.just("yoYo").log();
		testMono.subscribe(System.out::println);
	}

	// Mono_Error
	/*
	 * @Test public void monoError() { Mono<String> monoError =
	 * Mono.just("HelloWorld").concatWith(Mono.error(new
	 * RuntimeException("error"))).log();
	 * StepVerifier.create(monoError).expectNext("HellowWorld").expectErrorMessage(
	 * "error").verify(); }
	 */

	// mono_verify
	@Test
	public void monoVerify() {

		StepVerifier.create(Mono.error(new RuntimeException("error")).log()).expectError(RuntimeException.class)
				.verify();
	}

}
