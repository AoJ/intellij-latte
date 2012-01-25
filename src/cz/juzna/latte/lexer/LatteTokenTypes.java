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
    
    
    IElementType BAD_CHARACTER = TokenType.BAD_CHARACTER;
    IElementType WHITE_SPACE = TokenType.WHITE_SPACE;
    IElementType COMMENT = new LatteElementType("COMMENT");

    @Deprecated
    IElementType HTML_TEXT = TEMPLATE_HTML_TEXT; //new LatteElementType("HTML_TEXT");

    IElementType OPENING = new LatteElementType("OPEN-MACRO");
    IElementType CLOSING = new LatteElementType("CLOSE-MACRO");

    IElementType LATTE_PARAMS = new LatteElementType("PARAMS");
    IElementType MACRO_NAME = new LatteElementType("MACRO_NAME");
    IElementType MACRO = new LatteElementType("MACRO");
    IElementType PARAMS = new LatteElementType("PARAMS");

    IElementType TAG_START = new LatteElementType("TAG-START");
    IElementType TAG_CLOSING = new LatteElementType("TAG-CLOSING");
    IElementType END_TAG = new LatteElementType("END_TAG");
    IElementType TAG_NAME = new LatteElementType("TAG-NAME");
    IElementType OUTER_TEXT = new LatteElementType("OUTER-TEST");
    IElementType LATTE_STRING = new LatteElementType("LATTE-STRING");

    IElementType N_ATTR = new LatteElementType("N-ATTR");
    IElementType ATTR_NAME = new LatteElementType("ATTR-NAME");
    IElementType N_ATTR_EQ = new LatteElementType("N-ATTR-EQ");
    IElementType ATTR = new LatteElementType("ATTR");
    IElementType N_ATTR_VALUE = new LatteElementType("ATTR-VALUE");
    IElementType N_QUOTE = new LatteElementType("QUOTE");

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

    IElementType OPERATOR = new LatteElementType("OPERATOR");


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
