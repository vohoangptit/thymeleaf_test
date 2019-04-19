package jp.sbpayment.bpr.bl.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "t_roles")
@EqualsAndHashCode(callSuper = false)
public class Role extends BaseEntity {
  
  private static final long serialVersionUID = 1L;

  @Column(name = "name", nullable = false, length = 100)
  private String name;

  @ManyToMany(mappedBy = "roles")
  private List<User> users;

}
