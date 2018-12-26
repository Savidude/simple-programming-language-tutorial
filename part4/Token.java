package part4;

/*
Token types:
EOF (end of file) token should be used to indicate that there is no more input left in the lexical analysis.
 */
enum Type {
    INTEGER,
    PLUS,
    MINUS,
    MUL,
    DIV,
    EOF
}

class Token {
    // token type: INTEGER, PLUS, or EOF
    private Type type;
    // token value: 0, 1, 2. 3, 4, 5, 6, 7, 8, 9, '+', or null
    private Object value;


    Token(Type type, Object value) {
        this.type = type;
        this.value = value;
    }

    Type getType() {
        return type;
    }

    Object getValue() {
        return value;
    }
}