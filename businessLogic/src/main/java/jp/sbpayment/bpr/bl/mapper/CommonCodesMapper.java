package jp.sbpayment.bpr.bl.mapper;

import jp.sbpayment.bpr.bl.dto.CommonCodesDto;
import jp.sbpayment.bpr.bl.model.CommonCodes;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommonCodesMapper extends BaseMapper<CommonCodesDto, CommonCodes> {

  CommonCodesMapper INSTANCE = Mappers.getMapper(CommonCodesMapper.class);

}
