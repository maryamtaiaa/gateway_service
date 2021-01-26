package org.sid.gatewaysevice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewaySeviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewaySeviceApplication.class, args);
	}

   //@Bean
    RouteLocator routeLocator(RouteLocatorBuilder builder){
		/*return builder.routes()
				.route((r)->r.path("/customers/**").uri("http://localhost:8081/").id("r1"))
				.route((r)->r.path("/products/**").uri("http://localhost:8082/").id("r2"))
				.build();*/
    	
    	return builder.routes()
				.route((r)->r.path("/customers/**").uri("lb://CUSTOMER-SERVICE").id("r1"))
				.route((r)->r.path("/products/**").uri("lb://PRODUCT-SERVICE").id("r2"))
				.build();
    }
    
    @Bean
    DiscoveryClientRouteDefinitionLocator definitionLocator(ReactiveDiscoveryClient rdc,DiscoveryLocatorProperties properties) {
		return new DiscoveryClientRouteDefinitionLocator(rdc,properties);
    	
    }
    
    
    
	
	
}
