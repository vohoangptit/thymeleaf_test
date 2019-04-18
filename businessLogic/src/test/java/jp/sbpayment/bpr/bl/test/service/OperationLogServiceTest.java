package jp.sbpayment.bpr.bl.test.service;

import jp.sbpayment.bpr.bl.dto.OperationLogDto;
import jp.sbpayment.bpr.bl.repository.OperationLogRepository;
import jp.sbpayment.bpr.bl.service.OperationLogService;
import jp.sbpayment.bpr.bl.test.ApplicationConfigurationTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationConfigurationTest.class)
public class OperationLogServiceTest {

  @Autowired
  private OperationLogService operationLogService;

  @Autowired
  private OperationLogRepository operationLogRepository;

  private PodamFactory podam = new PodamFactoryImpl();

  private OperationLogDto operationLogDto;

  @Before
  public void setUp() {
    operationLogRepository.deleteAll();
    operationLogDto = podam.manufacturePojoWithFullData(OperationLogDto.class);
  }

  @Test
  public void testSaveOperationLogSuccess() {
    Assert.assertEquals(0, operationLogRepository.count());
    operationLogService.save(operationLogDto);
    Assert.assertEquals(1, operationLogRepository.count());
  }

  @Test(expected = NumberFormatException.class)
  public void testSaveOperationLogRequiredFunctionId() {
    operationLogDto.setFunctionId(Integer.valueOf(null));
    operationLogService.save(operationLogDto);
  }

  @Test(expected = DataIntegrityViolationException.class)
  public void testSaveOperationLogRequiredOperationUserId() {
    Assert.assertEquals(0, operationLogRepository.count());
    operationLogDto.setOperationUserId(null);
    operationLogService.save(operationLogDto);
    Assert.assertEquals(1, operationLogRepository.count());
  }

  @Test(expected = DataIntegrityViolationException.class)
  public void testSaveOperationLogRequiredOperationDate() {
    Assert.assertEquals(0, operationLogRepository.count());
    operationLogDto.setOperationDate(null);
    operationLogService.save(operationLogDto);
    Assert.assertEquals(1, operationLogRepository.count());
  }

  @Test(expected = NumberFormatException.class)
  public void testSaveOperationLogRequiredOperationCodeId() {
    Assert.assertEquals(0, operationLogRepository.count());
    operationLogDto.setOperationCodeId(Long.valueOf(null));
    operationLogService.save(operationLogDto);
    Assert.assertEquals(1, operationLogRepository.count());
  }

}
