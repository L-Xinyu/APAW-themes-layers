package api;

import api.apiControllers.SuggestionApiController;
import api.daos.DaoFactory;
import api.daos.memory.DaoMemoryFactory;
import api.dtos.SuggestionDto;
import http.Client;
import http.HttpException;
import http.HttpRequest;
import http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SuggestionIT {

    @BeforeAll
    static void before() {
        DaoFactory.setFactory(new DaoMemoryFactory());
    }

    @Test
    void testCreateUser() {
        HttpRequest request = HttpRequest.builder().path(SuggestionApiController.SUGGESTIONS).body(new SuggestionDto(false, "Mejorable...")).post();
        new Client().submit(request).getBody();
    }

    @Test
    void createSuggestionWithoutSuggestionDto() {
        HttpRequest request = HttpRequest.builder().path("/suggestions").body(null).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    void createSuggestionWithoutSuggestionDtoNegative() {
        HttpRequest request = HttpRequest.builder().path("/suggestions").body(new SuggestionDto(null, "Mejorable...")).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

}
