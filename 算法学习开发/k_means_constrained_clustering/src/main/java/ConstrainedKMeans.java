import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;
import java.util.stream.Collectors;

class ConstrainedKMeans {

  private final static int MAX_ITER = 100;

  static List<Cluster> cluster(List<Pick> picks) {
    List<Cluster> clusters = KMeans.cluster(picks);

    while (!successAssignPicksToClusters(picks, clusters)) {
      clusters = KMeans.cluster(picks, clusters.size() + 1);
    }

    rearrangeClustering(clusters);

    return clusters;
  }

  static boolean successAssignPicksToClusters(List<Pick> picks, List<Cluster> clusters) {

    clusters.forEach(Cluster::clearPicks);

    // the bigger the delta is, the more forward it is in the list
    picks.sort(Comparator.comparing(pick ->
        PickCalculator.calculatePickToClustersDistanceDelta(pick, clusters)));

    List<Pick> notAssignedPicks = new ArrayList<>();

    for (Pick currentPick : picks) {
      if (!successAssignPickToAnyCluster(currentPick, clusters)) {
        notAssignedPicks.add(currentPick);
      }
    }

    return successReassignMissedPicks(notAssignedPicks, clusters);
  }

  static boolean successAssignPickToAnyCluster(Pick currentPick, List<Cluster> clusters) {
    List<Cluster> sortedClusters = PickCalculator.sortPreferredClusters(currentPick, clusters);
    Optional<Cluster> cluster = sortedClusters.stream().filter(c -> c.fitPick(currentPick))
        .findFirst();
    if (cluster.isPresent()) {
      Cluster targetCluster = cluster.get();
      targetCluster.addPick(currentPick);
      return true;
    }
    return false;
  }

  static boolean successReassignMissedPicks(List<Pick> notAssignedPicks, List<Cluster> clusters) {
    for (ListIterator<Pick> missedIterator = notAssignedPicks.listIterator();
        missedIterator.hasNext(); ) {
      Pick missedPick = missedIterator.next();
      if (successAssignPickToAnyCluster(missedPick, clusters)) {
        missedIterator.remove();
        continue;
      }

      List<Cluster> sortedClusters = PickCalculator.sortPreferredClusters(missedPick, clusters);
      for (Cluster targetCluster : sortedClusters) {
        boolean assigned = false;
        for (ListIterator<Pick> swapIterator = targetCluster.getPicks().listIterator();
            swapIterator.hasNext(); ) {
          Pick swapCandidate = swapIterator.next();
          if (targetCluster.fitPickWhenSwap(missedPick, swapCandidate)) {
            List<Cluster> otherClusters = clusters.stream()
                .filter(cluster -> !cluster.equals(targetCluster)).collect(Collectors.toList());
            if (successAssignPickToAnyCluster(swapCandidate, otherClusters)) {
              swapIterator.remove();
              missedIterator.remove();
              swapIterator.add(missedPick);
              assigned = true;
              break;
            }
          }
        }
        if (assigned) {
          break;
        }
      }
    }

    return notAssignedPicks.isEmpty();
  }

  static void rearrangeClustering(List<Cluster> clusters) {
    int iter = 0;

    while (iter < MAX_ITER) {
      List<Location> lastCentroids = KMeans.recordCentroids(clusters);
      rearrangeCandidates(clusters);
      KMeans.updateClusterCentroids(clusters);
      List<Location> newCentroids = KMeans.recordCentroids(clusters);
      iter++;

      if (newCentroids.equals(lastCentroids)) {
        break;
      }
    }
  }

  static void rearrangeCandidates(List<Cluster> clusters) {
    for (Cluster currentCluster : clusters) {
      for (ListIterator<Pick> currentIterator = currentCluster.getPicks().listIterator();
          currentIterator.hasNext(); ) {
        Pick currentPick = currentIterator.next();
        List<Cluster> sortedClusters = PickCalculator.sortPreferredClusters(currentPick, clusters);
        for (Cluster betterCluster : sortedClusters) {
          // if already in the better cluster that it could be
          if (betterCluster.equals(currentCluster)) {
            break;
          }
          // if the better cluster can fit, add pick to this cluster and remove from old cluster
          else if (betterCluster.fitPick(currentPick)) {
            movePick(currentIterator, currentPick, betterCluster);
            break;
          }
          // try to swap with other picks not in optimal cluster
          else {
            Optional<Pick> swapCandidate = getSwapCandidate(currentPick, currentCluster,
                betterCluster, clusters);
            if (swapCandidate.isPresent()) {
              swapPicks(currentIterator, currentPick, swapCandidate.get(), betterCluster);
              break;
            }
          }
        }
      }
    }
  }

  private static Optional<Pick> getSwapCandidate(Pick currentPick, Cluster currentCluster,
      Cluster betterCluster, List<Cluster> clusters) {
    for (Pick swapCandidate : PickCalculator.nonOptimalPicksInCluster(betterCluster, clusters)) {
      if (betterCluster.fitPickWhenSwap(currentPick, swapCandidate)
          && currentCluster.fitPickWhenSwap(swapCandidate, currentPick)
          && isImproved(currentPick, swapCandidate, currentCluster, betterCluster)) {
        return Optional.of(swapCandidate);
      }
    }
    return Optional.empty();
  }

  private static boolean isImproved(Pick p1, Pick p2, Cluster c1, Cluster c2) {
    double originalDistance = p1.distance(c1.getCentroid()) + p2.distance(c2.getCentroid());
    double swappedDistance = p1.distance(c2.getCentroid()) + p2.distance(c1.getCentroid());
    return swappedDistance < originalDistance;
  }

  private static void movePick(ListIterator<Pick> currentIterator, Pick currentPick,
      Cluster betterCluster) {
    currentIterator.remove();
    if (!betterCluster.getPicks().contains(currentPick)) {
      betterCluster.addPick(currentPick);
    }
  }

  private static void swapPicks(ListIterator<Pick> currentIterator, Pick currentPick,
      Pick swapCandidate, Cluster betterCluster) {

    currentIterator.remove();
    currentIterator.add(swapCandidate);
    betterCluster.removePick(swapCandidate);
    betterCluster.addPick(currentPick);

  }


}
