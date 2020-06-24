import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import org.junit.Test;

public class ConstrainedKMeansClusteringTest {

  @Test
  public void clustering_1() {
    Pick p1 = new Pick(new Location(0, 0), 0.21);
    Pick p2 = new Pick(new Location(0.1, 0.1), 0.22);
    Pick p3 = new Pick(new Location(0.5, 0.5), 0.23);
    Pick p4 = new Pick(new Location(1, 0), 0.24);
    List<Pick> plist = new ArrayList<>(Arrays.asList(p1, p2, p3, p4));

    List<Cluster> clusters = ConstrainedKMeans.cluster(plist);
    assertEquals(1, clusters.size());

    List<List<Pick>> actual = new ArrayList<>();
    clusters.forEach(cluster -> actual.add(cluster.getPicks()));
    actual.forEach(picks -> picks.sort(Comparator.comparing(Pick::getConstraint)));
    actual.sort(Comparator.comparing(x -> x.get(0).getConstraint()));

    List<List<Pick>> expected = new ArrayList<>();
    expected.add(Arrays.asList(p1, p2, p3, p4));

    assertEquals(expected, actual);
  }

  @Test
  public void clustering_2() {
    Pick p1 = new Pick(new Location(0, 0), 0.41);
    Pick p2 = new Pick(new Location(0.1, 0.1), 0.42);
    Pick p3 = new Pick(new Location(0.5, 0.5), 0.43);
    Pick p4 = new Pick(new Location(1, 0), 0.44);
    List<Pick> plist = new ArrayList<>(Arrays.asList(p1, p2, p3, p4));

    List<Cluster> clusters = ConstrainedKMeans.cluster(plist);
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

  @Test
  public void clustering_3() {
    Pick p1 = new Pick(new Location(0, 0), 0.31);
    Pick p2 = new Pick(new Location(0.1, 0.1), 0.32);
    Pick p3 = new Pick(new Location(0.2, 0.2), 0.33);
    Pick p4 = new Pick(new Location(0.6, 0.6), 0.44);
    Pick p5 = new Pick(new Location(0.7, 0.7), 0.45);
    Pick p6 = new Pick(new Location(1.2, 1.2), 0.26);
    Pick p7 = new Pick(new Location(1.3, 1.3), 0.27);
    Pick p8 = new Pick(new Location(1.4, 1.4), 0.28);

    List<Pick> plist = new ArrayList<>(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8));

    List<Cluster> clusters = ConstrainedKMeans.cluster(plist);
    assertEquals(3, clusters.size());

    List<List<Pick>> actual = new ArrayList<>();
    clusters.forEach(cluster -> actual.add(cluster.getPicks()));
    actual.forEach(picks -> picks.sort(Comparator.comparing(Pick::getConstraint)));
    actual.sort(Comparator.comparing(x -> x.get(0).getConstraint()));

    List<List<Pick>> expected = new ArrayList<>();
    expected.add(Arrays.asList(p6, p7, p8));
    expected.add(Arrays.asList(p1, p2, p3));
    expected.add(Arrays.asList(p4, p5));

    assertEquals(expected, actual);
  }

  @Test
  public void clustering_4() {
    Pick p1 = new Pick(new Location(0, 0), 0.31);
    Pick p2 = new Pick(new Location(0.1, 0.1), 0.32);
    Pick p3 = new Pick(new Location(0.2, 0.2), 0.33);
    Pick p4 = new Pick(new Location(0.6, 0.6), 0.54);
    Pick p5 = new Pick(new Location(0.7, 0.7), 0.55);
    Pick p6 = new Pick(new Location(1.2, 1.2), 0.26);
    Pick p7 = new Pick(new Location(1.3, 1.3), 0.27);
    Pick p8 = new Pick(new Location(1.4, 1.4), 0.28);

    List<Pick> plist = new ArrayList<>(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8));

    List<Cluster> clusters = ConstrainedKMeans.cluster(plist);
    assertEquals(4, clusters.size());

    List<List<Pick>> actual = new ArrayList<>();
    clusters.forEach(cluster -> actual.add(cluster.getPicks()));
    actual.forEach(picks -> picks.sort(Comparator.comparing(Pick::getConstraint)));
    actual.sort(Comparator.comparing(x -> x.get(0).getConstraint()));

    List<List<Pick>> expected = new ArrayList<>();
    expected.add(Arrays.asList(p6, p7, p8));
    expected.add(Arrays.asList(p1, p2, p3));
    expected.add(Arrays.asList(p4));
    expected.add(Arrays.asList(p5));

    assertEquals(expected, actual);
  }

  @Test
  public void clustering_move_3() {
    Pick p1 = new Pick(new Location(0, 0), 0.31);
    Pick p2 = new Pick(new Location(0.1, 0.1), 0.32);
    Pick p3 = new Pick(new Location(0.2, 0.2), 0.33);
    Pick p4 = new Pick(new Location(0.6, 0.6), 0.54);
    Pick p5 = new Pick(new Location(0.7, 0.7), 0.55);
    Pick p6 = new Pick(new Location(1.2, 1.2), 0.06);
    Pick p7 = new Pick(new Location(1.3, 1.3), 0.17);
    Pick p8 = new Pick(new Location(1.4, 1.4), 0.18);

    List<Pick> plist = new ArrayList<>(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8));

    List<Cluster> clusters = ConstrainedKMeans.cluster(plist);
    assertEquals(3, clusters.size());

    List<List<Pick>> actual = new ArrayList<>();
    clusters.forEach(cluster -> actual.add(cluster.getPicks()));
    actual.forEach(picks -> picks.sort(Comparator.comparing(Pick::getConstraint)));
    actual.sort(Comparator.comparing(x -> x.get(0).getConstraint()));

    List<List<Pick>> expected = new ArrayList<>();
    expected.add(Arrays.asList(p6, p7, p8, p5));
    expected.add(Arrays.asList(p1, p2, p3));
    expected.add(Arrays.asList(p4));

    assertEquals(expected, actual);
  }

  @Test
  public void clustering_swap_3() {
    Pick p1 = new Pick(new Location(0, 0), 0.31);
    Pick p2 = new Pick(new Location(0.1, 0.1), 0.32);
    Pick p3 = new Pick(new Location(0.2, 0.2), 0.33);
    Pick p4 = new Pick(new Location(0.6, 0.6), 0.54);
    Pick p5 = new Pick(new Location(0.7, 0.7), 0.55);
    Pick p6 = new Pick(new Location(1.2, 1.2), 0.16);
    Pick p7 = new Pick(new Location(1.3, 1.3), 0.17);
    Pick p8 = new Pick(new Location(1.4, 1.4), 0.18);

    List<Pick> plist = new ArrayList<>(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8));

    List<Cluster> clusters = ConstrainedKMeans.cluster(plist);
    assertEquals(3, clusters.size());

    List<List<Pick>> actual = new ArrayList<>();
    clusters.forEach(cluster -> actual.add(cluster.getPicks()));
    actual.forEach(picks -> picks.sort(Comparator.comparing(Pick::getConstraint)));
    actual.sort(Comparator.comparing(x -> x.get(0).getConstraint()));

    List<List<Pick>> expected = new ArrayList<>();
    expected.add(Arrays.asList(p6, p7, p5));
    expected.add(Arrays.asList(p8, p4));
    expected.add(Arrays.asList(p1, p2, p3));

    assertEquals(expected, actual);
  }
}
