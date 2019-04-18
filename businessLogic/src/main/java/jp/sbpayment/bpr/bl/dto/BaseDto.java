package jp.sbpayment.bpr.bl.dto;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseDto {
  
  @NotNull
  long id;
  
  // @NotNull
  // private int delete;

}
