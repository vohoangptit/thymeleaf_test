package jp.sbpayment.bpr.bl.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "t_notice_mail")
@EqualsAndHashCode(callSuper = false)
public class NoticeMail extends BaseEntity {
  
  private static final long serialVersionUID = 1L;

  @Column(name = "notice_id", length = 256, nullable = false)
  private String noticeId;

  @Column(name = "status", nullable = false)
  private int status = 0;

}
