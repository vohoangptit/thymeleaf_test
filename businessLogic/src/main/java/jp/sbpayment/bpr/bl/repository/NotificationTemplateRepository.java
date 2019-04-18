package jp.sbpayment.bpr.bl.repository;

import jp.sbpayment.bpr.bl.model.NoticeTemplate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface NotificationTemplateRepository extends BaseRespository<NoticeTemplate> {

  @Query("select u from NoticeTemplate u where templateName=:templateName")
  NoticeTemplate findByTemplateName(@Param("templateName") String templateName);

}

