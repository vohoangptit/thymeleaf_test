package jp.sbpayment.bpr.bl.test.operationlogs;

import java.util.ArrayList;
import java.util.List;
import jp.sbpayment.bpr.bl.dto.CommonCodesDto;
import jp.sbpayment.bpr.bl.log.ConstLog;
import jp.sbpayment.bpr.bl.log.OperationLogCommon;
import jp.sbpayment.bpr.bl.model.OperationLog;
import jp.sbpayment.bpr.bl.repository.OperationLogRepository;
import jp.sbpayment.bpr.bl.service.CommonCodeService;
import jp.sbpayment.bpr.bl.test.ApplicationConfigurationTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationConfigurationTest.class)
public class OperationLogCommonTest {

  @Autowired
  private OperationLogRepository operationLogRepository;

  @MockBean 
  private CommonCodeService commonCodeService;

  @Autowired
  private OperationLogCommon operationLogCommon;

  private PodamFactory podam = new PodamFactoryImpl();

  private List<CommonCodesDto> commonCodesDtoes;

  /**
   * Setup before running test case.
   */
  @SuppressWarnings("unchecked")
  @Before
  public void setUp() {
    operationLogRepository.deleteAll();
    commonCodesDtoes = podam.manufacturePojoWithFullData(ArrayList.class, CommonCodesDto.class);
    CommonCodesDto commonCodesDto = new CommonCodesDto();
    commonCodesDto.setId(1);
    commonCodesDto.setCode("F001");
    commonCodesDto.setCodeType("FUNCTION_CODE");

    CommonCodesDto commonCodesDto2 = new CommonCodesDto();
    commonCodesDto2.setId(2);
    commonCodesDto2.setCode("OP004");
    commonCodesDto2.setCodeType("OPERATION_CODE");

    commonCodesDtoes.add(commonCodesDto);
    commonCodesDtoes.add(commonCodesDto2);
  }

  @Test
  public void testOperationLogCommon_CommonCodesAreNull() {
    operationLogCommon.logOperation(ConstLog.LOG_FUNC_NOTICE_LIST,
                                    ConstLog.LOG_OPER_SEARCH,
                                    "");
    List<OperationLog> results = operationLogRepository.findAll();
    Assert.assertEquals(0, results.size());
  }

  @Test
  public void testOperationLogCommon_InsertedLogDataToDatabase() {
    
    Mockito.when(commonCodeService.findCommonCodesByCode(ConstLog.LOG_FUNC_NOTICE_LIST))
      .thenReturn(commonCodesDtoes.subList(0, 1));
    
    Mockito.when(commonCodeService.findCommonCodesByCode(ConstLog.LOG_OPER_SEARCH))
      .thenReturn(commonCodesDtoes.subList(1, 2));

    Assert.assertEquals(0, operationLogRepository.count());
    String testData = "TestData";
    operationLogCommon.logOperation(ConstLog.LOG_FUNC_NOTICE_LIST,
                                    ConstLog.LOG_OPER_SEARCH,
                                    testData);

    List<OperationLog> results = operationLogRepository.findAll();
    Assert.assertEquals(1, results.size());
    Assert.assertEquals(commonCodesDtoes.get(0).getId(), results.get(0).getFunctionId());
    Assert.assertEquals(commonCodesDtoes.get(1).getId(), results.get(0).getOperationCodeId());
    Assert.assertEquals(testData, results.get(0).getData());
  }

}
