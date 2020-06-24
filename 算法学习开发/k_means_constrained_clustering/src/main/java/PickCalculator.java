import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PickCalculator {

  static Cluster findNearestCluster(Pick pick, List<Cluster> clusters) {
    double minDistance = Double.MAX_VALUE;
    Cluster targetCluster = null;
    for (Cluster cluster : clusters) {
      double distance = pick.distance(cluster.getCentroid());
      if (distance < minDistance) {
        minDistance = distance;
        targetCluster = cluster;
      }
    }
    return targetCluster;
  }

  //  distance to their nearest tote centroid minus distance to the furthest tote centroid
  static double calculatePickToClustersDistanceDelta(Pick pick, List<Cluster> clusters) {
    List<Double> distances = clusters.stream().map(cluster -> pick.distance(cluster.getCentroid()))
        .collect(Collectors.toList());
    return Collections.min(distances) - Collections.max(distances);
  }

  static List<Cluster> sortPreferredClusters(Pick pick, List<Cluster> clusters) {
    return clusters.stream().sorted(Comparator.comparingDouble(c -> pick.distance(c.getCentroid())))
        .collect(Collectors.toList());
  }

  static List<Pick> nonOptimalPicksInCluster(Cluster cluster, List<Cluster> clusters) {
    return cluster.getPicks().stream()
        .filter(pick -> !isOptimal(pick, cluster, clusters))
        .collect(Collectors.toList());
  }

  static boolean isOptimal(Pick pick, Cluster cluster, List<Cluster> clusters) {
    return cluster == PickCalculator.findNearestCluster(pick, clusters);
  }
}
