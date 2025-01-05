package org.example.gateway;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

	@Bean
	public RouteLocator routeLocator(
			RouteLocatorBuilder builder,
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
						.uri("ds://movies")
				)
				.route("genres", route -> route
						.host(hostUrl)
						.and()
						.path(
								"/api/genres",
								"/api/genres/{genreId}"
						)
						.uri("ds://genres")
				)
				.route("users", route -> route
						.host(hostUrl)
						.and()
						.path(
								"/api/users",
								"/api/users/**"
						)
						.uri("ds://users")
				)
				.build();
	}

	@Bean
	public GlobalFilter discoveryFilter(DiscoveryClient discoveryClient) {
		return new GlobalFilter() {
			@Override
			@SneakyThrows
			public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
				URI uri = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR);
				if (uri != null && "ds".equals(uri.getScheme())) {
					System.out.println("Discovery filter");
					System.out.println(uri.getHost());
					ServiceInstance instance = discoveryClient.getInstances(uri.getHost()).stream()
							.findFirst()
							.orElseThrow();
					System.out.println(instance.getHost());
					URI newUri = new URI(
							instance.getScheme(),   // Updated scheme
							uri.getUserInfo(),      // Keep the original user info
							instance.getHost(),     // Updated host
							instance.getPort(),     // Updated port
							uri.getPath(),          // Keep the original path
							uri.getQuery(),         // Keep the original query
							uri.getFragment()       // Keep the original fragment
					);
					exchange.getAttributes().put(ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR, newUri);
				}
				return chain.filter(exchange);
			}
		};
	}
}
