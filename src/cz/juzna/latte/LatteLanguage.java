package cz.juzna.latte;


import com.intellij.lang.Language;
import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.fileTypes.SyntaxHighlighterFactory;
import com.intellij.psi.templateLanguages.TemplateLanguage;
import cz.juzna.latte.editor.LatteSyntaxHighlighter;
import org.jetbrains.annotations.NotNull;


public class LatteLanguage extends Language implements TemplateLanguage {
    // singleton
    public static final LatteLanguage LATTE_LANGUAGE = new LatteLanguage();

    public LatteLanguage() {
        super("Latte", "application/x-latte-template");

        // register highlighter - lazy singleton factory
        SyntaxHighlighterFactory.LANGUAGE_FACTORY.addExplicitExtension(this, new SingleLazyInstanceSyntaxHighlighterFactory() {
            protected SyntaxHighlighter createHighlighter() {
                return new LatteSyntaxHighlighter();
            }
        });
    }
}
