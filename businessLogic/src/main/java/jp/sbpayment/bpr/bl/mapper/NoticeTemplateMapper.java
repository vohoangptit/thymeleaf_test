package jp.sbpayment.bpr.bl.mapper;

import jp.sbpayment.bpr.bl.dto.NoticeTemplateDto;
import jp.sbpayment.bpr.bl.model.NoticeTemplate;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface NoticeTemplateMapper extends BaseMapper<NoticeTemplateDto, NoticeTemplate> {

  NoticeTemplateMapper INSTANCE = Mappers.getMapper(NoticeTemplateMapper.class);

}
