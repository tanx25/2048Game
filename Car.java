import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

class Car extends Insurable {

    private String make;
    private String model;
    private Date purchaseDate;
    private long mileage;// define four assessment criteria of the car

    static final String inputTag = "CAR";

    Car(HashMap<String, ArrayList<Tag>> tags) throws ParseException {
        // car includes hashmap,arraylist
        // define a hashmap with the key value is the string
        // define an arraylist with tag and right shift by tags
        // throws a custom exception
        super(tags);
        make = tags.get("MAKE").get(0).getValue();
        // get the 'MAKE' , the first element in th list and Value from tags let them be 'make'
        model = tags.get("MODEL").get(0).getValue();
        // get the 'MODEL' , the first element in th list and Value from tags let them be 'model'
        purchaseDate = Utils.convertDate(tags.get("PURCHASE_DATE").get(0).getValue());
        // get the "PURCHASE_DATE" , the first element in th list and Value from tags let them be 'purchaseDate'
        mileage = Long.parseLong(tags.get("MILEAGE").get(0).getValue());
        // get the "MILEAGE" , the first element in th list and Value from tags let them be 'mileage'
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public long getMileage() {
        return mileage;
    }

    public static String getInputTag() {
        return inputTag;
    }
    //the six methods above return the defined elements in this class
}

