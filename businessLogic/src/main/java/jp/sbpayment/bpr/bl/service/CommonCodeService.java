package jp.sbpayment.bpr.bl.service;

import java.util.List;
import jp.sbpayment.bpr.bl.dto.CommonCodesDto;
import jp.sbpayment.bpr.bl.model.CommonCodes;

public interface CommonCodeService extends BusinessLogicService<CommonCodesDto, CommonCodes> {

  List<CommonCodesDto> findCommonCodesByCode(String code);

}
