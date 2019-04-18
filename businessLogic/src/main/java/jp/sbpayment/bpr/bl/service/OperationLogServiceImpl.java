package jp.sbpayment.bpr.bl.service;

import jp.sbpayment.bpr.bl.dto.OperationLogDto;
import jp.sbpayment.bpr.bl.mapper.BaseMapper;
import jp.sbpayment.bpr.bl.mapper.OperationLogMapper;
import jp.sbpayment.bpr.bl.model.OperationLog;
import jp.sbpayment.bpr.bl.repository.OperationLogRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OperationLogServiceImpl extends
    BusinessLogicServiceImpl<OperationLogRepository, OperationLogDto, OperationLog>
    implements OperationLogService {

  private final OperationLogRepository operationLogRepository;

  @Override
  protected OperationLogRepository getRepository() {
    return this.operationLogRepository;
  }

  @Override
  protected BaseMapper<OperationLogDto, OperationLog> getMapper() {
    return OperationLogMapper.INSTANCE;
  }

}
