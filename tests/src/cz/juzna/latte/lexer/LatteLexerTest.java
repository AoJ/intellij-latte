package cz.juzna.latte.lexer;


public class LatteLexerTest extends AbstractLexerTest {
    @Override
    public void setUp() throws Exception {
        super.setUp();
        lexer = new LatteLexer();
    }

    public void test01() throws Exception {
        LexerToken[] tokens = lex("Ahoj {if $jmeno > 0}");

        assertToken(tokens[0], LatteTokenTypes.TEMPLATE_HTML_TEXT, "Ahoj ");
        assertToken(tokens[1], LatteTokenTypes.OPENING, "{");
        assertToken(tokens[2], LatteTokenTypes.MACRO_NAME, "if");
        assertToken(tokens[3], LatteTokenTypes.WHITE_SPACE, " ");
        assertToken(tokens[4], LatteTokenTypes.VARIABLE, "$jmeno");
        assertToken(tokens[5], LatteTokenTypes.WHITE_SPACE, " ");
        assertToken(tokens[6], LatteTokenTypes.OPERATOR, ">");
        assertToken(tokens[7], LatteTokenTypes.WHITE_SPACE, " ");
        assertToken(tokens[8], LatteTokenTypes.NUMBER, "0");
        assertToken(tokens[9], LatteTokenTypes.CLOSING, "}");
        assertEquals(10, tokens.length);
    }
}
