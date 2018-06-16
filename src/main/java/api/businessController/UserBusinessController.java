package api.businessController;

import api.daos.DaoFactory;
import api.dtos.UserDto;
import api.entities.User;
import api.exceptions.NotFoundException;

public class UserBusinessController {

    public String create(UserDto userDto) {
        User user = new User(userDto.getNick(), null);
        DaoFactory.getFactory().getUserDao().save(user);
        return user.getId();
    }

    public void updateNick(String id, UserDto userDto) {
        User user = DaoFactory.getFactory().getUserDao().read(id)
                .orElseThrow(() -> new NotFoundException("User id: " + id));
        user.setNick(userDto.getNick());
        DaoFactory.getFactory().getUserDao().save(user);
    }

}
