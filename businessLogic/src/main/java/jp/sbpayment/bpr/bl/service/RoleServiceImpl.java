package jp.sbpayment.bpr.bl.service;

import jp.sbpayment.bpr.bl.dto.RoleDto;
import jp.sbpayment.bpr.bl.mapper.BaseMapper;
import jp.sbpayment.bpr.bl.mapper.RoleMapper;
import jp.sbpayment.bpr.bl.model.Role;
import jp.sbpayment.bpr.bl.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service for Role Management.
 */
@Service
@AllArgsConstructor
public class RoleServiceImpl extends 
    BusinessLogicServiceImpl<RoleRepository, RoleDto, Role> 
    implements RoleService {

  private final RoleRepository roleRepository;

  @Override
  protected RoleRepository getRepository() {
    return this.roleRepository;
  }

  @Override
  protected BaseMapper<RoleDto, Role> getMapper() {
    return RoleMapper.INSTANCE;
  }

}