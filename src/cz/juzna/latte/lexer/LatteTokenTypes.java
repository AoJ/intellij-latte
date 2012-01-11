package cz.juzna.latte.lexer;

import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import cz.juzna.latte.LatteLanguage;

/**
 * @author Jan Dolecek - juzna.cz@gmail.com
 */
public interface LatteTokenTypes {
    IElementType BAD_CHARACTER = TokenType.BAD_CHARACTER;
    IElementType WHITE_SPACE = TokenType.WHITE_SPACE;
    IElementType COMMENT = new LatteElementType("COMMENT");
    IElementType HTML_TEXT = new LatteElementType("HTML_TEXT");

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
    IElementType ATTR = new LatteElementType("ATTR");
    IElementType ATTR_VALUE = new LatteElementType("ATTR-VALUE");



    IFileElementType FILE = new IFileElementType("FILE", LatteLanguage.LATTE_LANGUAGE);

    TokenSet WHITESPACES = TokenSet.create(WHITE_SPACE);
    TokenSet COMMENTS = TokenSet.create(COMMENT);
    TokenSet STRING_LITERALS = TokenSet.create();
}
