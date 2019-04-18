package jp.sbpayment.bpr.bl.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "t_notice_template")
@EqualsAndHashCode(callSuper = false)
public class NoticeTemplate extends BaseEntity {

  @Column(name = "template_name", length = 256, nullable = false, unique = true)
  private String templateName;

  @Column(name = "title", length = 256, nullable = false)
  private String title;

  @Column(name = "notice", nullable = false)
  private String notice;

  @Column(name = "enabled", nullable = false)
  private int enabled = 0;

}
