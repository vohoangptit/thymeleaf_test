package jp.sbpayment.bpr.api;

import com.fasterxml.jackson.annotation.JsonView;
import jp.sbpayment.bpr.bl.service.NotificationService;
import jp.sbpayment.bpr.bl.view.NoticeView;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class RestApiController {

  private final NotificationService notificationService;

  @JsonView(NoticeView.NoticeSearch.class)
  @GetMapping("/notice/{notice_id}")
  public ResponseEntity<?> searchNotice(@PathVariable("notice_id") long noticeId) {
    return new ResponseEntity<>(notificationService.findById(noticeId), HttpStatus.OK);
  }

}
