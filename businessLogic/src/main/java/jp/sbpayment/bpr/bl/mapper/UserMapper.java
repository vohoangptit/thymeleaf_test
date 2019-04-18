package jp.sbpayment.bpr.bl.mapper;

import jp.sbpayment.bpr.bl.dto.UserDto;
import jp.sbpayment.bpr.bl.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper extends BaseMapper<UserDto, User> {

  UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

}
