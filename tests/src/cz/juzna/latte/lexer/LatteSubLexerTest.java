package cz.juzna.latte.lexer;

import junit.framework.TestCase;

/**
 * Created by IntelliJ IDEA.
 * User: juzna
 * Date: 1/25/12
 * Time: 9:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class LatteSubLexerTest extends AbstractLexerTest {

    @Override
    public void setUp() throws Exception {
        super.setUp();
        lexer = new LatteSubLexer();
    }

    public void testLink() throws Exception {
        LexerToken[] tokens = lex("Homepage:detail, id => $id");

        assertToken(tokens[0], LatteTokenTypes.IDENTIFIER, "Homepage");
        assertToken(tokens[1], LatteTokenTypes.COLON, ":");
        assertToken(tokens[2], LatteTokenTypes.IDENTIFIER, "detail");
        assertToken(tokens[3], LatteTokenTypes.COMMA, ",");
        assertToken(tokens[4], LatteTokenTypes.WHITE_SPACE, " ");
        assertToken(tokens[5], LatteTokenTypes.IDENTIFIER, "id");
        assertToken(tokens[6], LatteTokenTypes.WHITE_SPACE, " ");
        assertToken(tokens[7], LatteTokenTypes.ASSIGN, "=>");
        assertToken(tokens[8], LatteTokenTypes.WHITE_SPACE, " ");
        assertToken(tokens[9], LatteTokenTypes.VARIABLE, "$id");
        assertEquals(10, tokens.length);
    }

    public void testForeach() throws Exception {
        LexerToken[] tokens = lex("$items as $item");

        assertToken(tokens[0], LatteTokenTypes.VARIABLE, "$items");
        assertToken(tokens[1], LatteTokenTypes.WHITE_SPACE, " ");
        assertToken(tokens[2], LatteTokenTypes.KEYWORD, "as");
        assertToken(tokens[3], LatteTokenTypes.WHITE_SPACE, " ");
        assertToken(tokens[4], LatteTokenTypes.VARIABLE, "$item");
        assertEquals(5, tokens.length);
    }
}
