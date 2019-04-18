package jp.sbpayment.bpr.bl.repository;

import jp.sbpayment.bpr.bl.model.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BaseRespository<E extends BaseEntity> extends JpaRepository<E, Long> {

}
