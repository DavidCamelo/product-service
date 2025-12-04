package com.davidcamelo.product.config;

import com.davidcamelo.product.util.http.UserClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.loadbalancer.LoadBalancerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Slf4j
@Configuration
public class BeansConfig {

    @Bean
    public UserClient userClient(LoadBalancerInterceptor loadBalancerInterceptor) {
        var restClient = RestClient.builder()
                .baseUrl("http://users")
                .requestInterceptor(loadBalancerInterceptor)
                .build();
        var factory = HttpServiceProxyFactory.builderFor(RestClientAdapter.create(restClient)).build();
        return factory.createClient(UserClient.class);
    }
}