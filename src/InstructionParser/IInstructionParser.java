package InstructionParser;

import java.util.List;

public interface IInstructionParser {

    class ParserException extends Exception {

        public ParserException(String message){
            super(message);
        }

    }
    /*
        The only function that defines an instruction parser is
        turning a string of data into a list of instructions.
     */
    List<Instruction> parse(String instructions) throws ParserException;
}
