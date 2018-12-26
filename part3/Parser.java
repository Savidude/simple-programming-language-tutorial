package part3;

class Parser {
    private Lexer lexer;

    Parser(String text) {
        this.lexer = new Lexer(text);
    }

    /*
    Returns the value of an INTEGER token
     */
    int term() {
        Token token = lexer.getCurrentToken();
        lexer.eat(Type.INTEGER);
        return (int) token.getValue();
    }

    int expr() {
        int result = term();
        Token currentToken = lexer.getCurrentToken();
        while (currentToken.getType() == Type.PLUS || currentToken.getType() == Type.MINUS) {
            if (currentToken.getType() == Type.PLUS) {
                lexer.eat(Type.PLUS);
                result = result + term();
            } else if (currentToken.getType() == Type.MINUS) {
                lexer.eat(Type.MINUS);
                result = result - term();
            }
            currentToken = lexer.getCurrentToken();
        }
        return result;
    }
}
