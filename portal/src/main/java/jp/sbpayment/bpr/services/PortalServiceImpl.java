package jp.sbpayment.bpr.services;

import jp.sbpayment.bpr.bl.dto.BaseDto;
import jp.sbpayment.bpr.bl.log.OperationLogCommon;
import jp.sbpayment.bpr.bl.model.BaseEntity;
import jp.sbpayment.bpr.bl.service.BusinessLogicService;
import org.springframework.beans.factory.annotation.Autowired;

public class PortalServiceImpl
    <D extends BaseDto, E extends BaseEntity, S extends BusinessLogicService<D, E>> 
    implements PortalService<D, E, S> {
  
  @Autowired
  protected OperationLogCommon operationLogCommon;

  @Autowired
  protected S service;
  
}
