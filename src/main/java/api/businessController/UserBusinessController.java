package api.businessController;

import api.daos.DaoFactory;
import api.dtos.UserDto;
import api.entities.User;

public class UserBusinessController {

    public String create(UserDto userDto) {
        User user = new User(userDto.getNick(),null);
        DaoFactory.getFactory().getUserDao().save(user);
        return user.getId();
    }

}
