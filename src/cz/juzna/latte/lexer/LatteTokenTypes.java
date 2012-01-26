package cz.juzna.latte.lexer;

import com.intellij.psi.TokenType;
import com.intellij.psi.templateLanguages.TemplateDataElementType;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import cz.juzna.latte.LatteLanguage;

/**
 * @author Jan Dolecek - juzna.cz@gmail.com
 */
public interface LatteTokenTypes {
    // large grain parsing
    public static final IElementType TEMPLATE_HTML_TEXT = new LatteElementType("LATTE_TEMPLATE_HTML_TEXT"); // produced by lexer for all HTML code
    public static final IElementType OUTER_ELEMENT_TYPE = new LatteElementType("LATTE_FRAGMENT");
    public static final TemplateDataElementType TEMPLATE_DATA =
            new TemplateDataElementType("LATTE_TEMPLATE_DATA", LatteLanguage.LATTE_LANGUAGE, TEMPLATE_HTML_TEXT, OUTER_ELEMENT_TYPE);
    

	// Common tokens
    IElementType BAD_CHARACTER = TokenType.BAD_CHARACTER;
    IElementType WHITE_SPACE = TokenType.WHITE_SPACE;
    IElementType COMMENT = new LatteElementType("COMMENT");
    @Deprecated
    IElementType HTML_TEXT = TEMPLATE_HTML_TEXT;


	// TopLexer tokens
    IElementType OPENING = new LatteElementType("OPEN-MACRO");      // {
    IElementType MACRO_NAME = new LatteElementType("MACRO_NAME");   //  if
	IElementType PARAMS = new LatteElementType("PARAMS");           //     $cond
	IElementType CLOSING = new LatteElementType("CLOSE-MACRO");     //          }

	IElementType TAG_NAME = new LatteElementType("TAG-NAME");
	IElementType TAG_CLOSING = new LatteElementType("END_TAG");

    IElementType N_ATTR = new LatteElementType("N-ATTR");           // n:
    IElementType ATTR_NAME = new LatteElementType("ATTR-NAME");     //   link
    IElementType N_ATTR_EQ = new LatteElementType("N-ATTR-EQ");     //       =
	IElementType N_QUOTE = new LatteElementType("QUOTE");           //        "           "
	IElementType N_ATTR_VALUE = new LatteElementType("ATTR-VALUE"); //         Pres:detail
	IElementType ATTR = new LatteElementType("ATTR");


	// SubLexer tokens
    IElementType KEYWORD = new LatteElementType("KEYWORD");
    IElementType IDENTIFIER = new LatteElementType("IDENTIFIER");
    IElementType COLON = new LatteElementType(":");
    IElementType SEMICOLON = new LatteElementType(";");
    IElementType ASSIGN = new LatteElementType("ASSIGN");
    IElementType STRING = new LatteElementType("STRING");
    IElementType VARIABLE = new LatteElementType("VARIABLE");
    IElementType EXCLAMATION = new LatteElementType("EXCLAMATION");
    IElementType NUMBER = new LatteElementType("NUMBER");
    IElementType COMMA = new LatteElementType(",");
    IElementType MODIFIER = new LatteElementType("|");
    IElementType OPERATOR = new LatteElementType("OPERATOR");


	// Parser
	IElementType MACRO_NODE = new LatteElementType("MACRO-NODE");
	IElementType MACRO_ATTR = new LatteElementType("MACRO-ATTR");





    TokenSet nAttrSet = TokenSet.create(
            TAG_NAME,
            ATTR_NAME,
            N_ATTR_EQ,
            N_QUOTE,
            N_ATTR_VALUE
    );



    IFileElementType FILE = new IFileElementType("FILE", LatteLanguage.LATTE_LANGUAGE);

    TokenSet WHITESPACES = TokenSet.create(WHITE_SPACE);
    TokenSet COMMENTS = TokenSet.create(COMMENT);
    TokenSet STRING_LITERALS = TokenSet.create();
}
