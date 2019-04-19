package jp.sbpayment.bpr.bl.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "t_user")
@EqualsAndHashCode(callSuper = false)
public class User extends BaseEntity {
  
  private static final long serialVersionUID = 1L;

  @Column(name = "first_name", nullable = false, length = 256)
  private String firstName;

  @Column(name = "last_name", nullable = false, length = 256)
  private String lastName;

  @Column(name = "email", nullable = false)
  @Email
  private String email;

  @Column(name = "username", unique = true, nullable = false)
  private String username;

  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "isActive", nullable = false)
  private int isActive;

  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
  @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
          inverseJoinColumns = @JoinColumn(name = "role_id"))
  private List<Role> roles;

}
