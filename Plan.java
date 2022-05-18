import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

abstract class Plan {
    String name;
    long premium;
    long maxCoveragePerClaim;
    long deductible;
    RangeCriterion customerAgeCriterion = new RangeCriterion();
    RangeCriterion customerIncomeCriterion = new RangeCriterion();
    //define elements in Plan

    Plan(HashMap<String, ArrayList<Tag>> tags) {
        name = tags.get("NAME").get(0).getValue();
        // get the "NAME" , the first element in th list and Value from tags let them be 'name'
        premium = Integer.parseInt(tags.get("PREMIUM").get(0).getValue());
        // get the "PREMIUM" , the first element in th list and Value from tags let them be 'premium'
        maxCoveragePerClaim = Integer.parseInt(tags.get("MAX_COVERAGE_PER_CLAIM").get(0).getValue());
        // get the "MAX_COVERAGE_PER_CLAIM", the first element in th list and Value from tags let them be 'maxCoveragePerClaim'
        deductible = Integer.parseInt(tags.get("DEDUCTIBLE").get(0).getValue());
        // get the "DEDUCTIBLE" , the first element in th list and Value from tags let them be 'deductible'

        if (tags.get("CUSTOMER.AGE") != null) {
            for (Tag tag: tags.get("CUSTOMER.AGE")) {
                customerAgeCriterion.addCriterion(tag);
            }
            //loop tag and add age into AgeCriterion
        }
        if (tags.get("CUSTOMER.INCOME") != null) {
            for (Tag tag: tags.get("CUSTOMER.INCOME")) {
                customerIncomeCriterion.addCriterion(tag);
            }
            //loop tag and add income into IncomeCriterion
        }

    }

    abstract boolean isEligible(Insurable insurable, Date date);

    abstract Insurable getInsuredItem(Customer customer, Database database);

    boolean isEligible(Customer customer, Date currentDate) {
        // Extracting the approximate age of the customer (just based on the calendar years)
        LocalDate localCurrentDate = currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localBirthDate = customer.getDateOfBirth().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        long age = localCurrentDate.getYear() - localBirthDate.getYear();
        // Checking if the age is in the range.
        if (!customerAgeCriterion.isInRange(age))
            return false;
        // Checking if the income is in the range.
        return customerIncomeCriterion.isInRange(customer.getIncome());
    }

    String getName() {
        return name;
    }

    long getPremium() {
        return premium;
    }

    long getMaxCoveragePerClaim() {
        return maxCoveragePerClaim;
    }

    long getDeductible() {
        return deductible;
    }
}
