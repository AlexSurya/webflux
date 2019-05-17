/**
 * 
 */
package com.learing.reactor.router;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.learing.reactor.handler.FluxAndMonoHandler;

/**
 * @author alexsurya
 *
 */
@Configuration
public class RouterConfiguration {

	@Bean
	public RouterFunction<ServerResponse> router(FluxAndMonoHandler fluxAndMonoHandler) {

		return RouterFunctions
				.route(GET("/functional/flux").and(accept(MediaType.APPLICATION_JSON_UTF8)),fluxAndMonoHandler::fluxExample)
				.andRoute(GET("/functional/mono").and(accept(MediaType.APPLICATION_JSON_UTF8)),fluxAndMonoHandler::monoExample);
	}
}
