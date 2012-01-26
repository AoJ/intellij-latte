package cz.juzna.latte.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import cz.juzna.latte.lexer.LatteTokenTypes;

/**
 * Curly brackets macro
 */
public class MacroAttrImpl extends LattePsiElement {

	public MacroAttrImpl(ASTNode node) {
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
