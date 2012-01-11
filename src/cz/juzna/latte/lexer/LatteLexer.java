package cz.juzna.latte.lexer;


import com.intellij.lexer.FlexAdapter;
import com.intellij.lexer.MergingLexerAdapter;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import java.io.Reader;


public class LatteLexer extends MergingLexerAdapter {
    // To be merged
    private static final TokenSet TOKENS_TO_MERGE = TokenSet.create(
            LatteTokenTypes.COMMENT,
            LatteTokenTypes.WHITE_SPACE,
            LatteTokenTypes.HTML_TEXT
    );

    public LatteLexer() {
        super(new FlexAdapter(new _LatteLexer((Reader) null)), TOKENS_TO_MERGE);
    }
}