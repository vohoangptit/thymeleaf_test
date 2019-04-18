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
public class NoticeMailDto extends BaseDto {

  @NotNull
  @Size(max = 256)
  String noticeId;

  @NotNull
  int status = 0;

}
