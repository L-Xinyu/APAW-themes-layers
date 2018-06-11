package api.daos.memory;

import api.daos.ThemeDao;
import api.entities.Theme;

import java.util.HashMap;

public class ThemeDaoMemory extends GenericDaoMemory<Theme> implements ThemeDao {

    public ThemeDaoMemory() {
        super(new HashMap<>());
    }

    @Override
    public String getId(Theme theme) {
        return theme.getId();
    }

    @Override
    public void setId(Theme theme, String id) {
        theme.setId(id);
    }
}
