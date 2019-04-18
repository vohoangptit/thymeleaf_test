package jp.sbpayment.bpr.bl.service;

import java.util.List;

import jp.sbpayment.bpr.bl.dto.UserDto;
import jp.sbpayment.bpr.bl.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService extends BusinessLogicService<UserDto, User> {

  UserDto findByUsername(String username);

  List<UserDto> search(String name, String email);

  Page<UserDto> findPaginated(Pageable pageable, String name, String email);

}
