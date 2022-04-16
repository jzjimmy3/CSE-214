import java.util.Comparator;

public class WindSpeedComparator implements Comparator {
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
