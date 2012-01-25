package cz.juzna.latte.lexer;

import com.intellij.lexer.Lexer;
import com.intellij.psi.tree.TokenSet;

public class LatteLexer extends LayeredLexer {
    public static final TokenSet LATTE_TOKENS = TokenSet.create(LatteTokenTypes.PARAMS, LatteTokenTypes.N_ATTR_VALUE);

    public LatteLexer() {
        super(new LatteTopLexer(), new LatteSubLexer(), LATTE_TOKENS);
    }
}
