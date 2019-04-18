package jp.sbpayment.bpr.bl.dto;

import javax.persistence.Column;
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
public class NoticeTemplateDto extends BaseDto {

  @NotNull
  @Size(max = 256)
  @Column(unique = true)
  String templateName;

  @NotNull
  @Size(max = 256)
  String title;

  @NotNull
  String notice;

  @NotNull
  int enabled = 0;

}
