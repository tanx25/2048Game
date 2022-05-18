import java.text.ParseException;

class PrintCommand extends Command {
    private String entityType;
    private String queryType;
    private String queryValue;


    PrintCommand(String[] tokens) {
        super();
        entityType = tokens[1];
        queryType = tokens[2];
        queryValue = tokens[3];
        //definition and give elements value
    }

    @Override
    void run(Database database) {
        if (entityType.equals("CUSTOMER"))
            runPrintCustomer(database);
        else if (entityType.equals("PLAN"))
            runPrintPlan(database);
        else {
            throw new BadCommandException("Bad print command.");
        }
        //print different things if the entityType equals to "CUSTOMER" or "PLAN"
    }

    private void runPrintCustomer(Database database) {
        if (queryType.equals("TOTAL_CLAIMED")) {
            System.out.println("Total amount claimed by " + database.getCustomer(queryValue).getName() +
                    " is " + database.totalClaimAmountByCustomer(queryValue));
        } else if (queryType.equals("TOTAL_RECEIVED")) {
            System.out.println("Total amount received by " + database.getCustomer(queryValue).getName() +
                    " is " + database.totalReceivedAmountByCustomer(queryValue));
        } else {
            throw new BadCommandException("Invalid PRINT CUSTOMER command.");
        }
        //print different things if the queryType equals to "TOTAL_CLAIMED" or "TOTAL_RECEIVED"
    }

    //TODO
    private void runPrintPlan(Database database) {
        if (queryType.equals("NUM_CUSTOMERS")){
            System.out.println("Number of customers under "+ queryValue +
                    " is " + database.numberOfCutomersUnderPlan(queryValue) );
        }else if (queryType.equals("TOTAL_PAYED_TO_CUSTOMERS")){
            System.out.println("Total amount payed under "+ queryValue +
                    " is " + database.totalPayedToCutomers(queryValue));
        } else {
            System.out.println("PRINT PLAN feature is not yet implemented.");
        }
        //print different things if the queryType equals to "NUM_CUSTOMERS" or "TOTAL_PAYED_TO_CUSTOMERS"


    }
}
