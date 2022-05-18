import java.text.ParseException;

class CommandHandler {
    Database database;

    CommandHandler(Database database) {
        this.database = database;
    }
    // get Database

    void run(Command command) throws ParseException {
        command.run(database);
    }
    // call the run function in in Command
}
