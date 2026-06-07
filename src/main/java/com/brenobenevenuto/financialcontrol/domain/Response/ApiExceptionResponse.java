package com.brenobenevenuto.financialcontrol.domain.Response;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public record ApiExceptionResponse(
    int statusCode,
    String error,
    List<?> detail
) {


    private static final ObjectMapper MAPPER = new ObjectMapper();

    public String writeApiExceptionResponseAsJson() {
        try {
            return MAPPER.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Erro ao serializar ApiExceptionResponse", e);
        }
    }
}
