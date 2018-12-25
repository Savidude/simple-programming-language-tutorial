package part2;

class Parser {
    private Lexer lexer;

    Parser(String text) {
        this.lexer = new Lexer(text);
    }

    /*
    Tries to identify the structure "INTEGER PLUS INTEGER"
    expr -> INTEGER PLUS INTEGER
    expr -> INTEGER MINUS INTEGER
     */
    int expr() {
        // The current token is expected to be an integer
        Token left = lexer.getCurrentToken();
        this.lexer.eat(Type.INTEGER);

        // The current token is expected to be either a PLUS (+) or a MINUS (-)
        Token operation = lexer.getCurrentToken();
        if (operation.getType() == Type.PLUS) {
            lexer.eat(Type.PLUS);
        } else {
            lexer.eat(Type.MINUS);
        }

        // The current token is expected to be a single digit integer
        Token right = lexer.getCurrentToken();
        lexer.eat(Type.INTEGER);

        // Either a INTEGER PLUS INTEGER, or a INTEGER MINUS INTEGER token stream should be found at this point. As a
        // result, the method can return the result by adding, or subtracting the two integers.
        int result;
        if (operation.getType() == Type.PLUS) {
            result = (Integer)left.getValue() + (Integer)right.getValue();
        } else {
            result = (Integer)left.getValue() - (Integer)right.getValue();
        }
        return result;
    }
}
