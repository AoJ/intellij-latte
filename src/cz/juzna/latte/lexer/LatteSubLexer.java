package cz.juzna.latte.lexer;

import com.intellij.lexer.FlexAdapter;
import com.intellij.lexer.Lexer;
import com.intellij.lexer.MergingLexerAdapter;
import com.intellij.psi.tree.TokenSet;

import java.io.Reader;


public class LatteSubLexer extends MergingLexerAdapter {
    // To be merged
    private static final TokenSet TOKENS_TO_MERGE = TokenSet.create(
            LatteTokenTypes.WHITE_SPACE
    );

    public LatteSubLexer() {
        super(new FlexAdapter(new _LatteSubLexer((Reader) null)), TOKENS_TO_MERGE);
    }
}
