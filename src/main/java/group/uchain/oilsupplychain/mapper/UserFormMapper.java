package group.uchain.oilsupplychain.mapper;

import group.uchain.oilsupplychain.dto.User;

import java.util.List;

public interface UserFormMapper {

    User  selectByUsername(String username);

    String getUsernameById(Long id);

    List<String> getRefinery();

}