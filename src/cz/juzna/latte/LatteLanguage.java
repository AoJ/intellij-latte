package cz.juzna.latte;


import com.intellij.lang.Language;
import com.intellij.psi.templateLanguages.TemplateLanguage;


public class LatteLanguage extends Language implements TemplateLanguage {
    // singleton
    public static final LatteLanguage LATTE_LANGUAGE = new LatteLanguage();

    public LatteLanguage() {
        super("Latte", "application/x-latte-template");
    }
}
