package nisarg;

import battlecode.common.*;

import java.util.ArrayList;
import java.util.HashSet;
import static nisarg.ArchonBot.*;
import static nisarg.RobotPlayer.*;

/**
 * Created by nisar on 11-01-2017.
 */
public class GardenerBot {

    static Direction[] directions={Direction.getEast(),Direction.getNorth(),Direction.getWest(),Direction.getSouth()};
    static int prevNumGard=0;
    static void runGardener() throws GameActionException {
        System.out.println("I'm a gardener!");

        // The code you want your robot to perform every round should be in this loop
        while (true) {

            // Try/catch blocks stop unhandled exceptions, which cause your robot to explode
            try {
                if (rc.getHealth() < RobotType.GARDENER.maxHealth / 10 &&
                        rc.senseNearbyRobots(-1, rc.getTeam().opponent()).length > 0)
                    rc.broadcast(GARDENER_CHANNEL, rc.readBroadcast(GARDENER_CHANNEL) - 1);
                int i = 0;
                // Listen for home archon's location
                //  int prev = rc.readBroadcast(GARDENER_CHANNEL);
                //  rc.broadcast(GARDENER_CHANNEL, prev + 1);
                int xPos = rc.readBroadcast(0);
                int yPos = rc.readBroadcast(1);
                MapLocation archonLoc = new MapLocation(xPos, yPos);

                // Generate a random direction
                Direction dir = randomDirection();//rc.getLocation().directionTo(rc.getInitialArchonLocations(rc.getTeam().opponent())[0]);
//
//                if (rc.getLocation().distanceSquaredTo(archonLoc)< 10) {
//                    while (rc.getLocation().distanceSquaredTo(archonLoc)<= 10)
//                        tryMove(randomDirection());
//                }
//                // Randomly attempt to build a soldier or lumberjack in this direction
//                else {
//                    for (Direction d : directions) {
//                        if (rc.canPlantTree(d)) {
//                            rc.plantTree(d);
//                        }
//                    }
//                    TreeInfo[] trees = rc.senseNearbyTrees();
//                    for (TreeInfo t : trees) {
//                        if (rc.canWater(t.location)) {
//                            rc.water(t.location);
//                        }
//                        t.getContainedBullets();
//                        t.getContainedRobot();
//                    }
                // if (rc.getRoundNum() < 3000) {
                int prev = rc.readBroadcast(GARDENER_CHANNEL);
             //   rc.broadcast(GARDENER_CHANNEL, prev+1);
//                int scoutCount = rc.readBroadcast(SCOUT_CHANNEL);
               prevNumGard = rc.readBroadcast(LUMBERJACK_CHANNEL);
 //               int prevScoutNum=rc.readBroadcast(SCOUT_CHANNEL);
                 int prevNumSol = rc.readBroadcast(SOLDIER_CHANNEL);
                //rc.broadcast(GARDENER_CHANNEL, prev+1);
                TreeInfo[] trees = rc.senseNearbyTrees(1.5f);
                //  Direction dir = randomDirection();
         //       Direction dir1 = rc.getLocation().directionTo(rc.getInitialArchonLocations(rc.getTeam())[0]);
//                if(rc.getLocation().isWithinDistance(archonLoc,6)){
//                    while(rc.getLocation().isWithinDistance(archonLoc,6))
//                        if(rc.canMove(rc.getLocation().directionTo(archonLoc).opposite()))
//                            tryMove(rc.getLocation().directionTo(archonLoc).opposite());
//                        else if(rc.canMove(rc.getLocation().directionTo(archonLoc).rotateRightDegrees(90)))
//                        {tryMove(rc.getLocation().directionTo(archonLoc).rotateRightDegrees(90));}
//                        else
//                            tryMove(rc.getLocation().directionTo(archonLoc).rotateRightDegrees(90));
//                }



                if (rc.getLocation().isWithinDistance(archonLoc, 6)) //&& rc.getLocation().isWithinDistance(rc.senseNearbyRobots(1.5f,rc.getTeam())[0].location,1.5f))
                {  while (rc.getLocation().isWithinDistance(archonLoc, 6))// && rc.getLocation().isWithinDistance(rc.senseNearbyRobots(1.5f,rc.getTeam())[0].location,1.5f))
                {  if (!rc.hasMoved()) {
                   // if(rc.canMove(rc.getLocation().directionTo(rc.getInitialArchonLocations(rc.getTeam().opponent())[0]).opposite()))
                            tryMove(rc.getLocation().directionTo(rc.getInitialArchonLocations(rc.getTeam().opponent())[0]), 15, 24);
                   // tryMove(randomDirection());
//                    else if(rc.canMove(rc.getLocation().directionTo(rc.getInitialArchonLocations(rc.getTeam().opponent())[0])))
//                        tryMove(rc.getLocation().directionTo(rc.getInitialArchonLocations(rc.getTeam().opponent())[0]), 15, 24);

                }
                       // Clock.yield();
                    }
                }



//                if (prev >= GARDENER_MAX)
//                {
//                  if (rc.canBuildRobot(rc.getType().SCOUT, rc.getLocation().directionTo(archonLoc).opposite().rotateRightDegrees(10))
//                            && scoutCount<SCOUT_MAX) {
//                        scoutCount++;
//                        rc.broadcast(SCOUT_CHANNEL,scoutCount);
//                        rc.buildRobot(rc.getType().SCOUT, rc.getLocation().directionTo(archonLoc).opposite().rotateRightDegrees(10));
//                      dir.rotateLeftDegrees(10);
//                      Clock.yield();
//                    }
//                    while (prevScoutNum <= SCOUT_MAX) {
//                        if (rc.canBuildRobot(rc.getType().SCOUT, dir)) {
//                            rc.broadcast(SCOUT_CHANNEL, prevScoutNum + 1);
//                            rc.buildRobot(rc.getType().SCOUT, dir);
//                            dir.rotateLeftDegrees(20);
//                            rc.readBroadcast(SCOUT_CHANNEL);
//                            Clock.yield();
//                        }
//                    }
//                    while (prevNumGard <= LUMBERJACK_MAX) {
//                        if (rc.canBuildRobot(RobotType.LUMBERJACK, dir)) {
//                            rc.broadcast(LUMBERJACK_CHANNEL, prevNumGard + 1);
//                            rc.buildRobot(RobotType.LUMBERJACK, dir);
//                            dir.rotateLeftDegrees(30);
//                            prevNumGard=rc.readBroadcast(LUMBERJACK_CHANNEL);
//                            //Clock.yield();
//                        }
//                        dir.rotateLeftDegrees(30);
//                    }
//                while (prevNumSol <= SOLDIER_MAX) {
//                    if (rc.canBuildRobot(RobotType.SOLDIER, dir)) {
//                        prevNumSol=rc.readBroadcast(SOLDIER_CHANNEL);
//                        rc.broadcast(SOLDIER_CHANNEL, prevNumSol + 1);

//                        rc.buildRobot(RobotType.SOLDIER, dir);
                //                        dir.rotateLeftDegrees(30);
//                        Clock.yield();
//                    }
//                    dir.rotateLeftDegrees(30);
//                }
                for (int j = 0; j < 180; j++) {
                    //                       tryPlant(rc.getLocation().directionTo(rc.getInitialArchonLocations(rc.getTeam())[0]).rotateLeftDegrees(60),1,360);
         //           dir1 = rc.getLocation().directionTo(rc.getInitialArchonLocations(rc.getTeam().opponent())[0]).rotateLeftDegrees(45);
                    if (rc.canPlantTree(dir)) {
                        rc.plantTree(dir);
                       // Clock.yield();
                    }
                    dir.rotateLeftDegrees(2);
                    j++;
                }
                    if (trees.length > 0) {
                        while (i < trees.length) {
                            if (rc.canShake()) rc.shake(trees[i].ID);
                            if (trees[i].getTeam()==rc.getTeam() && rc.canWater(trees[i].ID)) rc.water(trees[i].ID);
                            Clock.yield();
                            i++;
                        }
                    }


//                    }
              //  Clock.yield();
            } catch(Exception e) {
                System.out.println("Gardener Exception");
                e.printStackTrace();
            }
        }
    }

    private static int healthGreaterThan10(TreeInfo[] trees) {
        int i=0;
        while(i<trees.length)
        { if(trees[i].health>10f)return i;
        i++;
    }
        return trees.length+1;
    }

    private static int healthLessthan10(TreeInfo[] trees) {
        int i = 0;
        while (i < trees.length) {
            if (trees[i].health < 10f) return i;
            i++;
        }
        return trees.length+1;
    }
    static boolean tryPlant(Direction dir, float degreeOffset, int checksPerSide) throws GameActionException {
        int currentCheck = 1;
        while(currentCheck<=checksPerSide) {
            // Try the offset of the left side
            if(rc.canPlantTree(dir))
            {
                rc.plantTree(dir);
                return true;
            }
            dir.rotateLeftDegrees(degreeOffset*currentCheck);
            currentCheck++;
        }
        // A move never happened, so return false.
        return false;
    }
}
