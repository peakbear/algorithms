import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.junit.Test;

public class ConstrainedKMeansFeatureTest {

  // ------------------------ ASSIGN PICKS TO CLUSTERS ----------------------------------------
  @Test
  public void assignedPickToAnyCluster_success() {
    List<Pick> picks = generateStandardTestPicks();
    List<Cluster> clusters = generateStandardTestClusters(picks);

    Pick testPick = new Pick(new Location(1.4, 1.4), 0.30);
    assertTrue(ConstrainedKMeans.successAssignPickToAnyCluster(testPick, clusters));

    List<List<Pick>> actual = getActualLists(clusters);
    List<List<Pick>> expected = new ArrayList<>();
    expected.add(Arrays.asList(testPick, picks.get(3), picks.get(4)));
    expected.add(Arrays.asList(picks.get(0), picks.get(1), picks.get(2)));
    expected.add(Arrays.asList(picks.get(5), picks.get(6), picks.get(7)));

    assertEquals(expected, actual);

  }

  @Test
  public void assignedPickToAnyCluster_fail() {
    List<Pick> picks = generateStandardTestPicks();
    List<Cluster> clusters = generateStandardTestClusters(picks);

    Pick testPick = new Pick(new Location(1.4, 1.4), 0.50);
    assertFalse(ConstrainedKMeans.successAssignPickToAnyCluster(testPick, clusters));

    List<List<Pick>> actual = getActualLists(clusters);
    List<List<Pick>> expected = new ArrayList<>();
    expected.add(Arrays.asList(picks.get(0), picks.get(1), picks.get(2)));
    expected.add(Arrays.asList(picks.get(3), picks.get(4)));
    expected.add(Arrays.asList(picks.get(5), picks.get(6), picks.get(7)));

    assertEquals(expected, actual);
  }

  @Test
  public void reassignMissedPicks_fitIn_success() {
    List<Pick> picks = generateStandardTestPicks();
    List<Cluster> clusters = generateStandardTestClusters(picks);

    Pick testPick = new Pick(new Location(1.4, 1.4), 0.30);
    List<Pick> notAssignedPicks = new ArrayList<>(Collections.singletonList(testPick));

    assertTrue(ConstrainedKMeans.successReassignMissedPicks(notAssignedPicks, clusters));

    assertTrue(notAssignedPicks.isEmpty());

    List<List<Pick>> actual = getActualLists(clusters);
    List<List<Pick>> expected = new ArrayList<>();
    expected.add(Arrays.asList(testPick, picks.get(3), picks.get(4)));
    expected.add(Arrays.asList(picks.get(0), picks.get(1), picks.get(2)));
    expected.add(Arrays.asList(picks.get(5), picks.get(6), picks.get(7)));

    assertEquals(expected, actual);
  }

  @Test
  public void reassignMissedPicks_swap_success() {
    List<Pick> picks = generateStandardTestPicks();
    List<Cluster> clusters = generateStandardTestClusters(picks);

    Pick testPick = new Pick(new Location(1.4, 1.4), 0.395);
    List<Pick> notAssignedPicks = new ArrayList<>(Collections.singletonList(testPick));

    assertTrue(ConstrainedKMeans.successReassignMissedPicks(notAssignedPicks, clusters));

    assertTrue(notAssignedPicks.isEmpty());

    List<List<Pick>> actual = getActualLists(clusters);
    List<List<Pick>> expected = new ArrayList<>();
    expected.add(Arrays.asList(picks.get(0), picks.get(3), picks.get(4)));
    expected.add(Arrays.asList(picks.get(1), picks.get(2), testPick));
    expected.add(Arrays.asList(picks.get(5), picks.get(6), picks.get(7)));

    assertEquals(expected, actual);
  }

  @Test
  public void reassignMissedPicks_fail() {
    List<Pick> picks = generateStandardTestPicks();
    List<Cluster> clusters = generateStandardTestClusters(picks);

    Pick testPick = new Pick(new Location(1.4, 1.4), 0.400);
    List<Pick> notAssignedPicks = new ArrayList<>(Collections.singletonList(testPick));

    assertFalse(ConstrainedKMeans.successReassignMissedPicks(notAssignedPicks, clusters));

    assertFalse(notAssignedPicks.isEmpty());

    List<List<Pick>> actual = getActualLists(clusters);
    List<List<Pick>> expected = new ArrayList<>();
    expected.add(Arrays.asList(picks.get(0), picks.get(1), picks.get(2)));
    expected.add(Arrays.asList(picks.get(3), picks.get(4)));
    expected.add(Arrays.asList(picks.get(5), picks.get(6), picks.get(7)));

    assertEquals(expected, actual);
  }

  @Test
  public void assignPicksToClusters_success() {
    List<Pick> picks = generateStandardTestPicks();
    List<Cluster> clusters = generateEmptyTestClusters();

    assertTrue(ConstrainedKMeans.successAssignPicksToClusters(picks, clusters));
  }

  @Test
  public void assignPicksToClusters_success2() {
    List<Pick> picks = generateStandardTestPicks();
    picks.add(new Pick(new Location(1.4, 1.4), 0.395));
    List<Cluster> clusters = generateEmptyTestClusters();

    assertTrue(ConstrainedKMeans.successAssignPicksToClusters(picks, clusters));
  }

  @Test
  public void assignPicksToClusters_fail() {
    List<Pick> picks = generateStandardTestPicks();
    picks.add(new Pick(new Location(1.4, 1.4), 0.400));
    List<Cluster> clusters = generateEmptyTestClusters();

    assertFalse(ConstrainedKMeans.successAssignPicksToClusters(picks, clusters));
  }


  // ------------------------ REARRANGE CLUSTERS ----------------------------------------
  @Test
  public void rearrangeClustering_swap1() {
    List<Pick> picks = generateStandardTestPicks();
    List<Cluster> clusters = generateEmptyTestClusters();
    clusters.get(0).addPick(picks.get(0));
    clusters.get(0).addPick(picks.get(1));
    clusters.get(0).addPick(picks.get(3));
    clusters.get(1).addPick(picks.get(4));
    clusters.get(1).addPick(picks.get(2));
    clusters.get(2).addPick(picks.get(5));
    clusters.get(2).addPick(picks.get(6));
    clusters.get(2).addPick(picks.get(7));

    ConstrainedKMeans.rearrangeClustering(clusters);

    List<List<Pick>> actual = getActualLists(clusters);
    List<List<Pick>> expected = new ArrayList<>();
    expected.add(Arrays.asList(picks.get(0), picks.get(1), picks.get(2)));
    expected.add(Arrays.asList(picks.get(3), picks.get(4)));
    expected.add(Arrays.asList(picks.get(5), picks.get(6), picks.get(7)));

    assertEquals(expected, actual);
  }

  @Test
  public void rearrangeClustering_swap2() {
    List<Pick> picks = generateStandardTestPicks();
    List<Cluster> clusters = generateEmptyTestClusters();
    clusters.get(0).addPick(picks.get(0));
    clusters.get(0).addPick(picks.get(6));
    clusters.get(0).addPick(picks.get(3));
    clusters.get(1).addPick(picks.get(4));
    clusters.get(1).addPick(picks.get(2));
    clusters.get(2).addPick(picks.get(5));
    clusters.get(2).addPick(picks.get(1));
    clusters.get(2).addPick(picks.get(7));

    ConstrainedKMeans.rearrangeClustering(clusters);

    List<List<Pick>> actual = getActualLists(clusters);
    List<List<Pick>> expected = new ArrayList<>();
    expected.add(Arrays.asList(picks.get(0), picks.get(1), picks.get(2)));
    expected.add(Arrays.asList(picks.get(3), picks.get(4)));
    expected.add(Arrays.asList(picks.get(5), picks.get(6), picks.get(7)));

    assertEquals(expected, actual);
  }

  @Test
  public void rearrangeClustering_swap3() {
    List<Pick> picks = generateStandardTestPicks();
    List<Cluster> clusters = generateEmptyTestClusters();
    clusters.get(0).addPick(picks.get(0));
    clusters.get(0).addPick(picks.get(6));
    clusters.get(0).addPick(picks.get(2));
    clusters.get(1).addPick(picks.get(3));
    clusters.get(1).addPick(picks.get(1));
    clusters.get(2).addPick(picks.get(5));
    clusters.get(2).addPick(picks.get(4));
    clusters.get(2).addPick(picks.get(7));

    ConstrainedKMeans.rearrangeClustering(clusters);

    List<List<Pick>> actual = getActualLists(clusters);
    List<List<Pick>> expected = new ArrayList<>();
    expected.add(Arrays.asList(picks.get(0), picks.get(1), picks.get(2)));
    expected.add(Arrays.asList(picks.get(3), picks.get(4)));
    expected.add(Arrays.asList(picks.get(5), picks.get(6), picks.get(7)));

    assertEquals(expected, actual);
  }


  // ------------------------ HELPERS ----------------------------------------
  private static List<Pick> generateStandardTestPicks() {
    Pick p1 = new Pick(new Location(0, 0), 0.301);
    Pick p2 = new Pick(new Location(0.1, 0.1), 0.302);
    Pick p3 = new Pick(new Location(0.2, 0.2), 0.303);
    Pick p4 = new Pick(new Location(0.6, 0.6), 0.304);
    Pick p5 = new Pick(new Location(0.7, 0.7), 0.305);
    Pick p6 = new Pick(new Location(1.2, 1.2), 0.306);
    Pick p7 = new Pick(new Location(1.3, 1.3), 0.307);
    Pick p8 = new Pick(new Location(1.4, 1.4), 0.308);
    return new ArrayList<>(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8));
  }

  private static List<Cluster> generateStandardTestClusters(List<Pick> picks) {
    Cluster cluster1 = new Cluster(new Location(0.1, 0.1));
    cluster1.addPick(picks.get(0));
    cluster1.addPick(picks.get(1));
    cluster1.addPick(picks.get(2));
    Cluster cluster2 = new Cluster(new Location(0.65, 0.65));
    cluster2.addPick(picks.get(3));
    cluster2.addPick(picks.get(4));
    Cluster cluster3 = new Cluster(new Location(1.3, 1.3));
    cluster3.addPick(picks.get(5));
    cluster3.addPick(picks.get(6));
    cluster3.addPick(picks.get(7));
    return new ArrayList<>(Arrays.asList(cluster1, cluster2, cluster3));
  }

  private static List<Cluster> generateEmptyTestClusters() {
    Cluster cluster1 = new Cluster(new Location(0.1, 0.1));
    Cluster cluster2 = new Cluster(new Location(0.65, 0.65));
    Cluster cluster3 = new Cluster(new Location(1.3, 1.3));
    return new ArrayList<>(Arrays.asList(cluster1, cluster2, cluster3));
  }

  private static List<List<Pick>> getActualLists(List<Cluster> clusters) {
    List<List<Pick>> actual = new ArrayList<>();
    clusters.forEach(cluster -> actual.add(cluster.getPicks()));
    actual.forEach(picks -> picks.sort(Comparator.comparing(Pick::getConstraint)));
    actual.sort(Comparator.comparing(x -> x.get(0).getConstraint()));
    return actual;
  }


}