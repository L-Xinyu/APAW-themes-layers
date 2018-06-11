package api.daos.memory;

import api.daos.DaoFactory;
import api.daos.SuggestionDao;
import api.daos.UserDao;

public class DaoMemoryFactory extends DaoFactory {

    private UserDao userDao;

    private SuggestionDao suggestionDao;

    @Override
    public UserDao getUserDao() {
        if (userDao == null) {
            this.userDao = new UserDaoMemory();
        }
        return this.userDao;
    }

    @Override
    public SuggestionDao getSuggestionDao() {
        if (suggestionDao == null) {
            this.suggestionDao = new SuggestionDaoMemory();
        }
        return this.suggestionDao;
    }
}
