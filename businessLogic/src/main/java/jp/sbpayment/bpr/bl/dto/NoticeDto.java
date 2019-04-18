package jp.sbpayment.bpr.bl.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import java.util.Date;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import jp.sbpayment.bpr.bl.view.NoticeView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class NoticeDto extends BaseDto {

  @JsonView(NoticeView.NoticeSearch.class)
  @Override
  public long getId() {
    return super.getId();
  }
  
  @NotNull
  @Size(max = 256)
  @JsonView(NoticeView.NoticeSearch.class)
  String title;

  long classId;

  long settlementWayId;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @JsonView(NoticeView.NoticeSearch.class)
  @JsonProperty("announce_date")
  Date announceDate;

  @NotNull
  @JsonView(NoticeView.NoticeSearch.class)
  @JsonProperty("display_day")
  int displayDay = 0;

  @NotNull
  @JsonView(NoticeView.NoticeSearch.class)
  String notice;

  @NotNull
  int displayTarget;

}
