import com.intellij.lang.LanguageASTFactory;
import com.intellij.openapi.application.PluginPathManager;
import com.intellij.psi.PsiFile;
import com.intellij.testFramework.ParsingTestCase;
import com.intellij.testFramework.PlatformTestCase;
import cz.juzna.latte.LatteLanguage;
import cz.juzna.latte.parser.LatteParserDefinition;

public class LatteParsingTest extends ParsingTestCase {
    public LatteParsingTest() {
        super("", "latte", new LatteParserDefinition());
        PlatformTestCase.initPlatformLangPrefix();
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        //addExplicitExtension(LanguageASTFactory.INSTANCE, LatteLanguage.LATTE_LANGUAGE, );
    }

    @Override
    protected String getTestDataPath() {
        return "tests/data";
    }
    
    public void testSample1() throws Exception { doTest(true); }
}
