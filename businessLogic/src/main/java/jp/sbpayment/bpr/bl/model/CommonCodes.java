package jp.sbpayment.bpr.bl.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "m_common_codes",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"code_type", "code"})})
@EqualsAndHashCode(callSuper = false)
public class CommonCodes extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @Column(name = "code_type", length = 20, nullable = false)
  private String codeType;

  @Column(name = "code", length = 20, nullable = false)
  private String code;

  @Column(name = "description", length = 256, nullable = false)
  private String description = "#";

}
