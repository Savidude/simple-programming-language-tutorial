package part1;

public class Parser {
    // String input (eg: "2+3")
    private String text;
    // Keeps an index of the current position in the input
    private int pos;
    // Current token instance
    private Token currentToken;

    public Parser(String text) {
        this.text = text;
        this.pos = 0;
        this.currentToken = null;
    }

    /* Lexical Analyzer (also known as lexer, scanner, or tokenizer)

    This method is responsible for breaking the input apart into a stream of tokens.
     */
    private Token getNextToken() {
        // Checks if the current position index has passed the number of characters in the input string. If so, return
        // an EOF token as there is no more input left to be converted into tokens.
        if (this.pos > this.text.length() - 1) {
            return new Token(Type.EOF, null);
        }

        // get a character at the current position
        char currentChar = this.text.charAt(this.pos);

        // If the character is a digit, then convert it to int, create an INTEGER token, increment pos index to point
        // to the next character after the digit, and return the INTEGER token
        if (Character.isDigit(currentChar)) {
            Token token = new Token(Type.INTEGER, Character.getNumericValue(currentChar));
            this.pos++;
            return token;
        }
        if (currentChar == '+') {
            Token token = new Token(Type.PLUS, currentChar);
            this.pos++;
            return token;
        }

        try {
            throw new Exception("Invalid token type");
        } catch (Exception e) {
            System.err.printf("Invalid token type %c found", currentChar);
            System.exit(0);
            return null;
        }
    }

    /*
    Compares the current token type with the token type passed. If they match, then "eat" the current token and assign
    the next token using the getNextToken() method. If they do not match, raise an exception.
     */
    private void eat(Type tokenType) {
        if (this.currentToken.getType() == tokenType) {
            this.currentToken = getNextToken();
        } else {
            try {
                throw new Exception("Expected token type did not match");
            } catch (Exception e) {
                System.err.printf("Expected token type %s did not match", tokenType.toString());
                System.exit(0);
            }
        }
    }

    /*
    Tries to identify the structure "INTEGER PLUS INTEGER"
    expr -> INTEGER PLUS INTEGER
     */
    public int expr() {
        // Set the current token to the first token taken from the input
        this.currentToken = getNextToken();

        // The current token is expected to be an integer
        Token left = this.currentToken;
        eat(Type.INTEGER);

        // The current token is expected to be a PLUS (+)
        Token operation = this.currentToken;
        eat(Type.PLUS);

        // The current token is expected to be a single digit integer
        Token right = this.currentToken;
        eat(Type.INTEGER);

        int result = (Integer)left.getValue() + (Integer)right.getValue();
        return result;
    }
}
