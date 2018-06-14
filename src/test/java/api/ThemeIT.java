package api;

import api.apiControllers.ThemeApiController;
import api.apiControllers.UserApiController;
import api.daos.DaoFactory;
import api.daos.memory.DaoMemoryFactory;
import api.dtos.ThemeDto;
import api.dtos.ThemeIdReferenceDto;
import api.dtos.UserDto;
import api.entities.Category;
import http.Client;
import http.HttpException;
import http.HttpRequest;
import http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ThemeIT {

    @BeforeAll
    static void before() {
        DaoFactory.setFactory(new DaoMemoryFactory());
    }

    @Test
    void testCreateTheme() {
        this.createTheme("Theme one");
    }

    private String createTheme(String theme) {
        String userId = this.createUser();
        HttpRequest request = HttpRequest.builder().path(ThemeApiController.THEMES)
                .body(new ThemeDto(theme, Category.SPORT, userId)).post();
        return (String) new Client().submit(request).getBody();
    }

    private String createUser() {
        HttpRequest request = HttpRequest.builder().path(UserApiController.USERS).body(new UserDto("uno")).post();
        return (String) new Client().submit(request).getBody();
    }

    @Test
    void testCreateThemeUserIdNotFound() {
        HttpRequest request = HttpRequest.builder().path(ThemeApiController.THEMES)
                .body(new ThemeDto("Theme one", Category.SPORT, "h3rFdEsw")).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.NOT_FOUND, exception.getHttpStatus());
    }

    @Test
    void testCreateThemeWithoutCategory() {
        HttpRequest request = HttpRequest.builder().path(ThemeApiController.THEMES)
                .body(new ThemeDto("Theme one", null, "h3rFdEsw")).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    void testReadAll() {
        for (int i = 0; i < 5; i++) {
            this.createTheme("theme" + i);
        }
        HttpRequest request = HttpRequest.builder().path(ThemeApiController.THEMES).get();
        List<ThemeIdReferenceDto> themes = (List<ThemeIdReferenceDto>) new Client().submit(request).getBody();
        assertTrue(themes.size() >= 5);
    }

    @Test
    void testDelete() {
        String id = this.createTheme("uno");
        HttpRequest request1 = HttpRequest.builder().path(ThemeApiController.THEMES).get();
        int count = ((List<ThemeIdReferenceDto>) new Client().submit(request1).getBody()).size();
        HttpRequest request2 = HttpRequest.builder().path(ThemeApiController.THEMES).path(UserApiController.ID_ID)
                .expandPath(id).delete();
        new Client().submit(request2);
        assertTrue(((List<ThemeIdReferenceDto>) new Client().submit(request1).getBody()).size() < count);
    }

    @Test
    void testVoteTheme() {
        String id = this.createTheme("uno");
        HttpRequest request = HttpRequest.builder().path(ThemeApiController.THEMES).path(UserApiController.ID_ID)
                .expandPath(id).path(ThemeApiController.VOTES).body(5).post();
        new Client().submit(request);
        new Client().submit(request);
    }

}
