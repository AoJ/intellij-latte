package cz.juzna.latte.lexer;

import com.intellij.psi.tree.IElementType;
import cz.juzna.latte.LatteLanguage;
import org.jetbrains.annotations.NotNull;


public class LatteElementType extends IElementType {
    public LatteElementType(@NotNull String debugName) {
        super(debugName, LatteLanguage.LATTE_LANGUAGE);
    }

    public String toString() {
        return "[Latte] " + super.toString();
    }
}
