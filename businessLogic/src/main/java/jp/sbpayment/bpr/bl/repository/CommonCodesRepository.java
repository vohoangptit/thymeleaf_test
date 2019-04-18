package jp.sbpayment.bpr.bl.repository;

import java.util.List;
import jp.sbpayment.bpr.bl.model.CommonCodes;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommonCodesRepository extends BaseRespository<CommonCodes> {

  @Query("select u from CommonCodes u where u.code LIKE %:code% ")
  List<CommonCodes> findByCode(@Param("code") String code);

}
