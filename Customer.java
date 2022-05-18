import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

class Customer {
    private String name;
    private Date dateOfBirth;
    private long income;

    static final String inputTag = "CUSTOMER";

    Customer(HashMap<String, ArrayList<Tag>> tags) throws ParseException {
        name = tags.get("NAME").get(0).getValue();
        // get the "NAME" , the first element in th list and Value from tags let them be 'name'
        dateOfBirth = Utils.convertDate(tags.get("DATE_OF_BIRTH").get(0).getValue());
        // get the "DATE_OF_BIRTH" , the first element in th list and Value from tags let them be 'dateOfBirth'
        income = Long.parseLong(tags.get("INCOME").get(0).getValue());
        // get the "INCOME" , the first element in th list and Value from tags let them be 'income'
    }

    public String getName() {
        return name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public long getIncome() {
        return income;
    }

    public static String getInputTag() {
        return inputTag;
    }
    //the four methods above return the defined elements in this class
}
