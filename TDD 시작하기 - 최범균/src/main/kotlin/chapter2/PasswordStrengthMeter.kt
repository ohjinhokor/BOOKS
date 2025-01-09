package chapter2

class PasswordStrengthMeter {
    fun meter(password: String): PasswordStrength? {
        if (password.isBlank()) {
            return PasswordStrength.INVALID
        }
        val metCounts = getMetCounts(password)
        if (metCounts <= 1) {
            return PasswordStrength.WEAK
        }
        if (metCounts == 2) {
            return PasswordStrength.NORMAL
        }
        return PasswordStrength.STRONG
    }

    private fun getMetCounts(password: String): Int {
        var metCounts = 0
        if (password.length >= 8) metCounts++
        if (meetsContainingNumberCriteria(password = password)) metCounts++
        if (meetsContainingUppercaseCriteria(password = password)) metCounts++
        return metCounts
    }

    private fun meetsContainingUppercaseCriteria(password: String): Boolean {
        password.toCharArray().forEach {
            if (it.isUpperCase()) return true
        }
        return false
    }

    private fun meetsContainingNumberCriteria(password: String): Boolean {
        password.toCharArray().forEach {
            if (it in '1'..'8') {
                return true
            }
        }
        return false
    }
}