import java.util.ArrayList;
import java.util.Scanner;

class InputReader {
    private Scanner keyboard;
    private static InputReader instance = null;
    private int lineNumber = 0;

    private InputReader() {
        keyboard = new Scanner(System.in);
    }
    //Using Scanner to read the input

    static InputReader getInstance() {
        if (instance == null) {
            instance = new InputReader();
        }
        return instance;
        //if input is null start a new InputReader
    }

    ArrayList<Command> getCommands() {
        ArrayList<Command> commands = new ArrayList<>();
        String line = "";
        lineNumber = 0;

        try {
            while (keyboard.hasNext()) {
                lineNumber++;
                line = keyboard.nextLine();
                if (line.startsWith("PRINT ")) {
                    commands.add(makePrintCommand(line));
                } else if (line.startsWith("BEGIN_")) {
                    commands.add(makeBlockCommand(line));
                } else if (line.equals("FINISH")) {
                    break;
                } else if (!line.equals("")) {
                    System.out.println(line);
                    throw new BadCommandException("Invalid command.");
                }
                // process the input according to the "PRINT" ,"BEGIN" and "FINISH" command
            }
        } catch (BadCommandException e) {
            throw new BadCommandException("Line " + lineNumber + " : " + e.getMessage());
            //if input is a 'bad command' ,throw it as a exception
        }
        return commands;
    }

    private Command makeBlockCommand(String line) {
        // Removes "BEGIN_" from the current line to get the command type;
        BlockCommand command = new BlockCommand(line.substring(6));
        /*loop the input and add lineNumber by 1 each time, if reach "END" stop the loop
        if the tokens is not valid ,throw a exception,otherwise return command
         */
        while (keyboard.hasNext()) {
            lineNumber ++;
            line = keyboard.nextLine();
            if (line.equals("END_" + command.getBlockType())) {
                return command;
            } else if (line.equals("")) {
            }
            else {
                String [] tokens = line.split(" ", 3);
                if (tokens.length != 3 || tokens[1].length() != 1)
                    throw new BadCommandException("Invalid tag.");
                command.addTag(new Tag(tokens));
            }
        }
        return command;
    }

    private Command makePrintCommand(String line) {
        String[] tokens = line.split(" ", 5);
        if (tokens.length > 4) {
            throw new BadCommandException("Invalid print command; too many tokens.");
        } else if (tokens.length < 4) {
                throw new BadCommandException("Invalid print command; too few tokens.");
        }
        return new PrintCommand(tokens);
        //return PrintCommand(tokens) if the tokens is valid.
    }
}