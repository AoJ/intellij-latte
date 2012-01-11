package cz.juzna.latte.parser;

import com.intellij.lang.ASTNode;
import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import com.intellij.lang.PsiParser;
import com.intellij.psi.tree.IElementType;
import cz.juzna.latte.lexer.LatteTokenTypes;
import org.jetbrains.annotations.NotNull;

public class LatteParser implements PsiParser {
    @NotNull
    @Override
    public ASTNode parse(IElementType root, PsiBuilder builder) {
        Marker marker = builder.mark();
        
        // Process all tokens
        while(!builder.eof()) {
            if(builder.getTokenType() == LatteTokenTypes.OPENING) parseMacro(builder); 
            builder.advanceLexer(); // move to next token
        }
        
        marker.done(root);
        return builder.getTreeBuilt();
    }

    
    private void parseMacro(PsiBuilder builder) {
        Marker macroStart = builder.mark();
        builder.advanceLexer();

        // is there a name?
        if(builder.getTokenType() == LatteTokenTypes.MACRO_NAME) {
            Marker macroNameMark = builder.mark();
            builder.advanceLexer();
            macroNameMark.done(LatteTokenTypes.MACRO_NAME);
        }

        // params
        Marker paramsMark = builder.mark();
        while((builder.getTokenType() != LatteTokenTypes.CLOSING) && !builder.eof()) {
            builder.advanceLexer();
        }
        paramsMark.done(LatteTokenTypes.PARAMS);

        // finish him
        if(builder.getTokenType() == LatteTokenTypes.CLOSING) {
            builder.advanceLexer();
        }
        macroStart.done(LatteTokenTypes.MACRO);
    }
}
