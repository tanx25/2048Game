import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

class Claim {
    private String contractName;
    private long amount;
    private Date date;
    private boolean successful;

    static final String inputTag = "CLAIM";

    Claim(HashMap<String, ArrayList<Tag>> tags) throws ParseException {
        // Claim includes hashmap,arraylist
        // define a hashmap with the key value is the string
        // define an arraylist with tag and right shift by 'tags'
        // throws a custom exception
        contractName = tags.get("CONTRACT_NAME").get(0).getValue();
        // get the "CONTRACT_NAME" , the first element in th list and Value from tags let them be 'contractName'
        date = Utils.convertDate(tags.get("DATE").get(0).getValue());
        // get the "DATE" , the first element in th list and Value from tags let them be 'date'
        amount = Long.parseLong(tags.get("AMOUNT").get(0).getValue());
        // get the "AMOUNT" , the first element in th list and Value from tags let them be 'amount'
    }

    public String getContractName() {
        return contractName;
    }

    public long getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }

    public boolean wasSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }
    //the five methods above return the defined elements in this class
}
