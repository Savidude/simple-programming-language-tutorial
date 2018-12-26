package part4;

class Parser {
    private Lexer lexer;

    Parser(String text) {
        this.lexer = new Lexer(text);
    }

    /*
    factor : INTEGER
     */
    int factor() {
        Token token = lexer.getCurrentToken();
        lexer.eat(Type.INTEGER);
        return (int) token.getValue();
    }

    /*
    term : factor ((MUL | DIV) factor)*
     */
    int term() {
       int result = factor();
       Token currentToken = lexer.getCurrentToken();
       while (currentToken.getType() == Type.MUL || currentToken.getType() == Type.DIV) {
           if (currentToken.getType() == Type.DIV) {
               lexer.eat(Type.DIV);
               result = result / factor();
           }
           if (currentToken.getType() == Type.MUL) {
               lexer.eat(Type.MUL);
               result = result * factor();
           }
           currentToken = lexer.getCurrentToken();
       }
       return result;
    }

    /*
    expr   : term ((PLUS | MINUS) term)*
    term   : factor ((MUL | DIV) factor)*
    factor : INTEGER
     */
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
