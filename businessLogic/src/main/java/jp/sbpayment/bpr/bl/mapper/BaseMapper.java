package jp.sbpayment.bpr.bl.mapper;

import jp.sbpayment.bpr.bl.dto.BaseDto;
import jp.sbpayment.bpr.bl.model.BaseEntity;
import org.mapstruct.InheritInverseConfiguration;

public interface BaseMapper<D extends BaseDto, E extends BaseEntity> {
  
  @InheritInverseConfiguration()
  E toEntity(D dto);

  D toDto(E entity);

}
