package jp.sbpayment.bpr.bl.mapper;

import jp.sbpayment.bpr.bl.dto.NoticeDto;
import jp.sbpayment.bpr.bl.model.Notice;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface NoticeMapper extends BaseMapper<NoticeDto, Notice> {

  NoticeMapper INSTANCE = Mappers.getMapper(NoticeMapper.class);

}
