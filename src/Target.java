import java.util.Map;

public class Target {

    private String targetName;
    private int hitPoint;
    private int targetLocation;

    public Target (String targetName, int hitPoint, int targetLocation) {
        this.targetName = targetName;
        this.hitPoint = hitPoint;
        this.targetLocation = targetLocation;
    }

    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    public int getHitPoint() {
        return hitPoint;
    }

    public void setHitPoint(int hitPoint) {
        this.hitPoint = hitPoint;
    }

    public int getTargetLocation() {
        return targetLocation;
    }

    public void setTargetLocation(int targetLocation) {
        this.targetLocation = targetLocation;
    }

    static {
        Target zombie = new Target("Zombie", 3, 2);
        Target kahvinkeitin = new Target("Kahvinkeitin", 1, 4);
        Target lukko = new Target("Lukko", 1, 3);

    }
}
