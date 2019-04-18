package jp.sbpayment.bpr.bl.mapper;

import jp.sbpayment.bpr.bl.dto.NoticeMailDto;
import jp.sbpayment.bpr.bl.model.NoticeMail;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface NoticeMailMapper extends BaseMapper<NoticeMailDto, NoticeMail> {

  NoticeMailMapper INSTANCE = Mappers.getMapper(NoticeMailMapper.class);

}
