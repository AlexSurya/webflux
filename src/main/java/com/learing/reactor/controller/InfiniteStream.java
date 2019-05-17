/**
 * 
 */
package com.learing.reactor.controller;

import java.time.Duration;

import org.springframework.http.MediaType;
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
public class InfiniteStream {

	// This method is used to print the infinite data
	@GetMapping(path = "infinite", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
	public Flux<Long> infinite() {

		return Flux.interval(Duration.ofSeconds(1)).log();
	}
}
