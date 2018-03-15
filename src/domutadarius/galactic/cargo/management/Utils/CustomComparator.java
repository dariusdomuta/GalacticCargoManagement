package domutadarius.galactic.cargo.management.Utils;

import domutadarius.galactic.cargo.management.Pojos.Result;

import java.util.Comparator;

public class CustomComparator implements Comparator<Result> {

    public CustomComparator(){

    }

    public int compare(Result result1, Result result2) {
        if (result1.getDeliveryTime() - result2.getDeliveryTime() < 0) {
            return -1;
        } else if (result1.getDeliveryTime() - result2.getDeliveryTime() > 0){
            return 1;
        } else {
            return 0;
        }
    }
}
