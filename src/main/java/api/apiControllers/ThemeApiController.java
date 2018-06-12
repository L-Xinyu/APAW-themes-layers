package api.apiControllers;

import api.businessController.ThemeBusinessController;
import api.dtos.ThemeDto;
import api.dtos.ThemeIdReferenceDto;
import api.exceptions.ArgumentNotValidException;

import java.util.List;

public class ThemeApiController {
    public static final String THEMES = "/themes";

    public static final String ID_ID = "/{id}";

    private ThemeBusinessController themeBusinessController = new ThemeBusinessController();

    public String create(ThemeDto themeDto) {
        this.validate(themeDto, "themeDto");
        this.validate(themeDto.getReference(), "themeDto reference");
        this.validate(themeDto.getCategory(), "themeDto category");
        this.validate(themeDto.getUserId(), "themeDto user id");
        return this.themeBusinessController.create(themeDto);
    }

    private void validate(Object property, String message) {
        if (property == null) {
            throw new ArgumentNotValidException(message + " is NULL");
        }
    }

    public List<ThemeIdReferenceDto> readAll() {
        return this.themeBusinessController.readAll();
    }

    public void delete(String id) {
        this.themeBusinessController.delete(id);
    }
}
