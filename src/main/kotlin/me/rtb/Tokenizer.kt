package me.rtb

class Tokenizer {
    fun tokenize(s : String ) : List<Token> {
        val tokens = mutableListOf<Token>()
        var tokenState = TokenState.NONE
        var nextToken = ""

        fun startNewToken(c:Char) {

            if (c.isDigit() || c == '-') {
                nextToken = "$c"
                tokenState = TokenState.READING_NUM
            }
            else if (c.isLetter()) {
                nextToken = "$c"
                tokenState = TokenState.READING_SYMBOL
            }
            else if (c == ' ') {

            }
            else {
                tokens.add( OperatorToken(c) )
            }
        }

        fun continueReadingNum(c:Char) {
            if (c.isDigit() || c=='.') {
                nextToken += c
            }
            else if (c.isLetter()) {
                throw ParserException("identifier cannot start with a  digit")
            }
            else if (c == ' ') {
                if (nextToken == "-")
                    tokens.add(OperatorToken('-'))
                else
                    tokens.add(NumberToken(nextToken.toDouble()))

                nextToken = ""
                tokenState = TokenState.NONE
            }
            else {
                tokens.add(NumberToken(nextToken.toDouble()));
                nextToken = ""
                tokenState = TokenState.NONE
                startNewToken(c)
            }
        }

        fun continueReadingSymbol(c:Char) {
            if (c.isLetterOrDigit()) {
                nextToken += c
            }
            else if (c == ' ') {
                tokens.add(SymbolToken(nextToken));
                nextToken = ""
                tokenState = TokenState.NONE
            }
            else {
                tokens.add(SymbolToken(nextToken));
                nextToken = ""
                tokenState = TokenState.NONE
                startNewToken(c)
            }
        }

        "$s ".forEach {
            when(tokenState) {
                TokenState.NONE -> startNewToken(it)
                TokenState.READING_NUM -> continueReadingNum(it)
                TokenState.READING_SYMBOL -> continueReadingSymbol(it)
            }

        }

        return tokens
    }

    enum class TokenState {
        NONE,
        READING_NUM,
        READING_SYMBOL,
    }

    class ParserException(msg: String) : RuntimeException(msg) {}
}

