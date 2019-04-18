package jp.sbpayment.bpr.common.utils;

import java.util.ArrayList;

public class SbpsUtils {

  /**
   * calcTheSequencePaginationNumbersRendering.
   * @param currentPage a currently page number selecting
   * @param totalPages  a total of pages according total elements after divided for number
   *                   of maximum items displayed on one page.
   * @param pageSize    maximum items displayed on one page
   * @return The the sequence page numbers for rendering after calculated
   */
  public static ArrayList<Integer> calcTheSequencePaginationNumbersRendering(
          int currentPage, int totalPages, int pageSize) {
    int fromNumber = 2; // A first number of pages begin from 2
    int toNumber = Math.min(totalPages, pageSize);
    if (currentPage >= pageSize) {
      if ((currentPage == totalPages) || (currentPage >= totalPages - 2)) {
        // 4 is number of pages that remain with current page number
        fromNumber = totalPages - 4;
        toNumber = totalPages;
      } else {
        fromNumber = currentPage - 1;
        toNumber = Math.min(totalPages, currentPage + 1);
      }
    }

    ArrayList<Integer> sequences = new ArrayList<>();
    sequences.add(fromNumber);
    sequences.add(toNumber);
    return sequences;
  }

}
