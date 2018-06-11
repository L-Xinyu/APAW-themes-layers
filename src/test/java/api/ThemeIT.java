package api;

import api.apiControllers.ThemeApiController;
import api.apiControllers.UserApiController;
import api.daos.DaoFactory;
import api.daos.memory.DaoMemoryFactory;
import api.dtos.ThemeDto;
import api.dtos.UserDto;
import api.entities.Category;
import http.Client;
import http.HttpException;
import http.HttpRequest;
import http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ThemeIT {

    @BeforeAll
    static void before() {
        DaoFactory.setFactory(new DaoMemoryFactory());
    }

    private String createUser() {
        HttpRequest request = HttpRequest.builder().path(UserApiController.USERS).body(new UserDto("uno")).post();
        return (String) new Client().submit(request).getBody();
    }

    @Test
    void createTheme() {
        String userId = this.createUser();
        HttpRequest request = HttpRequest.builder().path(ThemeApiController.THEMES)
                .body(new ThemeDto("Theme one", Category.SPORT, userId)).post();
        new Client().submit(request);
    }

    @Test
    void createThemeUserIdNotFound() {
        HttpRequest request = HttpRequest.builder().path(ThemeApiController.THEMES)
                .body(new ThemeDto("Theme one", Category.SPORT, "h3rFdEsw")).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.NOT_FOUND, exception.getHttpStatus());
    }

    @Test
    void createThemeWithoutCategory() {
        HttpRequest request = HttpRequest.builder().path(ThemeApiController.THEMES)
                .body(new ThemeDto("Theme one", null, "h3rFdEsw")).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

}
