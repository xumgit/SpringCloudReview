package com.xum.eurekazuul.provider;

import com.netflix.hystrix.exception.HystrixTimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Component
public class ApiFallbackProvider implements FallbackProvider {

    private static final Logger LOG = LoggerFactory.getLogger(ApiFallbackProvider.class);

    @Override
    public String getRoute() {
        LOG.info("ApiFallbackProvider=>getRoute");
        return "*";
    }

    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        LOG.info(String.format("route:%s,exceptionType:%s,stackTrace:%s", route, cause.getClass().getName(), cause.getStackTrace()));
        String message = "";
        if (cause instanceof HystrixTimeoutException) {
            message = "Timeout";
        } else {
            message = "Service exception";
        }
        return fallbackResponse(message);
    }

    public ClientHttpResponse fallbackResponse(String message) {

        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                LOG.info("ApiFallbackProvider=>ClientHttpResponse=>getStatusCode");
                return HttpStatus.OK;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                LOG.info("ApiFallbackProvider=>ClientHttpResponse=>getRawStatusCode");
                return 200;
            }

            @Override
            public String getStatusText() throws IOException {
                LOG.info("ApiFallbackProvider=>ClientHttpResponse=>getStatusText");
                return "OK";
            }

            @Override
            public void close() {
                LOG.info("ApiFallbackProvider=>ClientHttpResponse=>close");
            }

            @Override
            public InputStream getBody() throws IOException {
                LOG.info("ApiFallbackProvider=>ClientHttpResponse=>getBody");
                String bodyText = String.format("{\"code\": 999,\"message\": \"Service unavailable:%s\"}", message);
                return new ByteArrayInputStream(bodyText.getBytes());
            }

            @Override
            public HttpHeaders getHeaders() {
                LOG.info("ApiFallbackProvider=>ClientHttpResponse=>getHeaders");
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                return headers;
            }
        };
    }
}
