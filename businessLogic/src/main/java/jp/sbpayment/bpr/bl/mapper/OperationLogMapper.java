package jp.sbpayment.bpr.bl.mapper;

import jp.sbpayment.bpr.bl.dto.OperationLogDto;
import jp.sbpayment.bpr.bl.model.OperationLog;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OperationLogMapper extends BaseMapper<OperationLogDto, OperationLog> {

  OperationLogMapper INSTANCE = Mappers.getMapper(OperationLogMapper.class);

}
