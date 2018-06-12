package api.businessController;

import api.daos.DaoFactory;
import api.dtos.ThemeDto;
import api.dtos.ThemeIdReferenceDto;
import api.entities.Theme;
import api.entities.User;
import api.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.List;

public class ThemeBusinessController {

    public String create(ThemeDto themeDto) {
        User user = DaoFactory.getFactory().getUserDao().read(themeDto.getUserId()).orElseThrow(
                () -> new NotFoundException("User (" + themeDto.getUserId() + ")"));
        Theme theme = new Theme(themeDto.getReference(), themeDto.getCategory(), user);
        DaoFactory.getFactory().themeDao().save(theme);
        return theme.getId();
    }

    public List<ThemeIdReferenceDto> readAll() {
        List<Theme> themes = DaoFactory.getFactory().themeDao().findAll();
        List<ThemeIdReferenceDto> themeIdReferenceDto = new ArrayList<>();
        for (Theme theme : themes) {
            themeIdReferenceDto.add(new ThemeIdReferenceDto(theme));
        }
        return themeIdReferenceDto;
    }
}
