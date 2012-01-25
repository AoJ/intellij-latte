package cz.juzna.latte.lexer;


import com.intellij.lexer.Lexer;
import com.intellij.psi.tree.IElementType;

public class Test {
    
    public static void main(String[] args) throws Exception {
        String str = "Ahoj {* tady bude jmeno *} {$jmeno} :) <a href=\"{link Dashboard:signal!, id => $id}\" n:href=\"Novak:detail!\" title=\"lol\">novak</a> {* a tady je konec *}";
        System.out.println(str);

        IElementType el;

        Lexer merge = new LatteLexer();
        merge.start(str);

        while((el = merge.getTokenType()) != null) {
            System.out.printf("%s: %d %d '%s'\n", el.toString(), merge.getTokenStart(), merge.getTokenEnd(), str.substring(merge.getTokenStart(), merge.getTokenEnd()));
            merge.advance();
        }
    }
}
