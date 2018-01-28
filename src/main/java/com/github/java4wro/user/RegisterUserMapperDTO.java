package com.github.java4wro.user;

import com.github.java4wro.user.dto.RegisterUserDTO;
import com.github.java4wro.user.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)

public interface RegisterUserMapperDTO {

    @Mapping(target = "confirmedPassword", ignore = true)
    RegisterUserDTO toRegistrationUserDTO(User user);
}
