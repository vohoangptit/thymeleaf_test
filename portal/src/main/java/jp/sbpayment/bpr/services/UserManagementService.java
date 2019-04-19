package jp.sbpayment.bpr.services;

import java.util.List;
import jp.sbpayment.bpr.bl.dto.RoleDto;
import jp.sbpayment.bpr.bl.dto.UserDto;
import jp.sbpayment.bpr.bl.model.User;
import jp.sbpayment.bpr.bl.service.UserServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface UserManagementService extends PortalService<UserDto, User, UserServiceImpl> {

  int save(UserDto userDto);

  List<RoleDto> loadRoles();

  UserDto findUserByUserName(String username);

  Page<UserDto> findPaginated(PageRequest pageRequest, String name, String email);

}
