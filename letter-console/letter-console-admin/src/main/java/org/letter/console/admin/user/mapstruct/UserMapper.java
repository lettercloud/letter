package org.letter.console.admin.user.mapstruct;

import org.letter.console.admin.user.domain.User;
import org.letter.console.admin.user.domain.dto.UserDto;
import org.letter.console.base.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author letter
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper extends BaseMapper<UserDto, User> {
}
