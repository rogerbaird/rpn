package me.rtb

import org.junit.Assert.*
import org.junit.Test

class TokenizerTest {

    @Test
    fun `empty`() {
        val tokens  = Tokenizer().tokenize("")
        assertTrue(tokens.isEmpty())
    }

    @Test
    fun `number single digit`() {
        IntRange(0,9).map(Int::toString).forEach(::testNumber)
    }

    @Test
    fun `number multiple digit`() {
        IntRange(100,10000).map(Int::toString).forEach(::testNumber)
    }

    @Test
    fun `number with decimal`() {
        testNumber("0.01")
    }

    @Test
    fun `number ending with decimal`() {
        testNumber("0.")
    }

    @Test
    fun `negative number single digit`() {
        testNumber("-1")
    }

    @Test
    fun `negative number multiple digit`() {
        testNumber("-123")
    }

    @Test
    fun `negative decimal number multiple digit`() {
        testNumber("-12.34")
    }

    @Test(expected = NumberFormatException::class)
    fun `multiple decimals`() {
        testNumber("1.2.3")
    }

    /// --- symbols

    @Test
    fun `symbol`() {
        Tokenizer().tokenize("foo").also {
            assertEquals(1, it.size)
            assertEquals(SymbolToken("foo"), it[0])
        }
    }

    @Test
    fun `symbol with digits`() {
        Tokenizer().tokenize("foo11").also {
            assertEquals(1, it.size)
            assertEquals(SymbolToken("foo11"), it[0])
        }
    }

    @Test
    fun `number then symbol`() {
        Tokenizer().tokenize("11 foo").also {
            assertEquals(2, it.size)
            assertEquals(NumberToken("11".toDouble()), it[0])
            assertEquals(SymbolToken("foo"), it[1])
        }
    }

    @Test(expected = Tokenizer.ParserException::class)
    fun `illegal symbol`() {
        Tokenizer().tokenize("113foo")
    }

    ///---------- operations
    @Test
    fun `number with operation`() {
        Tokenizer().tokenize("12-").also {
            assertEquals(2, it.size)
            assertEquals(NumberToken("12".toDouble()), it[0])
            assertEquals(OperatorToken('-'), it[1])
        }
    }

    @Test
    fun `two numbers with operation`() {
        Tokenizer().tokenize(" 10 12-").also {
            assertEquals(3, it.size)
            assertEquals(NumberToken("10".toDouble()), it[0])
            assertEquals(NumberToken("12".toDouble()), it[1])
            assertEquals(OperatorToken('-'), it[2])
        }
    }

    @Test
    fun `mix numbers with operation`() {
        Tokenizer().tokenize(" 10 12 + 3.3").also {
            assertEquals(4, it.size)
            assertEquals(NumberToken("10".toDouble()), it[0])
            assertEquals(NumberToken("12".toDouble()), it[1])
            assertEquals(OperatorToken('+'), it[2])
            assertEquals(NumberToken("3.3".toDouble()), it[3])

        }
    }

    @Test
    fun `number then symbol then op`() {
        Tokenizer().tokenize("11 foo+").also {
            assertEquals(3, it.size)
            assertEquals(NumberToken("11".toDouble()), it[0])
            assertEquals(SymbolToken("foo"), it[1])
            assertEquals(OperatorToken('+'), it[2])
        }
    }

    private fun testNumber(s:String) {
        val tokens = Tokenizer().tokenize(s)
        assertEquals(1, tokens.size)
        assertEquals(NumberToken(s.toDouble()), tokens[0])
    }
}