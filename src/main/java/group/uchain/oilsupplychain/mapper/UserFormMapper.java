package group.uchain.oilsupplychain.mapper;

import group.uchain.oilsupplychain.dto.User;

public interface UserFormMapper {

    User  selectByUsername(String username);

    String getUsernameById(Long id);

}