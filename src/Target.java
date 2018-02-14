import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Target {

    private String targetName;
    private int hitPoint;
    private int targetLocation;
    private int damageDone;

//    public static List<String> targetEnemies=new ArrayList<Target>() ;


    public Target(String targetName, int hitPoint, int targetLocation, int damageDone) {
        this.targetName = targetName;
        this.hitPoint = hitPoint;
        this.targetLocation = targetLocation;
        this.damageDone = damageDone;
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

    public int getDamageDone(){return damageDone;}

    public void setDamageDone (int damageDone){this.damageDone = damageDone;}


    static {
        Target zombie = new Target("Zombie", 10, 2,10);
        Target kahvinkeitin = new Target("Kahvinkeitin", 1, 4,0);
        Target lukko = new Target("Lukko", 1, 3,0);

    }





    }





