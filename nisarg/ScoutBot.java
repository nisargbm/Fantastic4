package nisarg;

import battlecode.common.*;

import java.util.*;

import static nisarg.ArchonBot.*;
import static nisarg.RobotPlayer.*;

/**
 * Created by nisar on 17-01-2017.
 */
public class ScoutBot {
    public static void runScout() {
        System.out.println("I'm a scout!");
       TreeInfo[] trees;
       ArrayList<TreeInfo> treeInfos =new ArrayList<TreeInfo>();
        int i=0;
        // The code you want your robot to perform every round should be in this loop
        while (true) {
            // Try/catch blocks stop unhandled exceptions, which cause your robot to explode
            try {
//                rc.move(randomDirection());
                trees=rc.senseNearbyTrees();
                if (rc.getHealth() < RobotType.SCOUT.maxHealth / 10 &&
                        rc.senseNearbyRobots(-1, rc.getTeam().opponent()).length > 0)
                    rc.broadcast(SCOUT_CHANNEL, rc.readBroadcast(SCOUT_CHANNEL) - 1);
                int xPos = rc.readBroadcast(0);
                int yPos = rc.readBroadcast(1);
                MapLocation archonLoc = new MapLocation(xPos, yPos);
                int xPosEnemy = rc.readBroadcast(10);
                int yPosEnemy = rc.readBroadcast(11);
                int scoutCount = rc.readBroadcast(SCOUT_CHANNEL);
               // trees=rc.senseNearbyTrees(-1,rc.getTeam().opponent());
                MapLocation enemy = new MapLocation(xPosEnemy, yPosEnemy);
                RobotInfo[] bots = rc.senseNearbyRobots(-1, rc.getTeam().opponent());
                dodge();
//                if(scoutCount%4==0 && bots.length==0){
//                    tryMove(rc.getLocation().directionTo(rc.getInitialArchonLocations(rc.getTeam().opponent())[0]));
//                }
//                else{
                    trees = rc.senseNearbyTrees();
                    if(trees.length>0)
                    {
                        for(TreeInfo tree:trees){
                            rc.setIndicatorDot(tree.getLocation(),1,2,3);
                            if(!findTree(tree,treeInfos)) {
                                tryMove(rc.getLocation().directionTo(tree.getLocation()),60,6);
                                treeInfos.add(tree);
                                if (rc.canShake(tree.ID)) {
                                    rc.shake(tree.ID);
                                }
                            }
                            Clock.yield();
                        }
                    }
//                    else if(rc.canMove(Direction.getNorth()))
//                        rc.move(Direction.getNorth());
//                    else if(rc.canMove(Direction.getEast()))  rc.move(Direction.getEast());
//                    else if(rc.canMove(Direction.getSouth()))  rc.move(Direction.getSouth());
//                    else if(rc.canMove(Direction.getWest()))  rc.move(Direction.getWest());
                    else tryMove(randomDirection(),60,6);
//                    if (trees.length > 0) {
//                        for (TreeInfo tree : trees) {
//                            // bots = rc.senseNearbyRobots(-1, rc.getTeam().opponent());
//                           // if (bots.length == 0) {
//                                if (/*tree.location != rc.getLocation() &&*/ tree.getContainedRobot() != null) {
//                                    tryMove(rc.getLocation().directionTo(tree.location), 60, 6);
//                                    rc.shake(tree.ID);
//                                }
                           // }
//                            else
//                                break;
//                        }
//                    }
//                    else tryMove(randomDirection());
 //               }
//                bots=rc.senseNearbyRobots(-1, rc.getTeam().opponent());
//                if(bots.length>0)
//                {
//                    for (RobotInfo b : bots) {
//                        if (b.getTeam() != rc.getTeam() && rc.canFireSingleShot()) {
//
//                            Direction chase = rc.getLocation().directionTo(b.getLocation());
//                            rc.fireSingleShot(chase);
//                            rc.broadcast(10, (int) bots[0].getLocation().x);
//                            rc.broadcast(11, (int) bots[0].getLocation().y);
//                            tryMove(chase,60,6);
//                            //break;
//                        }
//                    }
//                }
////                else if(trees.length>0){
////                    trees = rc.senseNearbyTrees(-1, Team.NEUTRAL);
////                    if (trees.length > 0) {
////                        for (TreeInfo tree : trees) {
////                            // bots = rc.senseNearbyRobots(-1, rc.getTeam().opponent());
////                            if (bots.length == 0) {
////                                if (tree.location != rc.getLocation() && tree.getContainedRobot() != null) {
////                                    tryMove(rc.getLocation().directionTo(tree.location), 60, 6);
////                                    rc.shake(tree.ID);
////                                }
////                            } else
////                                break;
////                        }
////                    }
////                }
//                        if (rc.getHealth() < RobotType.SCOUT.maxHealth / 10)
//                            scoutCount--;
                        // Clock.yield() makes the robot wait until the next turn, then it will perform this loop again
                        Clock.yield();
            } catch (Exception e) {
                System.out.println("Scout Exception");
                e.printStackTrace();
            }
        }
    }

    private static boolean findTree(TreeInfo tree, ArrayList<TreeInfo> treeInfos) {
        if(treeInfos.size()==0)return false;
        else {
            for (int i = 0; i < treeInfos.size(); i++) {
                if (tree.getID() == treeInfos.get(i).getID()) return true;
            }
        }
        return false;
    }

}








//package nisarg;
//
//import battlecode.common.*;
//
//import static nisarg.ArchonBot.*;
//import static nisarg.RobotPlayer.randomDirection;
//import static nisarg.RobotPlayer.rc;
//import static nisarg.RobotPlayer.tryMove;
//
///**
// * Created by nisar on 17-01-2017.
// */
//public class ScoutBot {
//
//    public static void runScout() {
//        System.out.println("I'm a scout!");
//
//       // Team enemy = rc.getTeam().opponent();
//        int i=0;
//        // The code you want your robot to perform every round should be in this loop
//        while (true) {
//
//            // Try/catch blocks stop unhandled exceptions, which cause your robot to explode
//            try {
////                rc.move(randomDirection());
//                TreeInfo[] trees=rc.senseNearbyTrees(-1,Team.NEUTRAL);
//                if(rc.getHealth() < RobotType.SCOUT.maxHealth/10 &&
//                        rc.senseNearbyRobots(-1,rc.getTeam().opponent()).length > 0)
//                    rc.broadcast(SCOUT_CHANNEL,rc.readBroadcast(SCOUT_CHANNEL)-1);
//                int prevScoutNum=rc.readBroadcast(SCOUT_CHANNEL);
//                int xPos = rc.readBroadcast(0);
//                int yPos = rc.readBroadcast(1);
//                MapLocation archonLoc = new MapLocation(xPos, yPos);
//               int xPosEnemy=rc.readBroadcast(10);
//               int yPosEnemy=rc.readBroadcast(11);
//               MapLocation enemy=new MapLocation(xPosEnemy,yPosEnemy);
//                RobotInfo[] bots = rc.senseNearbyRobots(-1,rc.getTeam().opponent());
//                if(trees.length>0)
//                {
//                    rc.broadcast(NEUTRAL_TREE_X_CHANNEL,(int)trees[0].getLocation().x);
//                    rc.broadcast(NEUTRAL_TREE_Y_CHANNEL,(int)trees[0].getLocation().y);
//                }
//                if(bots.length==0) {
//                    if(!rc.hasMoved()) {
////                        if (xPosEnemy != 0 && yPosEnemy != 0 && rc.canMove(enemy))
////                            rc.move(rc.getLocation().directionTo(enemy));
////                        else
//                            if (prevScoutNum==2 && rc.canMove(rc.getInitialArchonLocations(rc.getTeam().opponent())[0]))
//                                tryMove(rc.getLocation().directionTo(rc.getInitialArchonLocations(rc.getTeam().opponent())[0]));
//                            else if (rc.canMove(archonLoc) && !rc.getLocation().isWithinDistance(archonLoc, 25f))
//                                rc.move(rc.getLocation().directionTo(archonLoc));
//                            else
//                                rc.move(randomDirection());
//                    }
//                }
//                else
//                {
//
//                    for (RobotInfo b : bots) {
//                        if (b.getTeam() != rc.getTeam() && rc.canFireSingleShot()) {
//
//                            Direction chase = rc.getLocation().directionTo(b.getLocation());
//                            rc.fireSingleShot(chase);
//                            rc.broadcast(10,(int)bots[0].getLocation().x);
//                            rc.broadcast(11,(int)bots[0].getLocation().y);
//                           // tryMove(chase);
//                            break;
//                        }
//                    }
//                    //else if(rc.canMove(rc.getLocation().directionTo(archonLoc).rotateLeftDegrees(90))){
//                       // rc.move(rc.getLocation().directionTo(archonLoc).rotateLeftDegrees(90));
//                    }
//                   //else rc.move(rc.getLocation().directionTo(archonLoc).rotateRightDegrees(90));
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
