package jp.sbpayment.bpr.bl.service;

import java.util.HashSet;
import java.util.Set;
import jp.sbpayment.bpr.bl.dto.RoleDto;
import jp.sbpayment.bpr.bl.dto.UserDto;
import jp.sbpayment.bpr.bl.mapper.BaseMapper;
import jp.sbpayment.bpr.bl.mapper.UserMapper;
import jp.sbpayment.bpr.bl.model.User;
import jp.sbpayment.bpr.bl.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl extends 
    BusinessLogicServiceImpl<UserRepository, UserDto, User>
    implements UserDetailsService {

  private final UserRepository userRepository;
  
  @Override
  protected UserRepository getRepository() {
    return this.userRepository;
  }
  
  @Override
  protected BaseMapper<UserDto, User> getMapper() {
    return UserMapper.INSTANCE;
  }

  @Override
  @Transactional(readOnly = true)
  public UserDetails loadUserByUsername(String username) {
    UserDto userDto = getMapper().toDto(getRepository().findByUsername(username));

    if (userDto == null) {
      throw new UsernameNotFoundException("Username " + username + " not found.");
    }

    if (userDto.getIsActive() != 1) {
      throw new UsernameNotFoundException("Username " + username + " not active.");
    }

    Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
    for (RoleDto roleDto : userDto.getRoles()) {
      grantedAuthorities.add(new SimpleGrantedAuthority(roleDto.getName()));
    }

    return new org.springframework.security.core.userdetails.User(userDto.getUsername(),
            userDto.getPassword(), grantedAuthorities);
  }

}
