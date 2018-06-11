package api.businessController;

import api.daos.DaoFactory;
import api.dtos.ThemeDto;
import api.entities.Theme;
import api.entities.User;
import api.exceptions.NotFoundException;

public class ThemeBusinessController {

    public String create(ThemeDto themeDto) {
        User user = DaoFactory.getFactory().getUserDao().read(themeDto.getUserId()).orElseThrow(
                () -> new NotFoundException("User (" + themeDto.getUserId() + ")"));
        Theme theme = new Theme(themeDto.getReference(), themeDto.getCategory(), user);
        DaoFactory.getFactory().themeDao().save(theme);
        return theme.getId();
    }

}
