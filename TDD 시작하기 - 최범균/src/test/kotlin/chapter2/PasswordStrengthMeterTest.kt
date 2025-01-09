package chapter2

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class PasswordStrengthMeterTest {
    @Test
    fun meetsAllCriteria_Then_Strong() {
        assertStrength("ab12!@AB", PasswordStrength.STRONG)
        assertStrength("abc1!Add", PasswordStrength.STRONG)
    }

    @Test
    fun meetsOtherCriteria_except_for_Length_Then_Normal() {
        assertStrength("ab12!@A", PasswordStrength.NORMAL)
    }

    @Test
    fun meetsOtherCriteria_except_for_number_Then_Normal() {
        assertStrength("ab!@ABqwre", PasswordStrength.NORMAL)
    }

    @Test
    fun emptyInput_Then_Invalid() {
        assertStrength("", PasswordStrength.INVALID)
    }

    @Test
    fun meetsOtherCriteria_except_for_Uppercase_Then_Normal() {
        assertStrength("ab12!@df", PasswordStrength.NORMAL)
    }

    @Test
    fun meetsOnlyLengthCriteria_Then_Weak() {
        assertStrength("abdefghi", PasswordStrength.WEAK)
    }

    @Test
    fun meetsOnlyUpperCriteria_Then_Weak() {
        assertStrength("ABSJD", PasswordStrength.WEAK)
    }

    @Test
    fun meetsNoCriteria_Then_Weak() {
        assertStrength("abc", PasswordStrength.WEAK)
    }

    fun assertStrength(password: String, expStr: PasswordStrength) {
        val meter = PasswordStrengthMeter()
        assertEquals(meter.meter(password), expStr)
    }
}