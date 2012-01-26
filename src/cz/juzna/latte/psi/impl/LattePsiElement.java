package cz.juzna.latte.psi.impl;

import com.intellij.extapi.psi.ASTDelegatePsiElement;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.impl.source.tree.SharedImplUtil;
import org.jetbrains.annotations.NotNull;

/**
 * Base of all PsiElements
 */
public class LattePsiElement extends ASTWrapperPsiElement {
	public LattePsiElement(@NotNull ASTNode astNode) {
		super(astNode);
	}

	// some common logic should come here
}
