package jp.sbpayment.bpr.bl.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "t_notice")
@EqualsAndHashCode(callSuper = false)
public class Notice extends BaseEntity {

  private static final long serialVersionUID = 1L;
  
  @Column(name = "title", length = 256, nullable = false)
  private String title;

  @Column(name = "class_id")
  private long classId;

  @Column(name = "settlement_way_id")
  private long settlementWayId;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @Column(name = "announce_date", nullable = false)
  private Date announceDate;

  @Column(name = "display_day", nullable = false)
  private int displayDay = 0;

  @Column(name = "notice", nullable = false)
  private String notice;

  @Column(name = "display_target", nullable = false)
  private int displayTarget;

}
