package jp.sbpayment.bpr.common.utils;

import java.util.Collection;
import java.util.Iterator;

public class CollectionUtils {

  private CollectionUtils() {
  }

  /** Add iterator to Collection.
   * @param collection List, ArrayList,...
   * @param iterator iterator data.
   * @param <T> return value.
   */
  public static <T> void addAll(Collection<T> collection, Iterator<T> iterator) {
    while (iterator.hasNext()) {
      collection.add(iterator.next());
    }
  }

}
