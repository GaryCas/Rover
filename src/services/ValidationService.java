package services;

import com.company.PlateuMap;
import com.company.Rover;
import com.company.exceptions.CollisionException;

/**
 * Created by rd019985 on 21/02/2017.
 */
public class ValidationService {

    // move to a validation service
    public static void validateAndAddyCoordinate(Rover rover, int yCoordinate) throws IndexOutOfBoundsException  {
        if(yCoordinate >= 0 && yCoordinate < PlateuMap.topRightY){
            rover.setyCoordinate(yCoordinate);
        }  else {
            throw new IndexOutOfBoundsException("xCoordinate must be between 0 and " + PlateuMap.topRightY + "/nBut is " + yCoordinate);
        }
    }

    public static void validateAndAddxCoordinate(Rover rover, int xCoordinate) throws IndexOutOfBoundsException {
        if(xCoordinate >= 0 && xCoordinate < PlateuMap.topRightX){
            rover.setxCoordinate(xCoordinate);
        } else {
            throw new IndexOutOfBoundsException("xCoordinate must be between 0 and " + PlateuMap.topRightX + "/nBut is " + xCoordinate);
        }
    }

    // custom exception here?
    public static void validateAndAddOrientation(Rover rover, String orientation) throws Exception {
        if( orientation.equalsIgnoreCase("N") ||
                orientation.equalsIgnoreCase("S") ||
                orientation.equalsIgnoreCase("W") ||
                orientation.equalsIgnoreCase("E")){
            rover.setOrientation(orientation);
        } else {
            throw new Exception("there is no such orientation as " + orientation);
        }
    }

    public static void validateNewCoordinates(Rover thisRover, int xCoordinate, int yCoordinate) throws IndexOutOfBoundsException, CollisionException {
        for (Rover rover : PlateuMap.rovers) {
            if(rover.getxCoordinate() == xCoordinate && rover.getyCoordinate() == yCoordinate){
                throw new CollisionException("Collision detected with rover " + rover.getId());
            }
        }

        validateAndAddxCoordinate(thisRover, xCoordinate);
        validateAndAddyCoordinate(thisRover, yCoordinate);
    }
}
