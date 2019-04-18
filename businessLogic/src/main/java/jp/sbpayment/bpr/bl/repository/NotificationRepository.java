package jp.sbpayment.bpr.bl.repository;

import java.util.List;
import java.util.Optional;
import jp.sbpayment.bpr.bl.model.Notice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface NotificationRepository extends BaseRespository<Notice> {

  @Query("select u from Notice u where u.title LIKE %:title% and u.notice like %:notice%"
          + " and u.displayDay >= 0")
  List<Notice> findByTitleAndNotice(@Param("title") String title,
                                    @Param("notice") String notice);

  @Query("select u from Notice u where u.id = (select max(id) from Notice)")
  Notice findNewestNoticeInserted();

  @Query("select u from Notice u where u.id = :id and u.displayDay >= 0")
  Optional<Notice> searchNoticeById(@Param("id") long id);

}

