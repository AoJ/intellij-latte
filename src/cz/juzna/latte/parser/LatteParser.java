package cz.juzna.latte.parser;

import com.intellij.lang.ASTNode;
import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import com.intellij.lang.PsiParser;
import com.intellij.psi.PsiElement;
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
            IElementType type = builder.getTokenType();

            if(type == LatteTokenTypes.OPENING) parseMacro(builder);
            else if(type == LatteTokenTypes.N_ATTR) parseNAttr(builder);

            builder.advanceLexer(); // move to next token
        }
        
        marker.done(root);
        return builder.getTreeBuilt();
    }

    // {macro ...}
    private void parseMacro(PsiBuilder builder) {
        Marker macroStart = builder.mark();
        builder.advanceLexer();

        // is there a name?
        String tagName = null;
        if(builder.getTokenType() == LatteTokenTypes.MACRO_NAME) {
            Marker macroNameMark = builder.mark();
            tagName = builder.getTokenText();
            builder.advanceLexer();
            macroNameMark.done(LatteTokenTypes.MACRO_NAME);
        }

        // params
        Marker paramsMark = builder.mark();
        parseParams(tagName, builder, LatteTokenTypes.CLOSING);
        paramsMark.done(LatteTokenTypes.PARAMS);

        // finish him
        if(builder.getTokenType() == LatteTokenTypes.CLOSING) {
            builder.advanceLexer();
        }
        macroStart.done(LatteTokenTypes.MACRO);
    }

    // n:link="something"
    // n:link=something
    private void parseNAttr(PsiBuilder builder) {
        Marker start = builder.mark();
        builder.advanceLexer();
        
        // Process name
        String attrName = null;
        if(builder.getTokenType() == LatteTokenTypes.ATTR_NAME) {
            Marker macroName =  builder.mark();
            attrName = "@" + builder.getTokenText();
            builder.advanceLexer();
            macroName.done(LatteTokenTypes.MACRO_NAME);
        }
        
        if(builder.getTokenType() == LatteTokenTypes.N_ATTR_EQ) builder.advanceLexer();
        
        boolean inQuotes;
        if(builder.getTokenType() == LatteTokenTypes.N_QUOTE) {
            inQuotes = true;
            builder.advanceLexer();
        } else inQuotes = false;


        // Process value
        Marker value = builder.mark();
        parseParams(attrName, builder, inQuotes ? LatteTokenTypes.N_QUOTE : LatteTokenTypes.TEMPLATE_HTML_TEXT);
        value.done(LatteTokenTypes.PARAMS);

        if(inQuotes && builder.getTokenType() == LatteTokenTypes.N_QUOTE) {
            builder.advanceLexer();
        }

        start.done(LatteTokenTypes.MACRO);
    }

    // custom params
    private void parseParams(String macroName, PsiBuilder builder, IElementType closing) {
        // just process it atm
        while(builder.getTokenType() != closing && !builder.eof()) {
            builder.advanceLexer();
        }
        
    }
}
