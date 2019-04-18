package jp.sbpayment.bpr.bl.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import jp.sbpayment.bpr.bl.dto.BaseDto;
import jp.sbpayment.bpr.bl.mapper.BaseMapper;
import jp.sbpayment.bpr.bl.model.BaseEntity;
import jp.sbpayment.bpr.bl.repository.BaseRespository;
import jp.sbpayment.bpr.common.exceptions.ResourceNotFoundException;

public interface BusinessLogicService<D extends BaseDto, E extends BaseEntity> {

  D save(D dto);
  
  List<D> save(List<D> dtos);

  D findById(long id);

  D delete(long id);

  D delete(D dto);

  List<D> findAll();

  /**
   * Summary: support save, update element array.
   * @param dtos parameter object list
   * @param respository jpa
   * @param mapper dto <=> entity
   * @return new instance data object is filled by newest data
   */
  default List<D> saveInternal(List<D> dtos, BaseRespository<E> respository,
      BaseMapper<D, E> mapper) {
    
    if (dtos == null) {
      throw new ResourceNotFoundException();
    }
    List<E> entities = dtos.stream().map(dto -> mapper.toEntity(dto)).collect(Collectors.toList());
    entities = respository.saveAll(entities);
    return entities.stream().map(entity -> mapper.toDto(entity)).collect(Collectors.toList());
  }
  
  /**
   * Summary: support save, update element.
   * @param dto parameter object
   * @param respository jpa
   * @param mapper dto <=> entity
   * @return new instance data object is filled by newest data
   */
  default D saveInternal(D dto, BaseRespository<E> respository,
      BaseMapper<D, E> mapper) {
    
    if (dto == null) {
      throw new ResourceNotFoundException();
    }
    E entity = mapper.toEntity(dto);
    entity = respository.save(entity);
    return mapper.toDto(entity);
  }

  /**
   * summary: support find element by id.
   * 
   * @param id id of record
   * @param respository jpa
   * @param mapper convert dto <=> entity
   * @return
   */
  default D findByIdInternal(long id, BaseRespository<E> respository, BaseMapper<D, E> mapper) {

    Optional<E> optional = respository.findById(id);
    if (optional.isEmpty()) {
      throw new ResourceNotFoundException();
    }
    return mapper.toDto(optional.get());
  }
  
  /**
   * summary: support delete element by id.
   * 
   * @param id id of record
   * @param respository jpa
   * @param mapper convert dto <=> entity
   * @return
   */
  default D deleteInternal(long id, BaseRespository<E> respository, BaseMapper<D, E> mapper) {
    
    Optional<E> optional = respository.findById(id);
    if (optional.isEmpty()) {
      throw new ResourceNotFoundException();
    }
    /*
     * E entity = optional.get(); 
     * // entity.setDelete(1); entity = respository.save(entity); return mapper.toDto(entity);
     */
    // for override
    return null;
  }
  
  /**
   * Summary: support find all element on database.
   * @param respository jpa
   * @param mapper convert dto <=> entity
   * @return
   */
  default List<D> findAllInternal(BaseRespository<E> respository, BaseMapper<D, E> mapper) {
    
    List<E> list = respository.findAll();
    if (list == null || list.isEmpty()) {
      return new ArrayList<D>(0);
    }
    return list.stream().map(entity -> mapper.toDto(entity)).collect(Collectors.toList());
    
  }

}
