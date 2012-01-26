package cz.juzna.latte.editor;

import com.intellij.lang.Language;
import com.intellij.openapi.editor.colors.EditorColorsScheme;
import com.intellij.openapi.editor.ex.util.LayerDescriptor;
import com.intellij.openapi.editor.ex.util.LayeredLexerEditorHighlighter;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.fileTypes.StdFileTypes;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.templateLanguages.TemplateDataLanguageMappings;
import cz.juzna.latte.lexer.LatteTokenTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Layered highlighter - uses LatteSyntaxHighlighter as main one, and second for outer text (HTML)
 */
public class LatteTemplateHighlighter extends LayeredLexerEditorHighlighter {
    public LatteTemplateHighlighter(@Nullable Project project, @Nullable VirtualFile virtualFile, @NotNull EditorColorsScheme colors) {
        // create main highlighter
        super(new LatteSyntaxHighlighter(), colors);

        // highlighter for outer lang
        FileType type = null;
        if(project == null || virtualFile == null) {
            type = StdFileTypes.PLAIN_TEXT;
        } else {
	        Language language = TemplateDataLanguageMappings.getInstance(project).getMapping(virtualFile);
	        if(language != null) type = language.getAssociatedFileType();
            if(type == null) type = StdFileTypes.HTML;
        }
        SyntaxHighlighter outerHighlighter = SyntaxHighlighter.PROVIDER.create(type, project, virtualFile);

        registerLayer(LatteTokenTypes.HTML_TEXT, new LayerDescriptor(outerHighlighter, ""));
    }
}
