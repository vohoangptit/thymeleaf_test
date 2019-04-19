package jp.sbpayment.bpr.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import jp.sbpayment.bpr.bl.dto.RoleDto;
import jp.sbpayment.bpr.bl.dto.UserDto;
import jp.sbpayment.bpr.bl.log.ConstLog;
import jp.sbpayment.bpr.bl.model.User;
import jp.sbpayment.bpr.bl.service.RoleService;
import jp.sbpayment.bpr.common.utils.JsonUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserManagementServiceImpl
    extends PortalServiceImpl<UserDto, User, jp.sbpayment.bpr.bl.service.UserServiceImpl>
    implements UserManagementService {

  private final RoleService roleService;

  private final BCryptPasswordEncoder passwordEncoder;

  @Override
  public int save(@Valid UserDto userDto) {

    int flag = 0;
    UserDto oldUserDto = service.findByUsername(userDto.getUsername());
    if (oldUserDto == null) {
      userDto.setRoles(userDto.getRoles());
      userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
      service.save(userDto);
      flag = 1;

    } else {
      oldUserDto.setRoles(userDto.getRoles());
      oldUserDto.setFirstName(userDto.getFirstName());
      oldUserDto.setLastName(userDto.getLastName());
      oldUserDto.setIsActive(userDto.getIsActive());
      service.save(oldUserDto);
    }
    return flag;
  }

  @Override
  public List<RoleDto> loadRoles() {
    return roleService.findAll();
  }

  @Override
  public Page<UserDto> findPaginated(PageRequest pageRequest, String name, String email) {
    
    // Log to Operation Table Log
    Map<String, Object> data = new HashMap<>();
    data.put("name", name);
    data.put("email", email);
    operationLogCommon.logOperation(ConstLog.LOG_FUNC_NOTICE_LIST, ConstLog.LOG_OPER_SEARCH,
        JsonUtils.convert(data));
    return this.service.findPaginated(pageRequest, name, email);
  }
  
  @Override
  public UserDto findUserByUserName(String username) {
    return this.service.findByUsername(username);
  }

}
