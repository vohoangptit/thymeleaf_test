package jp.sbpayment.bpr.bl.dto;

import java.util.List;
import javax.persistence.Column;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserDto extends BaseDto {

  @NotNull
  @Size(max = 256)
  String firstName;

  @NotNull
  @Size(max = 256)
  String lastName;

  @NotNull
  @Email
  String email;

  @NotNull
  @Column(unique = true)
  String username;

  @NotNull
  String password;

  @NotNull
  int isActive;

  @NotNull
  List<@Valid RoleDto> roles;

}
