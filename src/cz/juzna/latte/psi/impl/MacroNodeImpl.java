package cz.juzna.latte.psi.impl;

import com.intellij.extapi.psi.ASTDelegatePsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import cz.juzna.latte.lexer.LatteTokenTypes;
import org.jetbrains.annotations.NotNull;

/**
 * Curly brackets macro
 */
public class MacroNodeImpl extends LattePsiElement {

	public MacroNodeImpl(ASTNode node) {
		super(node);
	}

	public String getMacroName() {
		for(PsiElement el: getChildren()) {
			if(el.getNode().getElementType() == LatteTokenTypes.MACRO_NAME) return el.getText();
		}
		return null;
	}

	public PsiElement getParams() {
		for(PsiElement el: getChildren()) {
			if(el.getNode().getElementType() == LatteTokenTypes.PARAMS) return el;
		}
		return null;
	}

}
