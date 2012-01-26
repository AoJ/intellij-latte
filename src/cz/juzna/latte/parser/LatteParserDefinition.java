package cz.juzna.latte.parser;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import cz.juzna.latte.lexer.LatteLexer;
import cz.juzna.latte.lexer.LatteTokenTypes;
import cz.juzna.latte.psi.impl.LatteFileImpl;
import cz.juzna.latte.psi.impl.LattePsiElement;
import cz.juzna.latte.psi.impl.MacroAttrImpl;
import cz.juzna.latte.psi.impl.MacroNodeImpl;
import org.jetbrains.annotations.NotNull;

public class LatteParserDefinition implements ParserDefinition {
    @NotNull
    @Override
    public Lexer createLexer(Project project) {
        return new LatteLexer();
    }

    @Override
    public PsiParser createParser(Project project) {
        return new LatteParser();
    }

    @Override
    public IFileElementType getFileNodeType() {
        return LatteTokenTypes.FILE;
    }

    @NotNull
    @Override
    public TokenSet getWhitespaceTokens() {
        return LatteTokenTypes.WHITESPACES;
    }

    @NotNull
    @Override
    public TokenSet getCommentTokens() {
        return LatteTokenTypes.COMMENTS;
    }

    @NotNull
    @Override
    public TokenSet getStringLiteralElements() {
        return LatteTokenTypes.STRING_LITERALS;
    }

    @NotNull
    @Override
    public PsiElement createElement(ASTNode node) {
	    IElementType type = node.getElementType();

	    if(type == LatteTokenTypes.MACRO_NODE) return new MacroNodeImpl(node);
	    else if(type == LatteTokenTypes.MACRO_ATTR) return new MacroAttrImpl(node);
	    else return new LattePsiElement(node);
    }

    @Override
    public PsiFile createFile(FileViewProvider fileViewProvider) {
        return new LatteFileImpl(fileViewProvider);
    }

    @Override
    public SpaceRequirements spaceExistanceTypeBetweenTokens(ASTNode astNode, ASTNode astNode1) {
        return SpaceRequirements.MAY;
    }
}
