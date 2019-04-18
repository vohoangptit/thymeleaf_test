package jp.sbpayment.bpr.bl.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import jp.sbpayment.bpr.bl.dto.CommonCodesDto;
import jp.sbpayment.bpr.bl.mapper.BaseMapper;
import jp.sbpayment.bpr.bl.mapper.CommonCodesMapper;
import jp.sbpayment.bpr.bl.model.CommonCodes;
import jp.sbpayment.bpr.bl.repository.CommonCodesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CommonCodeServiceImpl extends 
    BusinessLogicServiceImpl<CommonCodesRepository, CommonCodesDto, CommonCodes> 
    implements CommonCodeService {

  private final CommonCodesRepository commonCodesRepository;

  @Override
  public List<CommonCodesDto> findCommonCodesByCode(String code) {
    List<CommonCodes> list = commonCodesRepository.findByCode(code);
    if (list == null || list.isEmpty()) {
      return Collections.emptyList();
    }
    return list.stream().map(entity -> getMapper().toDto(entity))
        .collect(Collectors.toList());
  }

  public CommonCodesRepository getRepository() {
    
    return commonCodesRepository;
  }

  @Override
  protected BaseMapper<CommonCodesDto, CommonCodes> getMapper() {
    
    return CommonCodesMapper.INSTANCE;
  }

}
