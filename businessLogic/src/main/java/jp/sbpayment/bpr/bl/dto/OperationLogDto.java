package jp.sbpayment.bpr.bl.dto;

import java.util.Date;
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
public class OperationLogDto extends BaseDto {

  @Size(max = 20)
  String mid;

  @NotNull
  long functionId;

  @NotNull
  @Size(max = 64)
  String operationUserId = "#";

  @NotNull
  Date operationDate;

  @NotNull
  long operationCodeId;

  String data;

}
