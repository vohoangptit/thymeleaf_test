package jp.sbpayment.bpr.common.response;

public class CustomResponse {

  String message;

  public CustomResponse() {
  }

  public CustomResponse(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

}
