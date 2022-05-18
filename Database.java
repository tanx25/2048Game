import java.util.ArrayList;

class Database {
    private ArrayList<Customer> customers = new ArrayList<>();
    private ArrayList<Home> homes = new ArrayList<>();
    private ArrayList<Car> cars = new ArrayList<>();
    private ArrayList<Plan> plans = new ArrayList<>();
    private ArrayList<Contract> contracts = new ArrayList<>();
    private ArrayList<Claim> claims = new ArrayList<>();
    // create arraylists for all the elements
    void insertHome(Home home) {
        homes.add(home);
    }

    void insertCar(Car car) {
        cars.add(car);
    }

    void insertCustomer(Customer customer) {
        customers.add(customer);
    }

    void insertPlan(Plan plan) {
        plans.add(plan);
    }

    void insertClaim(Claim claim) {
        claims.add(claim);
    }

    void insertContract(Contract contract) {
        contracts.add(contract);
    }

    // insert methods for adding each elements

    Plan getPlan(String name) {
        for (Plan plan : plans) {
            if (plan.name.equals(name))
                return plan;
        }
        return null;
    }

    Customer getCustomer(String name) {
        for (Customer customer : customers) {
            if (customer.getName().equals(name))
                return customer;
        }
        return null;
    }

    Contract getContract(String name) {
        for (Contract contract : contracts) {
            if (contract.getContractName().equals(name))
                return contract;
        }
        return null;
    }

    /**
     * There is at most one home owned by each person.
     */
    Home getHome(String ownnerName) {
        for (Home home : homes) {
            if (home.getOwnerName().equals(ownnerName))
                return home;
        }
        return null;
    }

    /**
     * There is at most one car owned by each person.
     */
    Car getCar(String ownnerName) {
        for (Car car : cars) {
            if (car.getOwnerName().equals(ownnerName))
                return car;
        }
        return null;
    }
    /*'get' methods for each elements, loop for each element , the maximum of home and  car for each person is one,
    if the information existed return it, otherwise return null */
    long totalClaimAmountByCustomer(String customerName) {
        long totalClaimed = 0;
        for (Claim claim : claims) {
            if (getContract(claim.getContractName()).getCustomerName().equals(customerName))
                totalClaimed += claim.getAmount();
        }
        return totalClaimed;
        //loop claim and calculate total claim amount by customer and return total claim
    }

    long totalReceivedAmountByCustomer(String customerName) {
        long totalReceived = 0;
        for (Claim claim : claims) {
            //System.out.println(claim.getContractName());
            Contract contract = getContract(claim.getContractName());
            if (contract.getCustomerName().equals(customerName)) {
                if (claim.wasSuccessful()) {
                    long deductible = getPlan(contract.getPlanName()).getDeductible();
                    totalReceived += Math.max(0, claim.getAmount() - deductible);
                }
            }
        }
        return totalReceived;
        //loop claim and calculate total received amount by customer and return total claim
    }


    long numberOfCutomersUnderPlan(String planName){
        long totalNumber = 0;
        for (Contract contract:contracts){

            if (contract.getPlanName().equals(planName)){
                totalNumber++;
            }
        }
        return totalNumber;
        //loop contract and calculate total number of customer under plan and return total number
    }

    long totalPayedToCutomers(String planName){
        long totalPayed = 0;
        for (Claim claim : claims){
            Contract contract = getContract(claim.getContractName());
            //System.out.println(contract.getPlanName());
            if (contract.getPlanName().equals(planName)) {
                if (claim.wasSuccessful()) {
                    long deductible = getPlan(contract.getPlanName()).getDeductible();
                    totalPayed += Math.max(0, claim.getAmount() - deductible);
                }
            }
        }
        return totalPayed;
    }

       //loop claim and calculate total payed to customer under plan and return total payed
}
