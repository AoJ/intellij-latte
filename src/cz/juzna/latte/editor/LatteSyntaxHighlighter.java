package cz.juzna.latte.editor;


import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.HighlighterColors;
import com.intellij.openapi.editor.SyntaxHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import cz.juzna.latte.lexer.LatteLexer;
import cz.juzna.latte.lexer.LatteTokenTypes;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;


public class LatteSyntaxHighlighter extends SyntaxHighlighterBase {

    public static final String            BAD_CHARACTER_ID = "Bad character";
    public static final TextAttributesKey BAD_CHARACTER    = TextAttributesKey.createTextAttributesKey(BAD_CHARACTER_ID, HighlighterColors.BAD_CHARACTER.getDefaultAttributes().clone());

    public static final String            COMMENT_ID       = "Latte comment";
    public static final TextAttributesKey COMMENT          = TextAttributesKey.createTextAttributesKey(COMMENT_ID, SyntaxHighlighterColors.LINE_COMMENT.getDefaultAttributes().clone());

    public static final String            BRACKETS_ID      = "Brackets";
    public static final TextAttributesKey BRACKETS         = TextAttributesKey.createTextAttributesKey(BRACKETS_ID, SyntaxHighlighterColors.BRACES.getDefaultAttributes().clone());

    public static final String            IDENTIFIER_ID    = "Identifier";
    public static final TextAttributesKey IDENTIFIER       = TextAttributesKey.createTextAttributesKey(IDENTIFIER_ID, HighlighterColors.TEXT.getDefaultAttributes().clone());

    public static final String            ATTR_ID          = "Attribute";
    public static final TextAttributesKey ATTR             = TextAttributesKey.createTextAttributesKey(ATTR_ID, SyntaxHighlighterColors.NUMBER.getDefaultAttributes().clone());

    public static final String            KEYWORD_ID       = "Keyword";
    public static final TextAttributesKey KEYWORD          = TextAttributesKey.createTextAttributesKey(KEYWORD_ID, SyntaxHighlighterColors.KEYWORD.getDefaultAttributes().clone());


    // Groups of IElementType's
    public static final TokenSet sBAD          = TokenSet.create(LatteTokenTypes.BAD_CHARACTER);
    public static final TokenSet sCOMMENTS     = TokenSet.create(LatteTokenTypes.COMMENT);
    public static final TokenSet sBRACES       = TokenSet.create(LatteTokenTypes.OPENING, LatteTokenTypes.CLOSING);
    public static final TokenSet sIDENTIFIERS  = TokenSet.create(LatteTokenTypes.TAG_NAME, LatteTokenTypes.MACRO_NAME);
    public static final TokenSet sATTRS        = TokenSet.create(LatteTokenTypes.ATTR_VALUE, LatteTokenTypes.N_ATTR);
    public static final TokenSet sKEYWORDS     = TokenSet.create();


    // Static container
    private static final Map<IElementType, TextAttributesKey> ATTRIBUTES = new HashMap<IElementType, TextAttributesKey>();


    // Fill in the map
    static {
        fillMap(ATTRIBUTES, sBAD,         BAD_CHARACTER);
        fillMap(ATTRIBUTES, sCOMMENTS,    COMMENT);
        fillMap(ATTRIBUTES, sBRACES,      BRACKETS);
        fillMap(ATTRIBUTES, sIDENTIFIERS, IDENTIFIER);
        fillMap(ATTRIBUTES, sATTRS,       ATTR);
        fillMap(ATTRIBUTES, sKEYWORDS,    KEYWORD);
    }


    @NotNull
    @Override
    public Lexer getHighlightingLexer() {
        return new LatteLexer();
    }

    @NotNull
    @Override
    public TextAttributesKey[] getTokenHighlights(IElementType type) {
        TextAttributesKey[] x = pack(ATTRIBUTES.get(type));
        return x;
    }
}
