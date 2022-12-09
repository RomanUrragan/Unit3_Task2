import java.lang.Integer.max

const val TYPE_MASTERCARD = "Mastercard"
const val TYPE_MAESTRO = "Maestro"
const val TYPE_VISA = "Visa"
const val TYPE_MIR = "Мир"
const val TYPE_VK_PAY = "VK Pay"
const val MAX_TRANSFER_WITHOUT_COMMISSION = 75_000
const val MIN_COMMISSION_FOR_ViSA_AND_MIR = 35
const val MAX_LIMIT_PER_DAY_FOR_CARDS = 150_000
const val MAX_LIMIT_PER_TIME_FOR_VK_PAY = 15_000
const val MAX_LIMIT_PER_MONTH_FOR_CARDS = 600_000
const val MAX_LIMIT_PER_MONTH_FOR_VK_PAY = 40_000

fun main() {
    var transferAmount = 20_000
    var previousAmountPerMonth = 23_000
    var previousAmountPerDay = 15_000

    println("Совершен перевод на сумму " +
            (transferAmount - calculateCommission(TYPE_VISA, previousAmountPerDay, previousAmountPerMonth, transferAmount))
            + "р c учетом комиссии")
}

fun calculateCommission(
    cardType: String,
    previousAmountPerDay: Int,
    previousAmountPerMonth: Int,
    transferAmount: Int
): Int {
    return when {
        dailyLimitIsReached(cardType, previousAmountPerDay, transferAmount) -> {
            throw Exception("Превышен лимит перевода за раз / за сутки")
        }
        monthlyLimitIsReached(cardType, previousAmountPerMonth, transferAmount) -> {
            throw Exception("Превышен лимит перевода за месяц")
        }
        else -> {
            when {
                (cardType == TYPE_MAESTRO || cardType == TYPE_MASTERCARD) && transferAmount > MAX_TRANSFER_WITHOUT_COMMISSION -> {
                    (transferAmount * 0.006 + 20).toInt()
                }
                cardType == TYPE_MIR || cardType == TYPE_VISA -> {
                    max(MIN_COMMISSION_FOR_ViSA_AND_MIR, (transferAmount * 0.0075).toInt())
                }
                else -> 0
            }
        }
    }
}

fun dailyLimitIsReached(cardType: String, previousAmountPerDay: Int, transferAmount: Int): Boolean {
    return when (cardType) {
        TYPE_VK_PAY -> {
            (transferAmount > MAX_LIMIT_PER_TIME_FOR_VK_PAY)
        }
        else -> (previousAmountPerDay + transferAmount > MAX_LIMIT_PER_DAY_FOR_CARDS)
    }
}

fun monthlyLimitIsReached(cardType: String, previousAmount: Int, transferAmount: Int): Boolean {
    return when (cardType) {
        TYPE_VK_PAY -> {
            (previousAmount + transferAmount > MAX_LIMIT_PER_MONTH_FOR_VK_PAY)
        }
        else -> (previousAmount + transferAmount > MAX_LIMIT_PER_MONTH_FOR_CARDS)
    }
}