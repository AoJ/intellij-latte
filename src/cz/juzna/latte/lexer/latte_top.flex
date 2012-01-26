package cz.juzna.latte.lexer;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.intellij.util.containers.Stack;
import static cz.juzna.latte.lexer.LatteTokenTypes.*;

%%

%class _LatteTopLexer
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


%state MACRO_STARTED
%state TAG_STARTED
%state CLOSING_TAG_STARTED
%state IN_TAG
%state IN_MACRO
%state N_ATTR_STARTED
%state AFTER_ATTR_NAME
%state ATTR_VALUE
%state ATTR_VALUE_SIMPLE
%state ATTR_VALUE_SINGLE
%state ATTR_VALUE_DOUBLE



%%




<YYINITIAL> {
	"<!--" .* "-->"              { return HTML_TEXT; }
    "</"                         { yypushState(CLOSING_TAG_STARTED); return HTML_TEXT; /* TAG_CLOSING; */ }
    "<" / [!a-zA-Z0-9:]          { yypushState(TAG_STARTED); return HTML_TEXT; /* TAG_START; */ }
    [{}<>] / {WHITE_SPACE_CHAR}  { return HTML_TEXT; }
    "{}"                         { return HTML_TEXT; }
    [^{<]+                       { return HTML_TEXT; }
}

<YYINITIAL, ATTR_VALUE_SIMPLE, ATTR_VALUE_SINGLE, ATTR_VALUE_DOUBLE> {
    {OPENING}/[^\s\'\"{}]        { yypushState(MACRO_STARTED);    return OPENING; }
}

<MACRO_STARTED> {
    {MACRO_NAME}                  { yybegin(IN_MACRO); return MACRO_NAME; }
    {CLOSING}                     { yypopState(); return CLOSING; }
}

<IN_MACRO> {
    {WHITE_SPACE_CHAR}+         { return WHITE_SPACE; }
    "!"                         { return EXCLAMATION; }
    [^!} ][^}]*                  { return PARAMS; }
    //{STRING}                    { return STRING; }
}

/*<IN_MACRO, ATTR_VALUE_SIMPLE, ATTR_VALUE_SINGLE, ATTR_VALUE_DOUBLE> {
    {IDENTIFIER}                { return IDENTIFIER; }
    ":"                         { return COLON; }
    ";"                         { return SEMICOLON; }
    "=>"                        { return ASSIGN; }
    "$" {IDENTIFIER}            { return VARIABLE; }
    "!"                         { return EXCLAMATION; }
    ","                         { return COMMA; }
    [0-9]+("."[0-9]+)?          { return NUMBER; }
}
  */

<IN_MACRO> {
    {CLOSING}                   { yypopState(); return CLOSING; }
}

<TAG_STARTED, CLOSING_TAG_STARTED> {
    [-!a-zA-Z0-9:]+               { yybegin(IN_TAG); return TAG_NAME; }
}

<IN_TAG> {
    "n:" / [a-zA-Z0-9]          { yybegin(N_ATTR_STARTED); return N_ATTR; }
}

<IN_TAG, N_ATTR_STARTED> {
    [-a-zA-Z0-9_]+?             { yybegin(AFTER_ATTR_NAME); return ATTR_NAME; }
}

<IN_TAG, N_ATTR_STARTED, AFTER_ATTR_NAME> {
    {WHITE_SPACE_CHAR}+         { return HTML_TEXT; }
    "/" {WHITE_SPACE_CHAR}* ">" { yypopState(); return HTML_TEXT; }
    ">"                         { yypopState(); return HTML_TEXT; /* TAG_CLOSING; */ }
}


<AFTER_ATTR_NAME> {
    "=" / ['\"]                 { yybegin(ATTR_VALUE); return N_ATTR_EQ; }
    "="                         { yybegin(ATTR_VALUE_SIMPLE); return N_ATTR_EQ; }
}

<ATTR_VALUE> {
    "\""                        { yybegin(ATTR_VALUE_DOUBLE); return N_QUOTE; }
    "\'"                        { yybegin(ATTR_VALUE_SINGLE); return N_QUOTE; }
}

<ATTR_VALUE_SIMPLE> {
    [^ {}]+                     { return N_ATTR_VALUE; }
    ">"                         { yypopState(); return HTML_TEXT; /*TAG_CLOSING;*/ }
    " "                         { yybegin(IN_TAG); return HTML_TEXT; /*WHITE_SPACE*/ }
}

<ATTR_VALUE_SINGLE> {
    [^'{}]+                     { return N_ATTR_VALUE; }
    "'"                         { yybegin(IN_TAG); return N_QUOTE; }
}

<ATTR_VALUE_DOUBLE> {
    [^\"{}]+                    { return N_ATTR_VALUE; }
    "\""                        { yybegin(IN_TAG); return N_QUOTE; }
}



{COMMENT}                                { return COMMENT; }
{WHITE_SPACE_CHAR}+                      { return WHITE_SPACE; }
.                                        { return BAD_CHARACTER; }
