import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

class Contract {
    private String contractName;
    private String customerName;
    private String planName;
    private Date startDate;
    private Date endDate;
    private int discountPercentage;

    static final String inputTag = "CONTRACT";

    Contract(HashMap<String, ArrayList<Tag>> tags) throws ParseException {
        contractName = tags.get("CONTRACT_NAME").get(0).getValue();
        // get the "CONTRACT_NAME" , the first element in th list and Value from tags let them be 'contractName'
        customerName = tags.get("CUSTOMER_NAME").get(0).getValue();
        // get the "CUSTOMER_NAME" , the first element in th list and Value from tags let them be 'customerName'
        planName = tags.get("PLAN_NAME").get(0).getValue();
        // get the "PLAN_NAME" , the first element in th list and Value from tags let them be 'planName'
        startDate = Utils.convertDate(tags.get("START_DATE").get(0).getValue());
        // get the "START_DATE" , the first element in th list and Value from tags let them be 'startDate'
        endDate = Utils.convertDate(tags.get("END_DATE").get(0).getValue());
        // get the "END_DATE" , the first element in th list and Value from tags let them be 'endDate'
        discountPercentage = Integer.parseInt(tags.get("DISCOUNT_PERCENTAGE").get(0).getValue());
        // get the "DISCOUNT_PERCENTAGE" , the first element in th list and Value from tags let them be 'discountPercentage'

    }

    public String getCustomerName() {
        return customerName;
    }

    public String getPlanName() {
        return planName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public int getDiscountPercentage() {
        return discountPercentage;
    }

    public static String getInputTag() {
        return inputTag;
    }

    public String getContractName() {
        return contractName;
    }
    //the seven methods above return the defined elements in this class
}
