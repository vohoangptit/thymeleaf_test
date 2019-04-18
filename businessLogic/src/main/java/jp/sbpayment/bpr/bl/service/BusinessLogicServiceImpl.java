package jp.sbpayment.bpr.bl.service;

import java.util.List;
import jp.sbpayment.bpr.bl.dto.BaseDto;
import jp.sbpayment.bpr.bl.mapper.BaseMapper;
import jp.sbpayment.bpr.bl.model.BaseEntity;
import jp.sbpayment.bpr.bl.repository.BaseRespository;
import jp.sbpayment.bpr.common.exceptions.ResourceNotFoundException;

public abstract class 
    BusinessLogicServiceImpl<R extends BaseRespository<E>, D extends BaseDto, E extends BaseEntity> 
    implements BusinessLogicService<D, E> {

  protected abstract R getRepository();

  protected abstract BaseMapper<D, E> getMapper();

  @Override
  public D save(D dto) {
    return saveInternal(dto, getRepository(), getMapper());
  }
  
  @Override
  public List<D> save(List<D> dtos) {
    return saveInternal(dtos, getRepository(), getMapper());
  }

  @Override
  public D findById(long id) {
    return findByIdInternal(id, getRepository(), getMapper());
  }

  @Override
  public D delete(long id) {
    return deleteInternal(id, getRepository(), getMapper());
  }

  @Override
  public D delete(D dto) {
    if (dto == null) {
      throw new ResourceNotFoundException();
    }
    return delete(dto.getId());
  }

  @Override
  public List<D> findAll() {
    return findAllInternal(getRepository(), getMapper());
  }

}
