package jp.sbpayment.bpr.bl.log;

import java.util.Date;
import java.util.List;
import jp.sbpayment.bpr.bl.dto.CommonCodesDto;
import jp.sbpayment.bpr.bl.dto.OperationLogDto;
import jp.sbpayment.bpr.bl.service.CommonCodeService;
import jp.sbpayment.bpr.bl.service.OperationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OperationLogCommon {

  @Autowired
  OperationLogCommon() {
  }

  @Autowired
  private OperationLogService operationLogService;

  @Autowired
  private CommonCodeService commonCodeService;

  /**
   * logOperation.
   * @param function function
   * @param operation operation
   * @param data json data
   */
  public void logOperation(String function, String operation, String data) {
    List<CommonCodesDto> functions = commonCodeService.findCommonCodesByCode(function);
    if (functions == null || functions.size() == 0) {
      return;
    }
    
    List<CommonCodesDto> operations = commonCodeService.findCommonCodesByCode(operation);
    if (operations == null || operations.size() == 0) {
      return;
    }
    
    OperationLogDto log = new OperationLogDto();
    log.setFunctionId(functions.get(0).getId());
    log.setOperationDate(new Date());
    log.setOperationCodeId(operations.get(0).getId());
    log.setData(data);
    this.operationLogService.save(log);
  }

}
