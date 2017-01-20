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
				Direction dir = randomDirection();
				int prev = rc.readBroadcast(GARDENER_CHANNEL);
                int scoutCount = rc.readBroadcast(SCOUT_CHANNEL);
			  int  prevNumGard = rc.readBroadcast(LUMBERJACK_CHANNEL);
                int prevScoutNum=rc.readBroadcast(SCOUT_CHANNEL);
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

          //  tryMove(rc.getLocation().directionTo(rc.getInitialArchonLocations(rc.getTeam().opponent())[0]),60,6);
          //  rc.setIndicatorLine(rc.getLocation(),rc.getInitialArchonLocations(rc.getTeam().opponent())[0],rc.getID(),2,3);

//				if (rc.getLocation().isWithinDistance(archonLoc, 6)) //&& rc.getLocation().isWithinDistance(rc.senseNearbyRobots(1.5f,rc.getTeam())[0].location,1.5f))
//				{  while (rc.getLocation().isWithinDistance(archonLoc, 6))// && rc.getLocation().isWithinDistance(rc.senseNearbyRobots(1.5f,rc.getTeam())[0].location,1.5f))
//				{  if (!rc.hasMoved()) {
//							tryMove(rc.getLocation().directionTo(rc.getInitialArchonLocations(rc.getTeam().opponent())[0]), 15, 24);
//				}
//					}
//				}



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



                 //   while (prevScoutNum <= SCOUT_MAX) {
//                        if (rc.canBuildRobot(rc.getType().SCOUT, dir)) {
//                            rc.broadcast(SCOUT_CHANNEL, prevScoutNum + 1);
//                            rc.buildRobot(rc.getType().SCOUT, dir);
//                            dir.rotateLeftDegrees(60);
//                            prevScoutNum=rc.readBroadcast(SCOUT_CHANNEL);
//                           // break;
//                            //Clock.yield();
//                        }
//                        else dir.rotateLeftDegrees(60);
//               //   }




               //     while (prevNumGard <= LUMBERJACK_MAX) {
                        if (rc.canBuildRobot(RobotType.LUMBERJACK, dir)) {
                            rc.broadcast(LUMBERJACK_CHANNEL, prevNumGard + 1);
                            rc.buildRobot(RobotType.LUMBERJACK, dir);
                            dir.rotateLeftDegrees(60);
                            prevNumGard=rc.readBroadcast(LUMBERJACK_CHANNEL);
                            //Clock.yield();
							//break;
                        }
                        else
                        dir.rotateLeftDegrees(60);
               //     }




//                while (prevNumSol <= SOLDIER_MAX) {
//                    if (rc.canBuildRobot(RobotType.SOLDIER, dir)) {
//                        rc.broadcast(SOLDIER_CHANNEL, prevNumSol + 1);
//                        prevNumSol=rc.readBroadcast(SOLDIER_CHANNEL);
//                        rc.buildRobot(RobotType.SOLDIER, dir);
//                        dir.rotateLeftDegrees(30);
//                        Clock.yield();
//                    }
//                    dir.rotateLeftDegrees(30);
//                }




//				for (int j = 0; j < 180; j++) {
//			if (rc.canPlantTree(dir)) {
//						rc.plantTree(dir);
//					   // Clock.yield();
//					}
//					dir.rotateLeftDegrees(2);
//					j++;
//				}
//					if (trees.length > 0) {
//						while (i < trees.length) {
//							if (rc.canShake()) rc.shake(trees[i].ID);
//							if (trees[i].getTeam()==rc.getTeam() && rc.canWater(trees[i].ID)) rc.water(trees[i].ID);
//							Clock.yield();
//							i++;
//						}
//					}


//                    }
			    Clock.yield();
			} catch(Exception e) {
				System.out.println("Gardener Exception");
				e.printStackTrace();
			}
		}
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
