import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import org.junit.Test;

public class KMeansTest {

  @Test
  public void initial_k_1() {
    Pick p1 = new Pick(new Location(0, 0), 0.1);
    Pick p2 = new Pick(new Location(0.1, 0.1), 0.1);
    Pick p3 = new Pick(new Location(0.5, 0.5), 0.1);
    Pick p4 = new Pick(new Location(1, 0), 0.1);
    List<Pick> plist = new ArrayList<>(Arrays.asList(p1, p2, p3, p4));

    List<Cluster> clusters = KMeans.cluster(plist);
    assertEquals(1, clusters.size());
  }

  @Test
  public void initial_k_2() {
    Pick p1 = new Pick(new Location(0, 0), 0.9);
    Pick p2 = new Pick(new Location(0.1, 0.1), 0.4);
    Pick p3 = new Pick(new Location(0.5, 0.5), 0.5);
    Pick p4 = new Pick(new Location(1, 0), 0.1);
    List<Pick> plist = new ArrayList<>(Arrays.asList(p1, p2, p3, p4));

    List<Cluster> clusters = KMeans.cluster(plist);
    assertEquals(2, clusters.size());
  }

  @Test
  public void initial_k_3() {
    Pick p1 = new Pick(new Location(0, 0), 0.9);
    Pick p2 = new Pick(new Location(0.1, 0.1), 0.4);
    Pick p3 = new Pick(new Location(0.5, 0.5), 0.5);
    Pick p4 = new Pick(new Location(1, 0), 0.2);
    List<Pick> plist = new ArrayList<>(Arrays.asList(p1, p2, p3, p4));

    List<Cluster> clusters = KMeans.cluster(plist);
    assertEquals(3, clusters.size());
  }

  @Test
  public void clustering_2() {
    Pick p1 = new Pick(new Location(0, 0), 0.41);
    Pick p2 = new Pick(new Location(0.1, 0.1), 0.42);
    Pick p3 = new Pick(new Location(0.65, 0.65), 0.43);
    Pick p4 = new Pick(new Location(1, 1), 0.44);
    List<Pick> plist = new ArrayList<>(Arrays.asList(p1, p2, p3, p4));

    List<Cluster> clusters = KMeans.cluster(plist);
    assertEquals(2, clusters.size());

    List<List<Pick>> actual = new ArrayList<>();
    clusters.forEach(cluster -> actual.add(cluster.getPicks()));
    actual.forEach(picks -> picks.sort(Comparator.comparing(Pick::getConstraint)));
    actual.sort(Comparator.comparing(x -> x.get(0).getConstraint()));

    List<List<Pick>> expected = new ArrayList<>();
    expected.add(Arrays.asList(p1, p2));
    expected.add(Arrays.asList(p3, p4));

    assertEquals(expected, actual);
  }

}