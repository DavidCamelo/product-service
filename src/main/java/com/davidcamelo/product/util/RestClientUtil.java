package com.davidcamelo.product.util;

import com.davidcamelo.product.dto.ErrorDTO;
import com.davidcamelo.product.dto.FilterDTO;
import com.davidcamelo.product.dto.RestClientResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

import java.util.Date;

@Slf4j
@Component
@RequiredArgsConstructor
public class RestClientUtil<T> {
    private final RestClient.Builder restClientBuilder;

    public RestClientResponse<T> create(String service, T dto, Class<T> tClass) {
        var restClientResponse = new RestClientResponse<T>();
        try {
            restClientResponse.setDTO(restClientBuilder.build().post().uri("http://{service}/api/{service}", service, service).body(dto).retrieve().body(tClass));
        } catch (Exception ex) {
            restClientResponse.setErrorDTO(handleException(ex));
        }
        return restClientResponse;
    }

    public RestClientResponse<T> getById(String service, Long id, Class<T> tClass) {
        var restClientResponse = new RestClientResponse<T>();
        try {
            restClientResponse.setDTO(restClientBuilder.build().get().uri("http://{service}/api/{service}/{id}", service, service, id).retrieve().body(tClass));
        } catch (Exception ex) {
            restClientResponse.setErrorDTO(handleException(ex));
        }
        return restClientResponse;
    }

    public RestClientResponse<Page<T>> getAll(String service, FilterDTO filterDTO, Class<Page> tClass) {
        var restClientResponse = new RestClientResponse<Page<T>>();
        try {
            restClientResponse.setDTO(restClientBuilder.build().get().uri("http://{service}/api/{service}", service, service).retrieve().body(tClass));
        } catch (Exception ex) {
            restClientResponse.setErrorDTO(handleException(ex));
        }
        return restClientResponse;
    }

    public RestClientResponse<T> update(String service, Long id, T dto, Class<T> tClass) {
        var restClientResponse = new RestClientResponse<T>();
        try {
            restClientResponse.setDTO(restClientBuilder.build().put().uri("http://{service}/api/{service}/{id}", service, service, id).body(dto).retrieve().body(tClass));
        } catch (Exception ex) {
            restClientResponse.setErrorDTO(handleException(ex));
        }
        return restClientResponse;
    }

    public RestClientResponse<T> delete(String service, Long id) {
        var restClientResponse = new RestClientResponse<T>();
        try {
            restClientBuilder.build().delete().uri("http://{service}/api/{service}/{id}", service, service, id).retrieve().toBodilessEntity();
        } catch (Exception ex) {
            restClientResponse.setErrorDTO(handleException(ex));
        }
        return restClientResponse;
    }

    private ErrorDTO handleException(Exception ex) {
        if (ex instanceof HttpClientErrorException httpEx) {
            try {
                return httpEx.getResponseBodyAs(ErrorDTO.class);
            } catch (Exception e) {
                log.error("Error parsing HttpClientErrorException response: {}", e.getMessage());
            }
        } else if (ex instanceof RestClientException restClientEx) {
            log.error("RestClientException {}",restClientEx.getMessage());
            return null;
        }
        return ErrorDTO.builder().message(ex.getMessage()).timestamp(new Date()).build();
    }
}
