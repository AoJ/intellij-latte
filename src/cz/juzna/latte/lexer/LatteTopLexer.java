package cz.juzna.latte.lexer;


import com.intellij.lexer.FlexAdapter;
import com.intellij.lexer.Lexer;
import com.intellij.lexer.LookAheadLexer;
import com.intellij.lexer.MergingLexerAdapter;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import java.io.Reader;


public class LatteTopLexer extends MergingLexerAdapter {
    // To be merged
    private static final TokenSet TOKENS_TO_MERGE = TokenSet.create(
            LatteTokenTypes.COMMENT,
            LatteTokenTypes.WHITE_SPACE,
            LatteTokenTypes.HTML_TEXT
    );

    public LatteTopLexer() {
       super(new LookAheadLexer(new FlexAdapter(new _LatteTopLexer((Reader) null))) {

            @Override
            protected void lookAhead(Lexer lex) {
                IElementType type = lex.getTokenType();
                if(type == LatteTokenTypes.N_ATTR) { // n: attr - keep all interesting tokens as they are
                    addToken(type);
                    lex.advance();

                    while (LatteTokenTypes.nAttrSet.contains(type = lex.getTokenType())) {
                        addToken(type);
                        lex.advance();
                    }
                }

                if(LatteTokenTypes.nAttrSet.contains(type)) addToken(LatteTokenTypes.TEMPLATE_HTML_TEXT); // otherwise replace attributes by generic HTML
                else addToken(type);

                lex.advance();
            }
        }, TOKENS_TO_MERGE);
    }
}