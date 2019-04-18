package jp.sbpayment.bpr.bl.test.service;

import java.util.List;
import jp.sbpayment.bpr.bl.dto.CommonCodesDto;
import jp.sbpayment.bpr.bl.repository.CommonCodesRepository;
import jp.sbpayment.bpr.bl.service.CommonCodeService;
import jp.sbpayment.bpr.bl.test.ApplicationConfigurationTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationConfigurationTest.class)
public class CommonCodeServiceTest {

  @Autowired
  private CommonCodeService commonCodeService;

  @Autowired
  private CommonCodesRepository commonCodesRepository;

  private PodamFactory podam = new PodamFactoryImpl();

  private CommonCodesDto commonCodesDto;

  @Before
  public void setUp() {
    commonCodesRepository.deleteAll();
    commonCodesDto = podam.manufacturePojoWithFullData(CommonCodesDto.class);
  }

  @Test
  public void testFindAll() {
    commonCodeService.save(commonCodesDto);
    Iterable<CommonCodesDto> inserted = commonCodeService.findAll();
    Assert.assertNotNull(inserted);
    Assert.assertEquals(inserted.iterator().next().getCode(),
            commonCodesDto.getCode());
    Assert.assertEquals(inserted.iterator().next().getCodeType(),
            commonCodesDto.getCodeType());
    Assert.assertEquals(inserted.iterator().next().getDescription(),
            commonCodesDto.getDescription());
  }

  @Test
  public void testFindCommonCodesByCode() {
    commonCodeService.save(commonCodesDto);
    CommonCodesDto code = commonCodeService.findCommonCodesByCode(commonCodesDto.getCode()).get(0);
    Assert.assertNotNull(code);
    Assert.assertEquals(commonCodesDto.getCode(), code.getCode());
    Assert.assertEquals(commonCodesDto.getCodeType(), code.getCodeType());
    Assert.assertEquals(commonCodesDto.getDescription(), code.getDescription());

    List<CommonCodesDto> commonCodesDtoNull = commonCodeService.findCommonCodesByCode("code");
    Assert.assertEquals(0, commonCodesDtoNull.size());
  }

}
