/**
 * 
 */
package com.learing.reactor.controller;

import java.time.Duration;

import javax.print.attribute.standard.Media;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

/**
 * @author alexsurya
 *
 */
@RestController
@RequestMapping("api/")
public class FluxController {

	@GetMapping(path = "flux")
	public ResponseEntity<Object> returnFlux() {

		Flux<String> fluxDemo = Flux.just("alex", "surya", "baba")
				.delayElements(Duration.ofSeconds(1))
				.log();
		return new ResponseEntity<Object>(fluxDemo, HttpStatus.OK);
	}
	
	@GetMapping(path = "flux1", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
	public ResponseEntity<Object> returnFlux1() {

		Flux<String> fluxDemo = Flux.just("alex", "surya", "baba")
				.delayElements(Duration.ofSeconds(1))
				.log();
		return new ResponseEntity<Object>(fluxDemo, HttpStatus.OK);
	}
	
	
	@GetMapping(path = "flux2")
	public Flux<Integer> returnFlux2() {

		Flux<Integer> fluxDemo = Flux.just(1,2,3,4)
				.delayElements(Duration.ofSeconds(1))
				.log();
		return Flux.just(1,2,3,4)
				.delayElements(Duration.ofSeconds(1))
				.log();
	}
	
	
	@GetMapping(path = "flux3")
	public Flux<String> returnFlux3() {

		Flux<String> fluxDemo = Flux.just("alex", "surya", "baba")
				.delayElements(Duration.ofSeconds(1))
				.log();
		return  Flux.just("alex","surya","baba")
				.delayElements(Duration.ofSeconds(1))
				.log();
	}
}
