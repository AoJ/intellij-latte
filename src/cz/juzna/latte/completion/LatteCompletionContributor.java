package cz.juzna.latte.completion;

import com.intellij.codeInsight.completion.CompletionContributor;
import com.intellij.codeInsight.completion.CompletionType;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.patterns.StandardPatterns;
import com.intellij.psi.PsiElement;

/**
 * @author Jan Dolecek - juzna.cz@gmail.com
 */
public class LatteCompletionContributor extends CompletionContributor {
    private static final Logger log = Logger.getInstance("LatteCompletionContributor");


    public LatteCompletionContributor() {
        log.info("Created Latte completion contributor");

        extend(CompletionType.BASIC, StandardPatterns.instanceOf(PsiElement.class), new KeywordCompletionProvider());
    }
}
