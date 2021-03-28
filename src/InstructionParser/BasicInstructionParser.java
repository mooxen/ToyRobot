package InstructionParser;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static InstructionParser.Instruction.Direction.*;

public class BasicInstructionParser implements IInstructionParser {


    /*
    basic representation of a command token
    Tokens are the smallest unit of meaning
    in the input (e.g):
        PLACE 1,2,EAST
        MOVE
        LEFT
        REPORT

    tokens are as follows:
        "PLACE" "1" "," "2" "," "EAST" "MOVE" "LEFT" "REPORT"
    */


    private static class Token {

        //helper map: maps string literals to the token type
        public static Map<String, TokenType> keywordMap = Map.of(
            "PLACE", TokenType.KW_PLACE,
            "MOVE", TokenType.KW_MOVE,
            "LEFT", TokenType.KW_LEFT,
            "RIGHT", TokenType.KW_RIGHT,
            "REPORT", TokenType.KW_REPORT,
            "EAST", TokenType.KW_EAST,
            "NORTH", TokenType.KW_NORTH,
            "SOUTH", TokenType.KW_SOUTH,
            "WEST", TokenType.KW_WEST
        );

        private enum TokenType {
            KW_PLACE,
            KW_MOVE,
            KW_LEFT,
            KW_RIGHT,
            KW_REPORT,
            KW_EAST(EAST),
            KW_NORTH(NORTH),
            KW_SOUTH(SOUTH),
            KW_WEST(WEST),
            NUMBER,
            COMMA;

            //provides an easy method of translation between token
            //types and instructions
            private Instruction.Direction instructionDirection;

            TokenType(){
                this.instructionDirection = null;
            }

            TokenType(Instruction.Direction instructionDirection){
                this.instructionDirection = instructionDirection;
            }

            Instruction.Direction getInstructionDirection(){
                return this.instructionDirection;
            }

        }

        public String cargo;
        public TokenType tokenType;

        public int lineNumber;
        public int colNumber;

        public Token(TokenType tokenType, String cargo, int lineNumber,  int colNumber){
            this.cargo = cargo;
            this.tokenType = tokenType;
            this.lineNumber = lineNumber;
            this.colNumber = colNumber;
        }

        @Override
        public String toString() {
            return "Token<"+this.tokenType.name()+":"+this.cargo+">";
        }

    }

    /*
        encapsulates the current state of
        tokenization to provide simpler
        management of line counting
        column counting and end of input handling
     */
    private static class TokenizerState {

        private int lineNumber;
        private int colNumber;
        private int scanIndex;
        private String input;

        public TokenizerState(String input){
            this.lineNumber = 0;
            this.colNumber = 0;
            this.scanIndex = 0;
            this.input = input;
        }

        public Character currentChar(){
            if(this.scanIndex >= this.input.length()){
                return null;
            }

            return this.input.charAt(this.scanIndex);
        }

        public Character getChar(){
            if(scanIndex >= this.input.length()){
                return null;
            }

            Character character = this.input.charAt(scanIndex);

            if(character == '\n'){
                this.lineNumber += 1;
                this.colNumber = 0;
            }else{
                this.colNumber += 1;
            }
            scanIndex += 1;

            return character;
        }

        public boolean hasCharacters(){
            return this.input.length() > this.scanIndex;
        }


        public void consumeWhitespace(){
            while(this.currentChar() != null && Character.isWhitespace(this.currentChar())){
                this.getChar();
            }
        }

        public Token createToken(Token.TokenType tokenType, String cargo){
            return new Token(tokenType, cargo, this.lineNumber, this.colNumber);
        }

    }

    /*
        Manages stepping through list of tokens
        provides simple error detection for the
        parser
     */
    private static class TokenFeed {

        private List<Token> tokens;
        private int currentIndex;

        public TokenFeed(List<Token> tokens){
            this.tokens = tokens;
            this.currentIndex = 0;
        }

        //consume a token if the type matches tokenType
        public Token consume(Token.TokenType tokenType) throws ParserException {
            Token token = this.tokens.get(this.currentIndex);
            if(tokenType == token.tokenType){
                this.currentIndex += 1;
                return token;
            }

            throw new ParserException("Expected Token " + tokenType.name() + ", found " + token.tokenType.name() +
                    ": line " + token.lineNumber + " column " + token.colNumber);
        }

        public boolean hasTokens(){
            return this.currentIndex < this.tokens.size();
        }

        public boolean peek(Token.TokenType type){
            return this.tokens.get(this.currentIndex).tokenType == type;
        }
    }


    //split input string into list of tokens for parsing into commands
    //removes redundant whitespace
    private List<Token> tokenize(String input) throws ParserException {
        TokenizerState tokenizerState = new TokenizerState(input);
        List<Token> tokenList = new ArrayList<>();
        String buffer = "";

        while (tokenizerState.hasCharacters()) {

            if (Character.isAlphabetic(tokenizerState.currentChar())) {
                //parse alphabetic tokens e.g. keywords

                while (tokenizerState.currentChar() != null && Character.isAlphabetic(tokenizerState.currentChar())) {
                    buffer += tokenizerState.getChar();
                }


                if (Token.keywordMap.containsKey(buffer)) {
                    Token.TokenType currentTokenType = Token.keywordMap.get(buffer);
                    tokenList.add(tokenizerState.createToken(currentTokenType, buffer));
                } else {
                    throw new ParserException("Unrecognized token " + buffer);
                }

            } else if (Character.isDigit(tokenizerState.currentChar())) {
                //parse number literals
                while (tokenizerState.currentChar() != null && Character.isDigit(tokenizerState.currentChar())) {
                    buffer += tokenizerState.getChar();
                }

                tokenList.add(tokenizerState.createToken(Token.TokenType.NUMBER, buffer));

            } else if (tokenizerState.currentChar() == ',') {
                //special case for commas
                buffer += tokenizerState.getChar();
                tokenList.add(tokenizerState.createToken(Token.TokenType.COMMA, buffer));
            }

            buffer = "";
            if (tokenizerState.currentChar() != null && Character.isWhitespace(tokenizerState.currentChar())) {
                tokenizerState.consumeWhitespace();
            }

        }

        return tokenList;


    }

    private Token parseDirection(TokenFeed tokenFeed) throws ParserException {
        if(tokenFeed.peek(Token.TokenType.KW_EAST)){

            return tokenFeed.consume(Token.TokenType.KW_EAST);

        }else if(tokenFeed.peek(Token.TokenType.KW_NORTH)){

            return tokenFeed.consume(Token.TokenType.KW_NORTH);

        }else if(tokenFeed.peek(Token.TokenType.KW_WEST)){

            return tokenFeed.consume(Token.TokenType.KW_WEST);

        }else if(tokenFeed.peek(Token.TokenType.KW_SOUTH)){

            return tokenFeed.consume(Token.TokenType.KW_SOUTH);

        }

        return null;
    }

    private Instruction parsePlace(TokenFeed tokenFeed) throws ParserException {

        tokenFeed.consume(Token.TokenType.KW_PLACE);
        Token X = tokenFeed.consume(Token.TokenType.NUMBER);
        tokenFeed.consume(Token.TokenType.COMMA);
        Token Y = tokenFeed.consume(Token.TokenType.NUMBER);
        tokenFeed.consume(Token.TokenType.COMMA);
        Token direction = this.parseDirection(tokenFeed);

        int XValue = Integer.parseInt(X.cargo);
        int YValue = Integer.parseInt(Y.cargo);
        Instruction.Direction instructionDirection = direction.tokenType.getInstructionDirection();

        return new Instruction.PlaceInstruction(XValue, YValue, instructionDirection);

    }

    private Instruction parseMove(TokenFeed tokenFeed) throws ParserException {
        tokenFeed.consume(Token.TokenType.KW_MOVE);
        return new Instruction.MoveInstruction();
    }

    private Instruction parseLeft(TokenFeed tokenFeed) throws ParserException {
        tokenFeed.consume(Token.TokenType.KW_LEFT);
        return new Instruction.LeftInstruction();
    }

    private Instruction parseRight(TokenFeed tokenFeed) throws ParserException {
        tokenFeed.consume(Token.TokenType.KW_RIGHT);
        return new Instruction.RightInstruction();
    }

    private Instruction parseReport(TokenFeed tokenFeed) throws ParserException {
        tokenFeed.consume(Token.TokenType.KW_REPORT);
        return new Instruction.ReportInstruction();
    }

    /*
    construct the list of instructions to hand to
    the simulator. Parse structure based on a recursive
    descent parser. Although in this case the furthest
    required descent is 2 levels, allows for extension
    to nested commands etc.
    */
    @Override
    public List<Instruction> parse(String instructionSrc) throws ParserException {

        List<Instruction> instructions = new ArrayList<>();
        List<Token> tokens = this.tokenize(instructionSrc);
        //note from author: token no longer looks like a word
        TokenFeed tokenFeed = new TokenFeed(tokens);

        while(tokenFeed.hasTokens()){

            if(tokenFeed.peek(Token.TokenType.KW_PLACE)){

                instructions.add(this.parsePlace(tokenFeed));

            }else if(tokenFeed.peek(Token.TokenType.KW_MOVE)){

                instructions.add(this.parseMove(tokenFeed));

            }else if(tokenFeed.peek(Token.TokenType.KW_RIGHT)){

                instructions.add(this.parseRight(tokenFeed));

            }else if(tokenFeed.peek(Token.TokenType.KW_LEFT)){

                instructions.add(this.parseLeft(tokenFeed));

            }else if(tokenFeed.peek(Token.TokenType.KW_REPORT)){

                instructions.add(this.parseReport(tokenFeed));
            }

        }

        return instructions;

    }
}
