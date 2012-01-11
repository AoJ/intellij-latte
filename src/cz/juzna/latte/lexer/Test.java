package cz.juzna.latte.lexer;


import com.intellij.lexer.Lexer;
import com.intellij.psi.tree.IElementType;

import java.io.Reader;
import java.io.StringReader;

public class Test {
    
    public static void main(String[] args) throws Exception {
        String str = "Ahoj {* tady bude jmeno *} {$jmeno} :) <a n:href=\"pepa\" title=\"lol\">novak</a> {* a tady je konec *}";
        System.out.println(str);

        _LatteLexer lexer = new _LatteLexer((Reader) null);
        IElementType el;

        lexer.reset(str, 0, str.length(), _LatteLexer.YYINITIAL);
 
        while((el = lexer.advance()) != null) {
            System.out.printf("%s: %d %d '%s'\n", el.toString(), lexer.getTokenStart(), lexer.getTokenEnd(), str.substring(lexer.getTokenStart(), lexer.getTokenEnd()));
        }
    }
}
