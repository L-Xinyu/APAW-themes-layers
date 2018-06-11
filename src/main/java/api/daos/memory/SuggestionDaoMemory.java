package api.daos.memory;

import api.daos.SuggestionDao;
import api.entities.Suggestion;

import java.util.HashMap;

public class SuggestionDaoMemory extends GenericDaoMemory<Suggestion> implements SuggestionDao {

    public SuggestionDaoMemory() {
        super(new HashMap<>());
    }

    @Override
    public String getId(Suggestion suggestion) {
        return suggestion.getId();
    }

    @Override
    public void setId(Suggestion suggestion, String id) {
        suggestion.setId(id);
    }
}
