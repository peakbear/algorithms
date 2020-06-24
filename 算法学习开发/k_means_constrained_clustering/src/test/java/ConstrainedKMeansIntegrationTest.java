import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import org.junit.Test;

public class ConstrainedKMeansIntegrationTest {

  @Test
  public void cluster_singleCLuster() {
    double constraintSum = TestPicks.singleCluster.stream().mapToDouble(Pick::getConstraint).sum();
    int estimatedK = new BigDecimal(constraintSum).setScale(0, RoundingMode.CEILING).intValue();
    List<Cluster> assignments = ConstrainedKMeans.cluster(TestPicks.singleCluster);
    assertEquals(estimatedK, assignments.size());
  }

  @Test
  public void cluster_testPick1() {
    double constraintSum = TestPicks.testPicks.stream().mapToDouble(Pick::getConstraint).sum();
    int estimatedK = new BigDecimal(constraintSum).setScale(0, RoundingMode.CEILING).intValue();
    List<Cluster> assignments = ConstrainedKMeans.cluster(TestPicks.testPicks);
    assertEquals(estimatedK, assignments.size());
  }

  @Test
  public void cluster_testPick2() {
    double constraintSum = TestPicks.testPicks2.stream().mapToDouble(Pick::getConstraint).sum();
    int estimatedK = new BigDecimal(constraintSum).setScale(0, RoundingMode.CEILING).intValue();
    List<Cluster> assignments = ConstrainedKMeans.cluster(TestPicks.testPicks2);
    assertEquals(estimatedK, assignments.size());
  }

  @Test
  public void cluster_testPick3() {
    double constraintSum = TestPicks.testPicks3.stream().mapToDouble(Pick::getConstraint).sum();
    int estimatedK = new BigDecimal(constraintSum).setScale(0, RoundingMode.CEILING).intValue();
    List<Cluster> assignments = ConstrainedKMeans.cluster(TestPicks.testPicks3);
    assertEquals(estimatedK, assignments.size());
  }
}
