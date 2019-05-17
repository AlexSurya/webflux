/**
 * 
 */
package com.learing.reactor.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

/**
 * @author alexsurya
 *
 */
@RestController
@RequestMapping("api/")
public class MonoController {

	//used to get the single data using mono
	@GetMapping(path = "mono")
	public Mono<Integer> getRecordUsingMono() {
		
		return Mono.just(1).log();
	}
}
