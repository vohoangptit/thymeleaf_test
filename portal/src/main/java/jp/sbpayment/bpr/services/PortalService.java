package jp.sbpayment.bpr.services;

import jp.sbpayment.bpr.bl.dto.BaseDto;
import jp.sbpayment.bpr.bl.model.BaseEntity;
import jp.sbpayment.bpr.bl.service.BusinessLogicService;

public interface PortalService<D extends BaseDto, E extends BaseEntity, 
    S extends BusinessLogicService<D, E>> {
  
}
