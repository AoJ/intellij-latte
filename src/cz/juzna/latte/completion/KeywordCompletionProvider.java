package cz.juzna.latte.completion;

import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionProvider;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.psi.PsiElement;
import com.intellij.util.ProcessingContext;
import cz.juzna.latte.lexer.LatteTokenTypes;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides keywords to be auto-completed
 */
public class KeywordCompletionProvider extends CompletionProvider<CompletionParameters> {
    // constant lists
    private static final String[] KEYWORDS = { "for", "foreach", "/for", "if", "else", "/if", "link", "plink" };
    private static final String[] FILTERS = { "date", "format", "replace", "url_encode", "json_encode", "title", "capitalize", "upper", "lower", "striptags", "join", "reverse", "length", "sort", "default", "keys", "escape", "raw", "merge" };

    // CompletionResultSet wants list of LookupElements
    private List<LookupElementBuilder> KEYWORD_LOOKUPS = new ArrayList();
    private List<LookupElementBuilder> FILTER_LOOKUPS = new ArrayList();


    public KeywordCompletionProvider() {
        super();

        for(String keyword: KEYWORDS) KEYWORD_LOOKUPS.add(LookupElementBuilder.create(keyword));
        for(String filter: FILTERS) FILTER_LOOKUPS.add(LookupElementBuilder.create(filter));
    }

    @Override
    protected void addCompletions(@NotNull CompletionParameters params,
                                  ProcessingContext ctx,
                                  @NotNull CompletionResultSet results) {

        PsiElement currElement = params.getPosition().getOriginalElement();
        if(currElement.getNode().getElementType() == LatteTokenTypes.MACRO_NAME) {
            for(LookupElementBuilder x: KEYWORD_LOOKUPS) results.addElement(x);
        }
    }
}
