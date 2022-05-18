import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

class BlockCommand extends Command {// subclass of Command
    private String blockType;
    private HashMap<String, ArrayList<Tag>> tags = new HashMap<>();// another hashmap

    BlockCommand(String blockType) {
        this.blockType = blockType;
    }//define and use 'blockType'

    void addTag(Tag tag) {  // method to add a tag
        if (!tags.containsKey(tag.getName())) { // add a tag if it is not in tags
            tags.put(tag.getName(), new ArrayList<Tag> ());
        }
        tags.get(tag.getName()).add(tag);
    }

    String getBlockType() {
        return blockType;
    }

    @Override //rewrite a method
    void run(Database database) throws ParseException {
        /* check the blockType equals to which inputTag
            (among Customer,Home, Car,Claim,Contact,HomePlan and CarPlan)
            if they are the same , insert the blockType */
        if (blockType.equals(Customer.inputTag)){
            database.insertCustomer(new Customer(tags));
        } if (blockType.equals(Home.inputTag)) {
            database.insertHome(new Home(tags));
        } if (blockType.equals(Car.inputTag)) {
            database.insertCar(new Car(tags));
        } if (blockType.equals(Claim.inputTag)) {
            Claim claim = new Claim(tags);
            database.insertClaim(claim);
            if (processClaim(claim, database)) {
                claim.setSuccessful(true);
                System.out.println("Claim on " + Utils.formattedDate(claim.getDate())
                        + " for contract " + claim.getContractName() + " was successful.");
            } else {
                claim.setSuccessful(false);
                System.out.println("Claim on " + Utils.formattedDate(claim.getDate())
                        + " for contract " + claim.getContractName() + " was not successful.");
            }
        } if (blockType.equals(Contract.inputTag)) {
            database.insertContract(new Contract(tags));
        } if (blockType.equals(HomePlan.inputTag)) {
            database.insertPlan(new HomePlan(tags));
        } if (blockType.equals(CarPlan.inputTag)) {
            database.insertPlan(new CarPlan(tags));
        }
    }

    private boolean processClaim(Claim claim, Database database) {
        Contract contract = database.getContract(claim.getContractName());
        Customer customer = database.getCustomer(contract.getCustomerName());
        Plan plan = database.getPlan(contract.getPlanName());
        Insurable insurable = plan.getInsuredItem(customer, database);

        // If the claimed amount is higher than covered by the plan.
        if (plan.getMaxCoveragePerClaim() < claim.getAmount())
            return false;

        // If the claim date is not in the contract period.
        if (claim.getDate().after(contract.getEndDate()) || claim.getDate().before(contract.getStartDate()))
            return false;

        // If the customer was not eligible.
        if (!plan.isEligible(customer, claim.getDate()))
            return false;

        return plan.isEligible(insurable, claim.getDate());
    }
}
