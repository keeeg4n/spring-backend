package dev.keeg4n.springboot_webflux.router;

import dev.keeg4n.springboot_webflux.handler.CustomerHandler;
import dev.keeg4n.springboot_webflux.handler.CustomerStreamHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouterConfig {

    private final CustomerHandler customerHandler;

    private final CustomerStreamHandler customerStreamHandler;

    public RouterConfig(CustomerHandler customerHandler, CustomerStreamHandler customerStreamHandler) {
        this.customerHandler = customerHandler;
        this.customerStreamHandler = customerStreamHandler;
    }

    @Bean
    public RouterFunction<ServerResponse> routerFunction() {
        return RouterFunctions
                .route()
                .GET("router/customers", customerHandler::getCustomer)
                .GET("router/customers/stream", customerStreamHandler::getCustomerStream)
                .GET("router/customers/{input}", customerHandler::getCustomerFromInput)
                .POST("router/customers/create", customerHandler::saveCustomer)
                .build();
    }

}
