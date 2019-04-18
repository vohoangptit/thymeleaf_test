package jp.sbpayment.bpr.bl.test.service;

import java.util.ArrayList;
import java.util.List;
import jp.sbpayment.bpr.bl.dto.RoleDto;
import jp.sbpayment.bpr.bl.dto.UserDto;
import jp.sbpayment.bpr.bl.repository.UserRepository;
import jp.sbpayment.bpr.bl.service.UserService;
import jp.sbpayment.bpr.bl.test.ApplicationConfigurationTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationConfigurationTest.class)
public class UserServiceTest {

  @Autowired
  private UserService userService;

  @Autowired
  private UserRepository userRepository;

  private PodamFactory podam = new PodamFactoryImpl();

  private UserDto userDto;

  private RoleDto roleDto;

  @MockBean
  private Pageable pageable;

  /**
   * setting up.
   */
  @Before
  public void setup() {
    userRepository.deleteAll();
    userDto = podam.manufacturePojoWithFullData(UserDto.class);
    userDto.setEmail("anh@gmail");
    roleDto = podam.manufacturePojoWithFullData(RoleDto.class);
  }

  @Test
  public void testSaveUserSuccess() {
    Assert.assertEquals(0, userService.findAll().spliterator().getExactSizeIfKnown());
    userService.save(userDto);
    Assert.assertEquals(1, userService.findAll().spliterator().getExactSizeIfKnown());
  }

  @Test(expected = DataIntegrityViolationException.class)
  public void testSaveUserRequiredFirstName() {
    userDto.setFirstName(null);
    userService.save(userDto);
  }

  @Test(expected = DataIntegrityViolationException.class)
  public void testSaveUserRequiredLastName() {
    userDto.setLastName(null);
    userService.save(userDto);
  }

  @Test(expected = DataIntegrityViolationException.class)
  public void testSaveUserRequiredUserName() {
    userDto.setUsername(null);
    userService.save(userDto);
  }

  @Test(expected = DataIntegrityViolationException.class)
  public void testSaveUserUniqueUserName() {
    userDto.setId(0);
    userService.save(userDto);
    userService.save(userDto);
  }

  @Test(expected = DataIntegrityViolationException.class)
  public void testSaveUserRequiredPassWord() {
    userDto.setPassword(null);
    userService.save(userDto);
  }

  @Test(expected = DataIntegrityViolationException.class)
  public void testSaveUserRequiredEmail() {
    userDto.setEmail(null);
    userService.save(userDto);
  }

  @Test(expected = NumberFormatException.class)
  public void testSaveUserRequiredIsActive() {
    userDto.setIsActive(Integer.valueOf(null));
    userService.save(userDto);
  }

  @Test(expected = DataIntegrityViolationException.class)
  public void testSaveUserRequiredRoleName() {
    roleDto.setName(null);
    List<RoleDto> list = new ArrayList<>();
    list.add(roleDto);
    userDto.setRoles(list);
    userService.save(userDto);
  }

  @Test
  public void testSearchUser() {
    userDto.setFirstName("name");
    userService.save(userDto);
    List<UserDto> list = userService.search(
            userDto.getFirstName(), userDto.getEmail());
    Assert.assertNotNull(list);
    Assert.assertEquals("name", list.get(0).getFirstName());
    Assert.assertEquals("anh@gmail", list.get(0).getEmail());
  }

  @Test
  public void testFindPaginated() {
    Mockito.when(pageable.getPageSize()).thenReturn(2);
    Mockito.when(pageable.getPageNumber()).thenReturn(2);
    Page<UserDto> paginated = userService.findPaginated(pageable,
            userDto.getFirstName(), userDto.getEmail());
    Assert.assertEquals(0, paginated.getTotalPages());

    Mockito.when(pageable.getPageSize()).thenReturn(1);
    Mockito.when(pageable.getPageNumber()).thenReturn(0);
    userService.save(userDto);
    Page<UserDto> paginate = userService.findPaginated(pageable,
            userDto.getFirstName(), userDto.getEmail());
    Assert.assertEquals(1, paginate.getTotalPages());
  }

  @Test
  public void testFindAll() {
    userService.save(userDto);
    Iterable<UserDto> iterable = userService.findAll();
    Assert.assertEquals(1, iterable.spliterator().getExactSizeIfKnown());
  }

  @Test
  public void testFindByUsername() {
    userService.save(userDto);
    UserDto user = userService.findByUsername(userDto.getUsername());
    Assert.assertEquals(userDto.getUsername(), user.getUsername());
  }

}