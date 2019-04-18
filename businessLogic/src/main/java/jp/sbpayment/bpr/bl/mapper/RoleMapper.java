package jp.sbpayment.bpr.bl.mapper;

import jp.sbpayment.bpr.bl.dto.RoleDto;
import jp.sbpayment.bpr.bl.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleMapper extends BaseMapper<RoleDto, Role> {

  RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

}
