package cz.juzna.latte.completion;

import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionProvider;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiWhiteSpace;
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
    private static final String[] KEYWORDS = {
		    // CoreMacros.php
		    "if", "ifset", "for", "foreach", "while", "first", "last", "sep", "class", "attr", "captute",
		    "var", "default", "dump", "debugbreak", "l", "r",

		    // FormMacros.php
		    "form", "input", "label", "formContainer",

		    // UIMacros.php
		    "include", "extends", "block", "define", "snippet", "widget", "control", "href", "link", "plink", "contentType", "status",
    };

	private static final String[] FILTERS = {
			// static
			"normalize", "toascii", "webalize", "truncate", "lower", "upper", "firstupper", "capitalize", "trim", "padleft", "padright",
			"replacere", "url", "striptags", "nl2br", "substr", "repeat", "implode", "number",

			// methods in Helpers.php
			"espaceHtml", "escapeHtmlComment", "escapeXML", "escapeCss", "escapeJs", "strip", "indent", "date", "bytes",
			"length", "replace", "dataStream", "null",
	};


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

	    // Keywords
        PsiElement curr = params.getPosition().getOriginalElement();
        if(curr.getNode().getElementType() == LatteTokenTypes.MACRO_NAME) {
            for(LookupElementBuilder x: KEYWORD_LOOKUPS) results.addElement(x);
	        results.stopHere();
        }

	    // Modifiers (if after pipe)
	    PsiElement prev = curr.getPrevSibling();
	    if(prev != null && prev instanceof PsiWhiteSpace) prev = prev.getPrevSibling();
	    if(prev != null && prev.getNode().getElementType() == LatteTokenTypes.MODIFIER) {
		    for(LookupElementBuilder x: FILTER_LOOKUPS) results.addElement(x);
		    results.stopHere();
	    }
    }
}
