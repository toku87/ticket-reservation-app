package com.github.java4wro.user;

import com.github.java4wro.user.dto.RegisterUserDTO;
import com.github.java4wro.user.dto.UserDTO;
import com.github.java4wro.user.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {



    UserDTO toUserDTO (User user);
    List <UserDTO> toUserDTO (List<User> users);




}
