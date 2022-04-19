//Jimmy Zhang CSE 214 R02 ID: 112844431

import java.util.Comparator;
/**
 * this class implements comparator and compares the wind speed values
 */
public class WindSpeedComparator implements Comparator {
    /**
     *the function below takes in two object parameters, converts it to type storm and compares windspeed values
     */
    @Override
    public int compare(Object o1, Object o2) {
        Storm left = (Storm) o1;
        Storm right = (Storm) o2;
        if(left.getWindSpeed() < right.getWindSpeed()){
            return -1;
        }
        else if(left.getWindSpeed()== right.getWindSpeed()){
            return 0;
        }else{
            return 1;
        }
    }
}
