package cz.juzna.latte;


import com.intellij.lang.Language;
import com.intellij.openapi.editor.colors.EditorColorsScheme;
import com.intellij.openapi.editor.highlighter.EditorHighlighter;
import com.intellij.openapi.fileTypes.*;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.templateLanguages.TemplateLanguage;
import cz.juzna.latte.editor.LatteSyntaxHighlighter;
import cz.juzna.latte.editor.LatteTemplateHighlighter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public class LatteLanguage extends Language implements TemplateLanguage {
    // singleton
    public static final LatteLanguage LATTE_LANGUAGE = new LatteLanguage();

    public LatteLanguage() {
        super("Latte", "application/x-latte-template");
    }
}
