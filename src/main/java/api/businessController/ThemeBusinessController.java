package api.businessController;

import api.daos.DaoFactory;
import api.dtos.ThemeDto;
import api.dtos.ThemeIdReferenceDto;
import api.entities.Category;
import api.entities.Theme;
import api.entities.User;
import api.entities.Vote;
import api.exceptions.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

public class ThemeBusinessController {

    public String create(ThemeDto themeDto) {
        User user = DaoFactory.getFactory().getUserDao().read(themeDto.getUserId())
                .orElseThrow(() -> new NotFoundException("User (" + themeDto.getUserId() + ")"));
        Theme theme = new Theme(themeDto.getReference(), themeDto.getCategory(), user);
        DaoFactory.getFactory().themeDao().save(theme);
        return theme.getId();
    }

    public List<ThemeIdReferenceDto> readAll() {
        return DaoFactory.getFactory().themeDao().findAll()
                .stream().map(theme -> new ThemeIdReferenceDto(theme))
                .collect(Collectors.toList());
    }

    public void delete(String id) {
        DaoFactory.getFactory().themeDao().deleteById(id);
    }

    public void createVote(String themeId, Integer vote) {
        Theme theme = DaoFactory.getFactory().themeDao().read(themeId)
                .orElseThrow(() -> new NotFoundException("Theme (" + themeId + ")"));
        theme.getVotes().add(new Vote(vote));
        DaoFactory.getFactory().themeDao().save(theme);
    }

    public Double readAverage(String themeId) {
        Theme theme = DaoFactory.getFactory().themeDao().read(themeId)
                .orElseThrow(() -> new NotFoundException("Theme (" + themeId + ")"));
        return theme.getVotes().stream().mapToDouble(Vote::getValue).average().orElse(Double.NaN);
    }

    public void updateCategory(String themeId, Category category) {
        Theme theme = DaoFactory.getFactory().themeDao().read(themeId)
                .orElseThrow(() -> new NotFoundException("Theme (" + themeId + ")"));
        theme.setCategory(category);
        DaoFactory.getFactory().themeDao().save(theme);
    }
}
