public class Pick {

  private Location loc;
  private double constraint;

  public Pick(Location loc, double constraint) {
    this.loc = new Location(loc.getX(), loc.getY());
    this.constraint = constraint;
  }

  public Location getLoc() {
    return loc;
  }

  public double getConstraint() {
    return this.constraint;
  }

  public double distance(Location that) {
    return Math.sqrt(Math.pow(this.loc.getX() - that.getX(), 2) + Math
        .pow(this.loc.getY() - that.getY(), 2));
  }

}
