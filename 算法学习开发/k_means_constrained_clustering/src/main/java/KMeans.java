import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

class KMeans {

  private final static int MAX_ITER = 100;

  static List<Cluster> cluster(final List<Pick> picks) {
    final int k = initialiseK(picks);
    List<Cluster> clusters = initialiseClustersCentroids(picks, k);
    return cluster(picks, clusters);
  }

  static List<Cluster> cluster(final List<Pick> picks, final int k) {
    List<Cluster> clusters = initialiseClustersCentroids(picks, k);
    return cluster(picks, clusters);
  }

  // CLUSTER -----------------------------------------------------------
  static List<Cluster> cluster(final List<Pick> picks, List<Cluster> clusters) {
    int iter = 0;
    while (iter < MAX_ITER) {
      List<Location> lastCentroids = recordCentroids(clusters);
      assignPicksToClusters(picks, clusters);
      List<Location> newCentroids = recordCentroids(clusters);
      iter++;

      if (newCentroids.equals(lastCentroids)) {
        break;
      }
    }

    return clusters;
  }


  // INIT -----------------------------------------------------------
  static int initialiseK(List<Pick> picks) {
    picks.sort(Comparator.comparingDouble(Pick::getConstraint).reversed());
    List<Cluster> clusters = new ArrayList<>();
    for (Pick pick : picks) {
      Optional<Cluster> cluster = clusters.stream().filter(c -> c.fitPick(pick)).findFirst();
      if (cluster.isPresent()) {
        cluster.get().addPick(pick);
      } else {
        Cluster newCluster = new Cluster(new Location(pick.getLoc()));
        newCluster.addPick(pick);
        clusters.add(newCluster);
      }
    }
    return clusters.size();
  }

  // K means ++
  static List<Cluster> initialiseClustersCentroids(final List<Pick> picks, final int k) {
    Random rand = new Random();

    List<Cluster> clusters = new ArrayList<>();
    Location initCentroid = new Location(picks.get(rand.nextInt(picks.size())).getLoc());
    clusters.add(new Cluster(initCentroid));

    for (int i = 1; i < k; i++) {
      List<Cluster> bestClusters = picks.stream().map(pick ->
          PickCalculator.findNearestCluster(pick, clusters)).collect(Collectors.toList());
      double sumOfSquaredDistances = 0;
      for (int index = 0; index < picks.size(); ++index) {
        sumOfSquaredDistances += Math
            .pow(picks.get(index).distance(bestClusters.get(index).getCentroid()), 2);
      }

      double r = rand.nextDouble();
      double accumulator = 0.0;
      int index = 0;
      while (accumulator < r) {
        accumulator += Math.pow(picks.get(index).distance(bestClusters.get(index).getCentroid()), 2)
            / sumOfSquaredDistances;
        index++;
      }
      index = index > 0 ? index - 1 : 0;
      Location newCentroid = new Location(picks.get(index).getLoc());
      clusters.add(new Cluster(newCentroid));
    }

    return clusters;
  }

  static List<Location> recordCentroids(final List<Cluster> clusters) {
    return clusters.stream().map(c -> new Location(c.getCentroid())).collect(Collectors.toList());
  }

  private static void assignPicksToClusters(final List<Pick> picks, List<Cluster> clusters) {
    clearClusterPicks(clusters);
    picks.forEach(pick -> {
      Cluster cluster = PickCalculator.findNearestCluster(pick, clusters);
      cluster.addPick(pick);
    });
    updateClusterCentroids(clusters);
  }

  private static void clearClusterPicks(List<Cluster> clusters) {
    clusters.forEach(Cluster::clearPicks);
  }


  static void updateClusterCentroids(List<Cluster> clusters) {
    clusters.forEach(Cluster::computeCentroid);
  }


}
