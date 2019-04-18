package jp.sbpayment.bpr.bl.dto;

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
public class CommonCodesDto extends BaseDto {

  @NotNull
  @Size(max = 20)
  String codeType;

  @NotNull
  @Size(max = 20)
  String code;

  @NotNull
  @Size(max = 256)
  String description = "#";

}
