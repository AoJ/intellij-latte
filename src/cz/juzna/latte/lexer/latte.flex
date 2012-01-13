package cz.juzna.latte.lexer;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import static cz.juzna.latte.lexer.LatteTokenTypes.*;

%%

%class _LatteLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{  return;
%eof}

STRING = \'(\\.|[^\'\\])*\'|\"(\\.|[^\"\\])*\"
CRLF = \n | \r | \r\n
WHITE_SPACE_CHAR = [\ \n\r\t\f]
COMMENT = "{*" ~"*}"
OPENING = "{"
CLOSING = "}"
MACRO_NAME = [^\'\"{} ]+


%state AFTER_LD
%state IN_LATTE
%state IN_TAG
%state TAG_STARTED
%state ATTR_NAME
%state IN_ATTR_VALUE

%%




<YYINITIAL> {
    {COMMENT}                    { yybegin(YYINITIAL); return COMMENT; }
    {OPENING}/[^\s\'\"{}]        { yybegin(AFTER_LD); return OPENING; }
//    "</"                         { yybegin(TAG_STARTED); return HTML_TEXT; /* TAG_CLOSING; */ }
//    "<" / [a-z0-9:]              { yybegin(TAG_STARTED); return HTML_TEXT; /* TAG_START; */ }
//    [^{<]+                       { return HTML_TEXT; }
    [^{}]+                       { return HTML_TEXT; }
}

<AFTER_LD> {
    {MACRO_NAME}                  { yybegin(IN_LATTE); return MACRO_NAME; }
    {CLOSING}                     { yybegin(YYINITIAL); return CLOSING; }
}

<IN_LATTE> {
    [^\}]+                        { return LATTE_PARAMS; }
    {CLOSING}                   { yybegin(YYINITIAL); return CLOSING; }
}

<TAG_STARTED> {
    [a-z0-9:]+                  { yybegin(IN_TAG); return HTML_TEXT; /* TAG_NAME; */ }
}

<IN_TAG> {
    "n:" [a-z0-9]+              { yybegin(ATTR_NAME); return N_ATTR; }
    [a-z0-9]+(={STRING})?       { return HTML_TEXT; }

    ">"                         { yybegin(YYINITIAL); return HTML_TEXT; /* END_TAG;*/ }
}

<ATTR_NAME> {
    "="                         { yybegin(IN_ATTR_VALUE); return HTML_TEXT; }
}

<IN_ATTR_VALUE> {
    {STRING}                    { yybegin(IN_TAG); return ATTR_VALUE; }
}



{WHITE_SPACE_CHAR}+                      { return WHITE_SPACE; }
.                                        { return BAD_CHARACTER; }
