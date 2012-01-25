package cz.juzna.latte.editor;

import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.openapi.options.colors.ColorDescriptor;
import com.intellij.openapi.options.colors.ColorSettingsPage;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.Map;


/**
 * Settings dialog for colors
 */
public class LatteColorsPage implements ColorSettingsPage {
    public static final AttributesDescriptor[] ATTRS = {
            new AttributesDescriptor("colors.bad.character", LatteSyntaxHighlighter.BAD_CHARACTER),
            new AttributesDescriptor("colors.comment",       LatteSyntaxHighlighter.COMMENT),
            new AttributesDescriptor("colors.brackets",      LatteSyntaxHighlighter.BRACKETS),
            new AttributesDescriptor("colors.identifier",    LatteSyntaxHighlighter.IDENTIFIER),
            new AttributesDescriptor("colors.attribute",     LatteSyntaxHighlighter.ATTR),
            new AttributesDescriptor("colors.keyword",       LatteSyntaxHighlighter.KEYWORD),
            new AttributesDescriptor("colors.variable",      LatteSyntaxHighlighter.VARIABLE),
            new AttributesDescriptor("colors.string",        LatteSyntaxHighlighter.STRING),
            new AttributesDescriptor("colors.punctuation",   LatteSyntaxHighlighter.PUNCTUATION),
    };

    @NotNull
    @Override
    public String getDisplayName() {
        return "Latte";
    }

    @Override
    public Icon getIcon() {
        return null;
    }

    @NotNull
    @Override
    public AttributesDescriptor[] getAttributeDescriptors() {
        return ATTRS;
    }

    @NotNull
    @Override
    public ColorDescriptor[] getColorDescriptors() {
        return new ColorDescriptor[0];
    }

    @NotNull
    @Override
    public SyntaxHighlighter getHighlighter() {
        return new LatteSyntaxHighlighter();
    }

    @NotNull
    @Override
    public String getDemoText() {
        return "{* Comment *}\n<h1 n:block=\"title\">Welcome</h1>\n\n{if $cond > 1}\n\t$variable\n{/if}\n<a href=\"{link Article:detail, id => $id}\">{$title}</a><br>\n<a n:href=\"Article:detail, id => $id\">Article {!$title|nl2br}</a>\n";
    }

    @Override
    public Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap() {
        return null;
    }
}
