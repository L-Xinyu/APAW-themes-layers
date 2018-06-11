package api.businessController;

import api.daos.DaoFactory;
import api.entities.Suggestion;
import api.dtos.SuggestionDto;

public class SuggestionBusinessController {

    public void create(SuggestionDto suggestionDto) {
        Suggestion suggestion = new Suggestion(suggestionDto.getNegative(), suggestionDto.getDescription());
        DaoFactory.getFactory().getSuggestionDao().save(suggestion);
    }

}
