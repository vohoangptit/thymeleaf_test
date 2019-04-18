package jp.sbpayment.bpr.common.test.utils;

import java.util.ArrayList;
import java.util.Iterator;
import jp.sbpayment.bpr.common.test.ApplicationConfigurationTest;
import jp.sbpayment.bpr.common.utils.CollectionUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationConfigurationTest.class)
public class CollectionUtilsTest {

  @Test
  public void testAddIteratorToCollection() {
    ArrayList<String> collectionsIterator = new ArrayList<>();
    Iterator<String> iterator = collectionsIterator.iterator();

    ArrayList<String> newCollections = new ArrayList<>();
    CollectionUtils.addAll(newCollections, iterator);
    Assert.assertEquals(0, newCollections.size());

    collectionsIterator.add("C");
    collectionsIterator.add("A");
    collectionsIterator.add("E");
    iterator = collectionsIterator.iterator();
    CollectionUtils.addAll(newCollections, iterator);
    Assert.assertEquals(3, newCollections.size());
  }

}
