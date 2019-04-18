package jp.sbpayment.bpr.bl.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "t_operation_log")
@EqualsAndHashCode(callSuper = true)
public class OperationLog extends BaseEntity {

  @Column(name = "mid", length = 20)
  private String mid;

  @Column(name = "function_id", nullable = false)
  private long functionId;

  @Column(name = "operation_user_id", length = 64, nullable = false)
  private String operationUserId = "#";

  @Column(name = "operation_date", nullable = false)
  private Date operationDate;

  @Column(name = "operation_code_id", nullable = false)
  private long operationCodeId;

  @Column(name = "data")
  private String data;

}
