import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

class Home extends Insurable {
    private String postalCode;
    private Date buildDate;

    static final String inputTag = "HOME";

    Home(HashMap<String, ArrayList<Tag>> tags) throws ParseException {
        super(tags);
        //use super to call the sub class
        postalCode = tags.get("POSTAL_CODE").get(0).getValue();
        buildDate = Utils.convertDate(tags.get("BUILD_DATE").get(0).getValue());
        // define postalCode and buildDate
    }

    public String getPostalCode() {
        return postalCode;
    }

    public Date getBuildDate() {
        return buildDate;
    }

    public static String getInputTag() {
        return inputTag;
    }
    // 'get' method and return postalCode , buildDate and inputTag
}
