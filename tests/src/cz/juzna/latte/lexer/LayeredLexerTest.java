package cz.juzna.latte.lexer;

import com.intellij.lang.Language;
import com.intellij.lexer.LexerBase;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;

import java.util.ArrayList;


public class LayeredLexerTest extends AbstractLexerTest {
    public static final IElementType DUMMY_TOKEN = new IElementType("dummy", Language.ANY);

    protected MyDummyLexer dummyLexer;


    @Override
    public void setUp() throws Exception {
        super.setUp();
        lexer = new LayeredLexer(new LatteTopLexer(), dummyLexer = new MyDummyLexer(), TokenSet.create(LatteTokenTypes.PARAMS, LatteTokenTypes.N_ATTR_VALUE));
    }

    public void testLayeredLexer() throws Exception {
        LexerToken[] tokens = lex("Ahoj {if $jmeno} <a n:href=\"HomePage:signal!, id => $id\" />");

        assertEquals(2, dummyLexer.buffers.size());
        assertEquals("$jmeno", dummyLexer.buffers.get(0));
        assertEquals("HomePage:signal!, id => $id", dummyLexer.buffers.get(1));
        
        assertToken(tokens[3], LatteTokenTypes.WHITE_SPACE, null);
        assertToken(tokens[4], DUMMY_TOKEN, "$");
    }


    private class MyDummyLexer extends LexerBase {
        private CharSequence myBuffer;
        private int myStartOffset;
        private int myEndOffset;
        private int myPos;
        public ArrayList<String> buffers = new ArrayList<String>();

        @Override
        public void start(CharSequence buffer, int startOffset, int endOffset, int initialState) {
            buffers.add(buffer.subSequence(startOffset, endOffset).toString());
            myBuffer = buffer;
            myPos = myStartOffset = startOffset;
            myEndOffset = endOffset;       
        }

        @Override
        public int getState() {
            return 0; 
        }

        @Override
        public IElementType getTokenType() {
            return myPos < myEndOffset ? DUMMY_TOKEN : null;
        }

        @Override
        public int getTokenStart() {
            return myPos;
        }

        @Override
        public int getTokenEnd() {
            return myPos + 1;
        }

        @Override
        public void advance() {
            myPos++;
        }

        @Override
        public CharSequence getBufferSequence() {
            return myBuffer;
        }

        @Override
        public int getBufferEnd() {
            return myEndOffset;
        }
    }
}
