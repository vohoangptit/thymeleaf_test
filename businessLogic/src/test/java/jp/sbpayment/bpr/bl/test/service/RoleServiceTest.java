package jp.sbpayment.bpr.bl.test.service;

import java.util.List;
import jp.sbpayment.bpr.bl.dto.RoleDto;
import jp.sbpayment.bpr.bl.repository.RoleRepository;
import jp.sbpayment.bpr.bl.service.RoleService;
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
public class RoleServiceTest {

  @Autowired
  private RoleService roleService;

  private RoleDto roleDto;
  @Autowired
  private RoleRepository roleRepository;

  /**
   * setting up.
   */
  @Before
  public void setUp() {
    PodamFactory podam = new PodamFactoryImpl();
    roleRepository.deleteAll();
    roleDto = podam.manufacturePojoWithFullData(RoleDto.class);
  }

  @Test
  public void testSaveRole() {
    roleService.save(roleDto);
    Assert.assertEquals(1, roleService.findAll().size());
  }

  @Test
  public void testFindAll() {
    List<RoleDto> roles = roleService.findAll();
    Assert.assertEquals(0, roles.size());
  }

}