import java.util.ArrayList;
import java.util.List;

public class Cluster {
  public final static double MAX_CONSTRAINT = 1.0;

  private Location centroid;
  private List<Pick> picks;

  public Cluster(Location centroid) {
    this.centroid = new Location(centroid.getX(), centroid.getY());
    picks = new ArrayList<>();
  }

  public Location getCentroid() {
    return centroid;
  }

  public List<Pick> getPicks() {
    return picks;
  }

  public void computeCentroid() {
    centroid.setX(picks.stream().mapToDouble(pick -> pick.getLoc().getX()).average().orElseGet(null));
    centroid.setY(picks.stream().mapToDouble(pick -> pick.getLoc().getY()).average().orElseGet(null));
  }


  public void addPick(Pick pick) {
    picks.add(pick);
  }

  public void removePick(Pick pick) {
    picks.remove(pick);
  }

  public void clearPicks() {
    picks = new ArrayList<>();
  }

  public boolean fitPick(Pick pick) {
    return this.getConstraint() + pick.getConstraint() <= MAX_CONSTRAINT;
  }

  public boolean fitPickWhenSwap(Pick in, Pick out) {
    return this.getConstraint() + in.getConstraint() - out.getConstraint() <= MAX_CONSTRAINT;
  }

  public double getConstraint() {
    return picks.stream().mapToDouble(Pick::getConstraint).sum();
  }

}
