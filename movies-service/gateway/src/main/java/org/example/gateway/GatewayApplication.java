package org.example.gateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

	@Bean
	public RouteLocator routeLocator(
			RouteLocatorBuilder builder,
			@Value("${movie.url}") String movieUrl,
			@Value("${genre.url}") String genreUrl,
			@Value("${gateway.url}") String hostUrl
	){
		return builder
				.routes()
				.route("movies", route -> route
						.host(hostUrl)
						.and()
						.path(
								"/api/movies",
								"/api/movies/**",
								"/api/genres/{genreId}/movies"
						)
						.uri(movieUrl)
				)
				.route("genres", route -> route
						.host(hostUrl)
						.and()
						.path(
								"/api/genres",
								"/api/genres/{genreId}"
						)
						.uri(genreUrl)
				)
				.build();
	}
}
