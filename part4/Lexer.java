package part4;

class Lexer {
    // String input (eg: "2+3")
    private String text;
    // Keeps an index of the current position in the input
    private int pos;
    // Current token instance
    private Token currentToken;
    // Current character being pointed to
    private char currentChar;

    Lexer(String text) {
        this.text = text;
        this.pos = 0;
        this.currentToken = null;
        this.currentChar = this.text.charAt(this.pos);
    }

    Token getCurrentToken() {
        if (this.currentToken == null) {
            this.currentToken = getNextToken();
        }
        return this.currentToken;
    }

    /*
    Compares the current token type with the token type passed. If they match, then "eat" the current token and assign
    the next token using the getNextToken() method. If they do not match, raise an exception.
     */
    void eat(Type tokenType) {
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
    Advances the 'pos' pointer, and set a value to the currentChar variable
     */
    private void advance() {
        this.pos++;
        // Checks if the current position index has passed the number of characters in the input string. If so, set a
        // MIN_VALUE for current char, indicating the end of file.
        if (this.pos > this.text.length() -1) {
            this.currentChar = Character.MIN_VALUE;
        } else {
            this.currentChar = this.text.charAt(this.pos);
        }
    }

    private void skipWhitespace() {
        while (this.currentChar != Character.MIN_VALUE && Character.isWhitespace(this.currentChar)) {
            advance();
        }
    }

    /*
    Return a single, ot multi-digit integer taken from the input
     */
    private int integer() {
        String result = "";
        while (this.currentChar != Character.MIN_VALUE && Character.isDigit(this.currentChar)) {
            result += this.currentChar;
            advance();
        }
        return Integer.parseInt(result);
    }

    /*
    This method is responsible for breaking the input apart into a stream of tokens.
    */
    private Token getNextToken() {
        while (this.currentChar != Character.MIN_VALUE) {
            if (Character.isWhitespace(this.currentChar)) {
                skipWhitespace();
                continue;
            }
            if (Character.isDigit(this.currentChar)) {
                return new Token(Type.INTEGER, integer());
            }
            if (this.currentChar == '+') {
                advance();
                return new Token(Type.PLUS, currentChar);
            }
            if (this.currentChar == '-') {
                advance();
                return new Token(Type.MINUS, currentChar);
            }
            if (this.currentChar == '*') {
                advance();
                return new Token(Type.MUL, currentChar);
            }
            if (this.currentChar == '/') {
                advance();
                return new Token(Type.DIV, currentChar);
            }

            try {
                throw new Exception("Invalid token type");
            } catch (Exception e) {
                System.err.printf("Invalid token type %c found", currentChar);
                System.exit(0);
                return null;
            }
        }
        return new Token(Type.EOF, null);
    }
}
