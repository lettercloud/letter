package org.letter.console.admin.user.mapstruct;

import org.letter.console.admin.user.domain.User;
import org.letter.console.admin.user.domain.dto.UserLoginDto;
import org.letter.console.base.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * UserLoginMapper
 * @author letter
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserLoginMapper extends BaseMapper<UserLoginDto, User> {
}
