package jp.sbpayment.bpr.bl.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import jp.sbpayment.bpr.bl.dto.UserDto;
import jp.sbpayment.bpr.bl.mapper.BaseMapper;
import jp.sbpayment.bpr.bl.mapper.UserMapper;
import jp.sbpayment.bpr.bl.model.User;
import jp.sbpayment.bpr.bl.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Service for User Management.
 */
@Service
@AllArgsConstructor
public class UserServiceImpl extends 
    BusinessLogicServiceImpl<UserRepository, UserDto, User> implements 
    UserService {

  private final UserRepository userRepository;

  @Override
  public UserDto findByUsername(String username) {
    return getMapper().toDto(userRepository.findByUsername(username));
  }

  @Override
  public List<UserDto> search(String name, String email) {
    List<User> users = userRepository.findByNameAndEmail(name, email);
    return users.stream().map(entity -> getMapper().toDto(entity)).collect(Collectors.toList());
  }

  @Override
  public Page<UserDto> findPaginated(Pageable pageable, String name, String email) {
    int pageSize = pageable.getPageSize();
    int currentPage = pageable.getPageNumber();
    int startItem = currentPage * pageSize;

    List<UserDto> list;

    List<User> users = userRepository.findByNameAndEmail(name, email);

    if (users.size() < startItem) {
      list = Collections.emptyList();
    } else {
      int toIndex = Math.min(startItem + pageSize, users.size());
      list = users.subList(startItem, toIndex).stream()
              .map(entity -> getMapper().toDto(entity)).collect(Collectors.toList());
    }

    return new PageImpl<>(list, PageRequest.of(currentPage, pageSize), users.size());
  }

  @Override
  protected UserRepository getRepository() {
    return this.userRepository;
  }

  @Override
  protected BaseMapper<UserDto, User> getMapper() {
    return UserMapper.INSTANCE;
  }

}