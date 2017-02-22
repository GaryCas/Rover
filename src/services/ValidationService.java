package services;

import com.company.PlateuMap;
import com.company.Rover;
import com.company.exceptions.CollisionException;
import com.company.exceptions.OrientationException;

/**
 * Created by rd019985 on 21/02/2017.
 */
public class ValidationService {

    /**
     *  bounds check
     * @param rover
     * @param yCoordinate
     * @throws IndexOutOfBoundsException
     */
    public static void validateAndAddyCoordinate(Rover rover, int yCoordinate) throws IndexOutOfBoundsException  {
        if(yCoordinate >= 0 && yCoordinate < PlateuMap.topRightY){
            rover.setyCoordinate(yCoordinate);
        }  else {
            throw new IndexOutOfBoundsException("xCoordinate must be between 0 and " + PlateuMap.topRightY + "/nBut is " + yCoordinate);
        }
    }

    /**
     *  bounds check
     *
     * @param rover
     * @param xCoordinate
     * @throws IndexOutOfBoundsException
     */
    public static void validateAndAddxCoordinate(Rover rover, int xCoordinate) throws IndexOutOfBoundsException {
        if(xCoordinate >= 0 && xCoordinate < PlateuMap.topRightX){
            rover.setxCoordinate(xCoordinate);
        } else {
            throw new IndexOutOfBoundsException("xCoordinate must be between 0 and " + PlateuMap.topRightX + "/nBut is " + xCoordinate);
        }
    }

    /**
     *
     * Validates that the orientation is of the correct value
     *
     * @param rover rover whose value is to be set
     * @param orientation String orientation
     * @throws Exception
     */
    public static void validateAndAddOrientation(Rover rover, String orientation) throws OrientationException {
        if( orientation.equalsIgnoreCase("N") ||
                orientation.equalsIgnoreCase("S") ||
                orientation.equalsIgnoreCase("W") ||
                orientation.equalsIgnoreCase("E")){
            rover.setOrientation(orientation);
        } else {
            throw new OrientationException("there is no such orientation as " + orientation);
        }
    }

    /**
     * This method determines whether the spot that a rover is trying to move to or initialise to is free and
     * within the bound of the plateu. Future iterations will throw a new custom exception for Rover Initialisation
     * So that it can be handled seperately and prevent having to create non-sensible fields when catching the
     * Collision exception from the Rover Constructor
     *
     * @param thisRover the moving or initialising rover
     * @param xCoordinate the xCoordinate the rover is moving to or being initialise on
     * @param yCoordinate the yCoordinate the rover is moving to or being initialise on
     * @throws IndexOutOfBoundsException thrown when either of the coordinates are out of the plateus bounds
     * @throws CollisionException thrown when there is a Collision with another rover
     */
    public static void validateNewCoordinates(Rover thisRover, int xCoordinate, int yCoordinate) throws IndexOutOfBoundsException, CollisionException {
        for (Rover rover : PlateuMap.rovers) {
            if(rover.getxCoordinate() == xCoordinate
                    && rover.getyCoordinate() == yCoordinate){

                thisRover.setCollidedWithRover(rover);
                throw new CollisionException("Collision detected with rover " + rover.getId());
            }
        }

        validateAndAddxCoordinate(thisRover, xCoordinate);
        validateAndAddyCoordinate(thisRover, yCoordinate);
    }
}
