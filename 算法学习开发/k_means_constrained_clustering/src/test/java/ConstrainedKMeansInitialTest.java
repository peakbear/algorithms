import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

public class ConstrainedKMeansInitialTest {
  @Test
  public void initial_k_1() {
    Pick p1 = new Pick(new Location(0, 0), 0.1);
    Pick p2 = new Pick(new Location(0.1, 0.1), 0.1);
    Pick p3 = new Pick(new Location(0.5, 0.5), 0.1);
    Pick p4 = new Pick(new Location(1, 0), 0.1);
    List<Pick> plist = new ArrayList<>(Arrays.asList(p1, p2, p3, p4));

    List<Cluster> clusters = ConstrainedKMeans.cluster(plist);
    assertEquals(1, clusters.size());
  }

  @Test
  public void initial_k_2() {
    Pick p1 = new Pick(new Location(0, 0), 0.9);
    Pick p2 = new Pick(new Location(0.1, 0.1), 0.4);
    Pick p3 = new Pick(new Location(0.5, 0.5), 0.5);
    Pick p4 = new Pick(new Location(1, 0), 0.1);
    List<Pick> plist = new ArrayList<>(Arrays.asList(p1, p2, p3, p4));

    List<Cluster> clusters = ConstrainedKMeans.cluster(plist);
    assertEquals(2, clusters.size());
  }

  @Test
  public void initial_k_3() {
    Pick p1 = new Pick(new Location(0, 0), 0.9);
    Pick p2 = new Pick(new Location(0.1, 0.1), 0.4);
    Pick p3 = new Pick(new Location(0.5, 0.5), 0.5);
    Pick p4 = new Pick(new Location(1, 0), 0.2);
    List<Pick> plist = new ArrayList<>(Arrays.asList(p1, p2, p3, p4));

    List<Cluster> clusters = ConstrainedKMeans.cluster(plist);
    assertEquals(3, clusters.size());
  }
}
