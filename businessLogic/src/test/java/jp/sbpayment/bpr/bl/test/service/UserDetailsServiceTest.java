package jp.sbpayment.bpr.bl.test.service;

import jp.sbpayment.bpr.bl.dto.UserDto;
import jp.sbpayment.bpr.bl.repository.UserRepository;
import jp.sbpayment.bpr.bl.service.UserService;
import jp.sbpayment.bpr.bl.test.ApplicationConfigurationTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationConfigurationTest.class)
public class UserDetailsServiceTest {

  @Autowired
  private UserDetailsService userDetailsService;

  private UserDto userDto;

  @Autowired
  private UserService userService;

  @Autowired
  private UserRepository userRepository;

  /**
   * setting up.
   */
  @Before
  public void setUp() {
    PodamFactory podam = new PodamFactoryImpl();
    userRepository.deleteAll();
    userDto = podam.manufacturePojoWithFullData(UserDto.class);
    userDto.setEmail("admin@123");
  }

  @Test
  public void testLoadUserByUsernameNotFound() {
    try {
      /* UserDetails userDetails = */userDetailsService.loadUserByUsername(userDto.getUsername());
      Assert.fail("Exception not thrown");
    } catch (UsernameNotFoundException e) {
      Assert.assertEquals("Username " + userDto.getUsername() + " not found.", e.getMessage());
    }
  }

  @Test
  public void testLoadUserByUsernameNotActive() {
    try {
      userService.save(userDto);
      /* UserDetails userDetails = */userDetailsService.loadUserByUsername(userDto.getUsername());
      Assert.fail("Exception not thrown");
    } catch (UsernameNotFoundException e) {
      Assert.assertEquals("Username " + userDto.getUsername() + " not active.", e.getMessage());
    }
  }

  @Test
  public void testLoadUserByUsernameSuccess() {
    userDto.setIsActive(1);
    userService.save(userDto);
    UserDetails userDetails = userDetailsService.loadUserByUsername(userDto.getUsername());
    Assert.assertNotNull(userDetails);
    Assert.assertEquals(userDto.getUsername(), userDetails.getUsername());
    Assert.assertEquals(userDto.getPassword(), userDetails.getPassword());
    Assert.assertEquals(userDto.getRoles().size(), userDetails.getAuthorities().size());
  }

}