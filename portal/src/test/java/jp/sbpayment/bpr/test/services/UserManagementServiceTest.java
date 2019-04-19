package jp.sbpayment.bpr.test.services;

import java.util.List;
import jp.sbpayment.bpr.bl.dto.RoleDto;
import jp.sbpayment.bpr.bl.dto.UserDto;
import jp.sbpayment.bpr.bl.service.RoleService;
import jp.sbpayment.bpr.bl.service.UserService;
import jp.sbpayment.bpr.services.UserManagementService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserManagementServiceTest {

  @Autowired
  private UserManagementService userManagementService;

  @Autowired
  private UserService userService;

  @Autowired
  private RoleService roleService;

  @MockBean
  PageRequest pageRequest;

  private UserDto userDto;

  private RoleDto roleDto;

  private PodamFactory podam = new PodamFactoryImpl();

  /**
   * setup.
   */
  @Before
  public void setUp() {
    userDto = podam.manufacturePojoWithFullData(UserDto.class);
    userDto.setEmail("test@mail.com");
    roleDto = podam.manufacturePojoWithFullData(RoleDto.class);
  }

  @Test
  public void testSave() {
    userDto.setId(1L);
    userManagementService.save(userDto);
    UserDto returnedUserDto = userService.findById(userDto.getId());
    Assert.assertNotNull(returnedUserDto);
    Assert.assertEquals(1L, returnedUserDto.getId());
    Assert.assertEquals(userDto.getUsername(), returnedUserDto.getUsername());
  }

  @Test
  public void testLoadRoles() {
    roleDto.setId(1L);
    roleService.save(roleDto);
    roleDto = podam.manufacturePojoWithFullData(RoleDto.class);
    roleDto.setId(2L);
    roleService.save(roleDto);
    List<RoleDto> returnedRoleDtoList = userManagementService.loadRoles();
    Assert.assertNotNull(returnedRoleDtoList);
    Assert.assertEquals(1L, returnedRoleDtoList.get(0).getId());
    Assert.assertEquals(2L, returnedRoleDtoList.get(1).getId());
  }

  @Test
  public void testFindUserByUserName() {
    userDto.setUsername("test");
    userManagementService.save(userDto);
    UserDto returnedUserDto = userManagementService.findUserByUserName(userDto.getUsername());
    Assert.assertNotNull(returnedUserDto);
    Assert.assertEquals(userDto.getUsername(), returnedUserDto.getUsername());
  }

  @Test
  public void testFindPaginated() {
    Mockito.when(pageRequest.getPageSize()).thenReturn(2);
    Mockito.when(pageRequest.getPageNumber()).thenReturn(2);
    Page<UserDto> paginated = userManagementService.findPaginated(pageRequest,
            userDto.getFirstName(), userDto.getEmail());
    Assert.assertEquals(0, paginated.getTotalPages());

    Mockito.when(pageRequest.getPageSize()).thenReturn(1);
    Mockito.when(pageRequest.getPageNumber()).thenReturn(0);
    userManagementService.save(userDto);
    Page<UserDto> paginate = userManagementService.findPaginated(pageRequest,
            userDto.getFirstName(), userDto.getEmail());
    Assert.assertEquals(1, paginate.getTotalPages());
  }

}
