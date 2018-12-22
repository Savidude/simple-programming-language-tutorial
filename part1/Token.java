package part1;

/*
Token types:
EOF (end of file) token should be used to indicate that there is no more input left in the lexical analysis.
 */
enum Type {
    INTEGER,
    PLUS,
    EOF
}

public class Token {
    // token type: INTEGER, PLUS, or EOF
    private Type type;
    // token value: 0, 1, 2. 3, 4, 5, 6, 7, 8, 9, '+', or null
    private Object value;


    public Token(Type type, Object value) {
        this.type = type;
        this.value = value;
    }

    public Type getType() {
        return type;
    }

    public Object getValue() {
        return value;
    }
}