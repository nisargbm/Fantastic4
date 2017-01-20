package nisarg;

import battlecode.common.*;

import static nisarg.ArchonBot.GARDENER_CHANNEL;
import static nisarg.ArchonBot.SOLDIER_CHANNEL;
import static nisarg.RobotPlayer.*;

/**
 * Created by nisar on 11-01-2017.
 */
public class SoldierBot {
    static void runSoldier() throws GameActionException {
        System.out.println("I'm an soldier!");
        Team enemy = rc.getTeam().opponent();
        while (true) {

            try {
                if (rc.getHealth() < RobotType.SOLDIER.maxHealth / 10 &&
                        rc.senseNearbyRobots(-1, rc.getTeam().opponent()).length > 0)
                    rc.broadcast(SOLDIER_CHANNEL, rc.readBroadcast(SOLDIER_CHANNEL) - 1);
                int xPosArc = rc.readBroadcast(0);
                int yPosArc = rc.readBroadcast(1);
                MapLocation archonLoc = new MapLocation(xPosArc, yPosArc);
                dodge();
                TreeInfo[] trees = rc.senseNearbyTrees();
                for (TreeInfo t : trees) {
                    if(rc.canShake(t.location)) rc.shake(t.location);
                    if ((t.getTeam()==rc.getTeam().opponent() || t.getTeam()==Team.NEUTRAL) && rc.canChop(t.getLocation())) {
                        rc.chop(t.getLocation());
                        break;
                    }
                }
                RobotInfo[] bots = rc.senseNearbyRobots();
                for (RobotInfo b : bots) {
                    if (b.getTeam() != rc.getTeam()) {
                        Direction towards = rc.getLocation().directionTo(b.getLocation());
                        rc.fireSingleShot(towards);
                        break;
                    }
                }
                if(rc.getLocation().isWithinDistance(archonLoc,12f)){
                   // tryMove(rc.getLocation().directionTo(archonLoc).opposite(),60,6);
                    tryMove(randomDirection());
                }
//                else if(rc.getLocation().isWithinDistance(archonLoc,20f) && !rc.getLocation().isWithinDistance(archonLoc,8f)){
//                    tryMove(randomDirection());
//                }
                else
                    tryMove(rc.getLocation().directionTo(archonLoc),60,6);
                Clock.yield();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // The code you want your robot to perform every round should be in this loop
//        while (true) {
//
//            // Try/catch blocks stop unhandled exceptions, which cause your robot to explode
//            try {
//                MapLocation myLocation = rc.getLocation();
//                int xPos = rc.readBroadcast(10);
//                int yPos = rc.readBroadcast(11);
//                int xpos = rc.readBroadcast(0);
//                int ypos = rc.readBroadcast(1);
//                MapLocation archonLoc = new MapLocation(xPos, yPos);
//                MapLocation enemy2 = new MapLocation(xPos - RobotType.LUMBERJACK.bodyRadius - RobotType.LUMBERJACK.strideRadius, yPos - RobotType.LUMBERJACK.bodyRadius - RobotType.LUMBERJACK.strideRadius);
//                int prevNumSol = rc.readBroadcast(SOLDIER_CHANNEL);
//                RobotInfo[] robots = rc.senseNearbyRobots(-1, enemy);
//                // if(rc.readBroadcast(10)!=0 && rc.readBroadcast(11)!=0) {
//
//                // See if there are any nearby enemy robots
//                // If there are some...
//                if (robots.length > 0) {
//                    // And we have enough bullets, and haven't attacked yet this turn...
//
//                    if (prevNumSol >= 5) {
//                        if (rc.canMove(rc.getLocation().directionTo(robots[0].location))) {
//                            rc.broadcast(10, (int) robots[0].getLocation().x);
//                            rc.broadcast(11, (int) robots[0].getLocation().y);
//                            rc.move(rc.getLocation().directionTo(robots[0].location));
//                        }
//                        if (rc.canFireSingleShot()) {
//                            // ...Then fire a bullet in the direction of the enemy.
//                            rc.fireSingleShot(rc.getLocation().directionTo(robots[0].location));
//                        }
//                    } else {
//                        if (robots[0].getLocation().isWithinDistance(rc.getLocation(), 10) && rc.canFireSingleShot()) {
//                            // ...Then fire a bullet in the direction of the enemy.
//                            rc.fireSingleShot(rc.getLocation().directionTo(robots[0].location));
//                        }
//                        // tryMove(rc.getLocation().directionTo(robots[0].location));
//                    }
//                }
//                //  }
//                else {
//                    robots = rc.senseNearbyRobots(-1, rc.getTeam());
//                    if (rc.getLocation().isWithinDistance(archonLoc, 15)) {
//                        tryMove(randomDirection());
//                    }
////                            tryMove(myLocation.directionTo(robots[0].getLocation()));
////
////                            if (rc.canFireSingleShot())
////                                rc.fireSingleShot(rc.getLocation().directionTo(archonLoc));
//                    else if (robots.length > 0)
//                        tryMove(rc.getLocation().directionTo(robots[0].getLocation()));
//                }
//
//                // Move randomly
//
//                // Clock.yield() makes the robot wait until the next turn, then it will perform this loop again
//                Clock.yield();
//            }
//            catch (Exception e) {
//                System.out.println("Soldier Exception");
//                e.printStackTrace();
//            }
//        }
    }

}
