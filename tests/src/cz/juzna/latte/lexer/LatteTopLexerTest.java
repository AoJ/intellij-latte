package cz.juzna.latte.lexer;


import com.intellij.openapi.util.io.FileUtil;
import com.intellij.psi.impl.DebugUtil;
import org.apache.tools.ant.types.resources.Tokens;

import java.io.File;

public class LatteTopLexerTest extends AbstractLexerTest {

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        lexer = new LatteTopLexer();
    }

    public void testComment01() throws Exception {
        LexerToken[] tokens = lex("Ahoj {* comment here *} more text {* second comment *}");
        
        assertEquals(4, tokens.length);
        assertToken(tokens[0], LatteTokenTypes.TEMPLATE_HTML_TEXT, "Ahoj ");
        assertToken(tokens[1], LatteTokenTypes.COMMENT, "{* comment here *}");
        assertToken(tokens[2], LatteTokenTypes.TEMPLATE_HTML_TEXT, " more text ");
        assertToken(tokens[3], LatteTokenTypes.COMMENT, "{* second comment *}");
    }

    public void testMacro01() throws Exception {
        LexerToken[] tokens = lex("Ahoj {$jmeno}");

        assertEquals(4, tokens.length);
        assertToken(tokens[0], LatteTokenTypes.TEMPLATE_HTML_TEXT, "Ahoj ");
        assertToken(tokens[1], LatteTokenTypes.OPENING, "{");
        assertToken(tokens[2], LatteTokenTypes.MACRO_NAME, "$jmeno");
        assertToken(tokens[3], LatteTokenTypes.CLOSING, "}");
    }

    public void testMacro02() throws Exception {
        LexerToken[] tokens = lex("Ahoj {if $jmeno} something {else} list {/endif}");

        assertToken(tokens[2], LatteTokenTypes.MACRO_NAME, "if");
        assertToken(tokens[3], LatteTokenTypes.WHITE_SPACE, " ");
        assertToken(tokens[4], LatteTokenTypes.PARAMS, "$jmeno");
    }

    public void testNAttr01() throws Exception {
        String[] templates = new String[] {
                "Ahoj <a n:href=\"Novak:detail!\" />",
                "Ahoj <a n:href=\'Novak:detail!\' />"
        };
        
        for(String tpl: templates) {
            LexerToken[] tokens = lex(tpl);

            assertToken(tokens[0], LatteTokenTypes.TEMPLATE_HTML_TEXT, null);
            assertToken(tokens[1], LatteTokenTypes.N_ATTR, "n:");
            assertToken(tokens[2], LatteTokenTypes.ATTR_NAME, "href");
            assertToken(tokens[3], LatteTokenTypes.N_ATTR_EQ, "=");
            assertToken(tokens[4], LatteTokenTypes.N_QUOTE, null);
            assertToken(tokens[5], LatteTokenTypes.N_ATTR_VALUE, "Novak:detail!");
            assertToken(tokens[6], LatteTokenTypes.N_QUOTE, null);
            assertToken(tokens[7], LatteTokenTypes.TEMPLATE_HTML_TEXT, null);
            assertEquals(8, tokens.length);
        }
    }   
    
    public void testNAttr02() throws Exception {
        String tpl = "Ahoj <a n:href=Novak:detail! />";

        LexerToken[] tokens = lex(tpl);

        assertToken(tokens[0], LatteTokenTypes.TEMPLATE_HTML_TEXT, null);
        assertToken(tokens[1], LatteTokenTypes.N_ATTR, "n:");
        assertToken(tokens[2], LatteTokenTypes.ATTR_NAME, "href");
        assertToken(tokens[3], LatteTokenTypes.N_ATTR_EQ, "=");
        assertToken(tokens[4], LatteTokenTypes.N_ATTR_VALUE, "Novak:detail!");
        assertToken(tokens[5], LatteTokenTypes.TEMPLATE_HTML_TEXT, null);
        assertEquals(6, tokens.length);
    }

	public void testHtmlTemplate01() throws Exception {
		String testPath = getTestDataPath() + "/sample01.html"; // plain html, no latte at all
		String data = FileUtil.loadFile(new File(testPath));

		LexerToken[] tokens = lex(data);
		assertEquals(1, tokens.length);
	}
	
	public void testHtml01() throws Exception {
		String[] htmlSnippets = new String[] {
				"<!DOCTYPE html>",
				"\n\n<html><head><body>",
				"<script type=\"text/javascript\" src=\"/js/cms.js\"></script>",
				"<!-- Load TinyMCE -->",
				"<div id=\"layout_header\">",
				"\t<script type=\"text/javascript\">\n" +
						"\t\tvar x = {};\n" +
						"\t\tvar y = { a: 1 };\n" +
						"\t</script>"
		};
		
		for(String snippet: htmlSnippets) {
			LexerToken[] tokens = lex(snippet);
			assertEquals("HTML snippet lexed as more than one token: " + snippet, 1, tokens.length);
		}
	}
}
