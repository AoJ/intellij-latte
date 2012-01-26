package cz.juzna.latte.lexer;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.intellij.util.containers.Stack;
import static cz.juzna.latte.lexer.LatteTokenTypes.*;

%%

%class _LatteSubLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%caseless
%eof{  return;
%eof}

%{
    private Stack<Integer> stack = new Stack<Integer>();

    public void yypushState(int newState) {
      stack.push(yystate());
      yybegin(newState);
    }

    public void yypopState() {
      yybegin(stack.pop());
    }
%}

IDENTIFIER = [a-zA-Z][a-zA-Z0-9_]*
STRING = \'(\\.|[^\'\\])*\'|\"(\\.|[^\"\\])*\"
STRING_NO_CURLY = \'(\\.|[^\'\\{}])*\'|\"(\\.|[^\"\\{}])*\"
CRLF = \n | \r | \r\n
WHITE_SPACE_CHAR = [\ \n\r\t\f]
COMMENT = "{*" ~"*}"
OPENING = "{"
CLOSING = "}"
MACRO_NAME = [^\'\"{} ]+


%%




<YYINITIAL> {
    "as"                        { return KEYWORD; }

	"|"                         { return MODIFIER; }
    {STRING}                    { return STRING; }
    {IDENTIFIER}                { return IDENTIFIER; }
    ":"                         { return COLON; }
    ";"                         { return SEMICOLON; }
    "=>"                        { return ASSIGN; }
    "$" {IDENTIFIER}            { return VARIABLE; }
    "!"                         { return EXCLAMATION; }
    ","                         { return COMMA; }
    [0-9]+("."[0-9]+)?          { return NUMBER; }

    [<>=][<>=]?[<>=]?           { return OPERATOR; }
}

{WHITE_SPACE_CHAR}+                      { return WHITE_SPACE; }
.                                        { return BAD_CHARACTER; }
