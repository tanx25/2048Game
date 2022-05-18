import java.util.ArrayList;
import java.util.HashMap;

class Insurable {
    protected String ownerName;// 'protected' can only be used by 'Package' and subclasses
    protected long value;

    Insurable(HashMap<String, ArrayList<Tag>> tags) {
        ownerName = tags.get("OWNER_NAME").get(0).getValue();//call the owner-name in tags
        value = Long.parseLong(tags.get("VALUE").get(0).getValue());//call the values in tags
    }

    public String getOwnerName() {
        return ownerName;
    }//a method to return the ownerName

    public long getValue() {
        return value;
    }//a method to return the value
}
