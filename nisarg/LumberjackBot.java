package nisarg;

import battlecode.common.*;

import static lectures.RobotPlayer.wander;
import static nisarg.ArchonBot.*;
import static nisarg.RobotPlayer.*;

public class LumberjackBot {
    static void runLumberjack() throws GameActionException {
        System.out.println("I'm a lumberjack!");
        Team enemy = rc.getTeam().opponent();
        int xPosArc = rc.readBroadcast(0);
        int yPosArc = rc.readBroadcast(1);
        MapLocation archonLoc = new MapLocation(xPosArc, yPosArc);
        int prevGardNum=rc.readBroadcast(GARDENER_CHANNEL);
        int i=0;
        // The code you want your robot to perform every round should be in this loop
        while (true) {
            try {
                if (rc.getHealth() < RobotType.LUMBERJACK.maxHealth / 10 &&
                        rc.senseNearbyRobots(-1, rc.getTeam().opponent()).length > 0)
                    rc.broadcast(LUMBERJACK_CHANNEL, rc.readBroadcast(LUMBERJACK_CHANNEL) - 1);
                dodge();
                RobotInfo[] bots = rc.senseNearbyRobots(-1,rc.getTeam().opponent());
                if(bots.length>0) {
                    for (RobotInfo b : bots) {
                        if (b.getTeam() != rc.getTeam() && rc.canStrike()) {
                            rc.strike();
                            Direction chase = rc.getLocation().directionTo(b.getLocation());
                            tryMove(chase);
                            break;
                        }
                    }
                }
                TreeInfo[] trees = rc.senseNearbyTrees();
                if(trees.length>0) {
                    for (TreeInfo t : trees) {
                       // tryMove(rc.getLocation().directionTo(t.location));
                        if (rc.canShake(t.location)) rc.shake(t.location);
                        if ((t.getTeam() == rc.getTeam().opponent() || t.getTeam() == Team.NEUTRAL) && rc.canChop(t.ID)) {
                            rc.chop(t.ID);
                            Clock.yield();
                        }
                    }
                }
                else
                tryMove(randomDirection(),60,6);
//                if (! rc.hasAttacked()) {
//                   // wander();
//                    if(prevGardNum>=GARDENER_MAX) {
//                        if (rc.getLocation().isWithinDistance(archonLoc, 15f)) {
//                            tryMove(rc.getLocation().directionTo(archonLoc).opposite(), 60, 6);
//                        }
//                        } else if (rc.getLocation().isWithinDistance(archonLoc, 20f) && !rc.getLocation().isWithinDistance(archonLoc, 8f)) {
//                            tryMove(randomDirection());
//                        } else
//                            tryMove(rc.getLocation().directionTo(archonLoc), 60, 6);
 //                   }
  //              }
                Clock.yield();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
//        while (true) {
//
//            // Try/catch blocks stop unhandled exceptions, which cause your robot to explode
//            try {
//
//                // See if there are any enemy robots within striking range (distance 1 from lumberjack's radius)
//                RobotInfo[] robots = rc.senseNearbyRobots(RobotType.LUMBERJACK.bodyRadius+ GameConstants.LUMBERJACK_STRIKE_RADIUS, enemy);
//                TreeInfo[] trees=rc.senseNearbyTrees();
//                int xPos = rc.readBroadcast(10);
//                int yPos = rc.readBroadcast(11);
//                MapLocation enemy1 = new MapLocation(xPos, yPos);
//                int xPosArc = rc.readBroadcast(0);
//                int yPosArc = rc.readBroadcast(1);
//                MapLocation archonLoc = new MapLocation(xPos, yPos);
//                MapLocation myLocation = rc.getLocation();
//                if (robots.length > 0 && rc.canSenseLocation(myLocation)) {
//                    // Use strike() to hit all nearby robots!
//                    rc.strike();
//                } else {
//                    // No close robots, so search for robots within sight radius
//                   // robots = rc.senseNearbyRobots(-1, enemy);
//
//                    // If there is a robot, move towards it
//                    if (robots.length > 0) {
//
//                        MapLocation enemyLocation = robots[0].getLocation();
//                        Direction toEnemy = myLocation.directionTo(enemyLocation);
//                        rc.broadcast(10, (int) robots[0].getLocation().x);
//                        rc.broadcast(11, (int) robots[0].getLocation().y);
//                        tryMove(myLocation.directionTo(enemyLocation));
//
//                        RobotInfo[] bots = rc.senseNearbyRobots();
//                        for (RobotInfo b : bots) {
//                            if (b.getTeam() != rc.getTeam() && rc.canStrike()) {
//                                rc.strike();
//                                Direction chase = rc.getLocation().directionTo(b.getLocation());
//                                tryMove(chase);
//                            }
//                        }
//                    }
//
//                    else if(trees.length>0)
//                    {
//
//                        i=0;
//                        while(i<trees.length) {
//                            if (trees[i].getTeam() != rc.getTeam() && rc.canChop(trees[i].location)) {
//                                rc.chop(trees[i].location);
//                            } else if (!(trees[i].getTeam().isPlayer())) {
//                                if (rc.canShake(trees[i].getLocation()))
//                                    rc.shake(trees[i].location);
//                                rc.chop(trees[i].location);
//                                Clock.yield();
//                            }
//                            i++;
//                        }
//                            RobotInfo[] bots=rc.senseNearbyRobots();
//                            for (RobotInfo b : bots) {
//                                if (b.getTeam() != rc.getTeam() && rc.canStrike()) {
//                                    rc.strike();
//                                    Direction chase = rc.getLocation().directionTo(b.getLocation());
//                                    tryMove(chase);
//                                }
//                            }
//                        }
//                    }
//                    int x=rc.readBroadcast(NEUTRAL_TREE_X_CHANNEL);
//                    int y=rc.readBroadcast(NEUTRAL_TREE_Y_CHANNEL);
//                    RobotInfo[] bots = rc.senseNearbyRobots();
//                    for (RobotInfo b : bots) {
//                        if (b.getTeam() != rc.getTeam() && rc.canStrike()) {
//                            rc.strike();
//                            Direction chase = rc.getLocation().directionTo(b.getLocation());
//                            tryMove(chase);
//                        }
//                    }
//                   rc.chop(new MapLocation(x,y));
////                    else
////                    tryMove(randomDirection());
////                    if(!rc.hasMoved()) {
////                        if (rc.canMove(rc.getLocation().directionTo(archonLoc).opposite()))
////                            tryMove(rc.getLocation().directionTo(archonLoc).opposite());
////                        else if (rc.canMove(rc.getLocation().directionTo(archonLoc).rotateRightDegrees(90))) {
////                            tryMove(rc.getLocation().directionTo(archonLoc).rotateRightDegrees(90));
////                        } else //break;
////                        {
////                            tryMove(rc.getLocation().directionTo(archonLoc).rotateRightDegrees(90));
////                        }
////                    }
////                    else{
////                    // Move Randomly
////                   tryMove(myLocation.directionTo(enemy1));
////
////                }
////                tryMove(randomDirection());
//                // Clock.yield() makes the robot wait until the next turn, then it will perform this loop again
//                Clock.yield();
//
//            } catch (Exception e) {
//                System.out.println("Lumberjack Exception");
//                e.printStackTrace();
//            }
//        }
    }
}

/**
 * Created by nisar on 11-01-2017.
 */
//public class LumberjackBot {
//    static void runLumberjack() throws GameActionException {
//        System.out.println("I'm a lumberjack!");
//        Team enemy = rc.getTeam().opponent();
//int i=0;
//        // The code you want your robot to perform every round should be in this loop
//        while (true) {
//
//            // Try/catch blocks stop unhandled exceptions, which cause your robot to explode
//            try {
//
//                // See if there are any enemy robots within striking range (distance 1 from lumberjack's radius)
//                RobotInfo[] robots = rc.senseNearbyRobots(RobotType.LUMBERJACK.bodyRadius+ GameConstants.LUMBERJACK_STRIKE_RADIUS, enemy);
//                TreeInfo[] trees=rc.senseNearbyTrees();
//                int xPos = rc.readBroadcast(10);
//                int yPos = rc.readBroadcast(11);
//                int xPosArchon=rc.readBroadcast(0);
//                int yPosArchon=rc.readBroadcast(1);
//                MapLocation archonLoc=new MapLocation(xPosArchon,yPosArchon);
//                MapLocation enemy1 = new MapLocation(xPos, yPos);
//                MapLocation myLocation = rc.getLocation();
//                if (robots.length > 0 && !rc.hasAttacked()) {
//                    // Use strike() to hit all nearby robots!
//                    rc.strike();
//                } else {
//                    // No close robots, so search for robots within sight radius
//                    robots = rc.senseNearbyRobots(10, enemy);
//
//                    // If there is a robot, move towards it
//                    if (robots.length > 0) {
//
//                        MapLocation enemyLocation = robots[0].getLocation();
//                        Direction toEnemy = myLocation.directionTo(enemyLocation);
//                        rc.broadcast(10, (int) robots[0].getLocation().x);
//                        rc.broadcast(11, (int) robots[0].getLocation().y);
//                        tryMove(myLocation.directionTo(enemyLocation));
//                    }
//                    else if(trees.length>0)
//                    {   i=0;
//                       // while(i<trees.length) {
//                            if (trees[i].getTeam() != rc.getTeam() && rc.canChop(trees[i].location))
//                            {   trees[0].getContainedBullets();
//                                rc.chop(trees[i].location); }
//                            else
////                                {//rc.shake(trees[i].location);
////                                 i++;}
//                            //tryMove(rc.getLocation().directionTo(trees[0].location));
//                      //  }
//                    }
////                        else if(xPos!=0 && yPos!=0){
////                            tryMove(rc.getLocation().directionTo(enemy1));
////                        }
//                    else
//                        tryMove(rc.getLocation().directionTo(archonLoc).opposite());
//                }
////                    else{
////                    // Move Randomly
////                   tryMove(myLocation.directionTo(enemy1));
////
////                }
//
//                // Clock.yield() makes the robot wait until the next turn, then it will perform this loop again
//                Clock.yield();
//
//            } catch (Exception e) {
//                System.out.println("Lumberjack Exception");
//                e.printStackTrace();
//            }
//        }
//    }
//}
