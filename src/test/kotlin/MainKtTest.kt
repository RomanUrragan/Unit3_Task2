import org.junit.Test

import org.junit.Assert.*

class MainKtTest {

    @Test
    fun calculateZeroCommissionForMasterCard() {
        var cardType = TYPE_MASTERCARD
        var previousAmountPerDay = 0
        var previousAmountPerMonth = 0
        var transferAmount = 1000

        val result = calculateCommission(cardType, previousAmountPerDay, previousAmountPerMonth, transferAmount)
        assertEquals(0, result)
    }

    @Test
    fun calculateZeroCommissionForMaestro() {
        var cardType = TYPE_MASTERCARD
        var previousAmountPerDay = 0
        var previousAmountPerMonth = 0
        var transferAmount = 1000

        val result = calculateCommission(cardType, previousAmountPerDay, previousAmountPerMonth, transferAmount)
        assertEquals(0, result)
    }

    @Test
    fun calculateZeroCommissionForVKPay() {
        var cardType = TYPE_VK_PAY
        var previousAmountPerDay = 0
        var previousAmountPerMonth = 0
        var transferAmount = 1000

        val result = calculateCommission(cardType, previousAmountPerDay, previousAmountPerMonth, transferAmount)
        assertEquals(0, result)
    }

    @Test
    fun calculateCommissionForMasterCard() {
        var cardType = TYPE_MASTERCARD
        var previousAmountPerDay = 0
        var previousAmountPerMonth = 0
        var transferAmount = 76_000

        val result = calculateCommission(cardType, previousAmountPerDay, previousAmountPerMonth, transferAmount)
        assertEquals(476, result)
    }
    @Test
    fun calculateCommissionForMaestro() {
        var cardType = TYPE_MAESTRO
        var previousAmountPerDay = 0
        var previousAmountPerMonth = 0
        var transferAmount = 76_000

        val result = calculateCommission(cardType, previousAmountPerDay, previousAmountPerMonth, transferAmount)
        assertEquals(476, result)
    }

    @Test
    fun calculateMinCommissionForMir() {
        var cardType = TYPE_MIR
        var previousAmountPerDay = 0
        var previousAmountPerMonth = 0
        var transferAmount = 1000

        val result = calculateCommission(cardType, previousAmountPerDay, previousAmountPerMonth, transferAmount)
        assertEquals(MIN_COMMISSION_FOR_ViSA_AND_MIR, result)
    }

    @Test
    fun calculateMinCommissionForVisa() {
        var cardType = TYPE_VISA
        var previousAmountPerDay = 0
        var previousAmountPerMonth = 0
        var transferAmount = 1000

        val result = calculateCommission(cardType, previousAmountPerDay, previousAmountPerMonth, transferAmount)
        assertEquals(MIN_COMMISSION_FOR_ViSA_AND_MIR, result)
    }

    @Test
    fun calculateCommissionForMir() {
        var cardType = TYPE_MIR
        var previousAmountPerDay = 0
        var previousAmountPerMonth = 0
        var transferAmount = 100_000

        val result = calculateCommission(cardType, previousAmountPerDay, previousAmountPerMonth, transferAmount)
        assertEquals(750, result)
    }

    @Test
    fun calculateCommissionForVISA() {
        var cardType = TYPE_VISA
        var previousAmountPerDay = 0
        var previousAmountPerMonth = 0
        var transferAmount = 100_000

        val result = calculateCommission(cardType, previousAmountPerDay, previousAmountPerMonth, transferAmount)
        assertEquals(750, result)
    }

    @Test
    fun dailyLimitIsReachedVkPay(){
        var cardType = TYPE_VK_PAY
        var previousAmountPerDay = 0
        var transferAmount = MAX_LIMIT_PER_TIME_FOR_VK_PAY + 1

        val result = dailyLimitIsReached(cardType, previousAmountPerDay, transferAmount)
        assert(result)
    }
    @Test
    fun dailyLimitIsNotReachedVkPay(){
        var cardType = TYPE_VK_PAY
        var previousAmountPerDay = 0
        var transferAmount = MAX_LIMIT_PER_TIME_FOR_VK_PAY - 1

        val result = dailyLimitIsReached(cardType, previousAmountPerDay, transferAmount)
        assert(!result)
    }

    @Test
    fun dailyLimitIsReachedMasterCard(){
        var cardType = TYPE_MASTERCARD
        var previousAmountPerDay = MAX_LIMIT_PER_DAY_FOR_CARDS
        var transferAmount = 1

        val result = dailyLimitIsReached(cardType, previousAmountPerDay, transferAmount)
        assert(result)
    }

    @Test
    fun dailyLimitIsNotReachedMasterCard(){
        var cardType = TYPE_MASTERCARD
        var previousAmountPerDay = 0
        var transferAmount = 15_000

        val result = dailyLimitIsReached(cardType, previousAmountPerDay, transferAmount)
        assert(!result)
    }

    @Test
    fun monthlyLimitIsReachedVkPay(){
        var cardType = TYPE_VK_PAY
        var previousAmountPerMonth = MAX_LIMIT_PER_MONTH_FOR_VK_PAY
        var transferAmount = 1

        val result = monthlyLimitIsReached(cardType, previousAmountPerMonth, transferAmount)
        assert(result)
    }

    @Test
    fun monthlyLimitIsNotReachedVkPay(){
        var cardType = TYPE_VK_PAY
        var previousAmountPerMonth = 0
        var transferAmount = 10_000

        val result = monthlyLimitIsReached(cardType, previousAmountPerMonth, transferAmount)
        assert(!result)
    }

    @Test
    fun monthlyLimitIsReachedMasterCard(){
        var cardType = TYPE_MASTERCARD
        var previousAmountPerMonth = MAX_LIMIT_PER_MONTH_FOR_CARDS
        var transferAmount = 1

        val result = monthlyLimitIsReached(cardType, previousAmountPerMonth, transferAmount)
        assert(result)
    }

    @Test
    fun monthlyLimitIsNotReachedMasterCard(){
        var cardType = TYPE_MASTERCARD
        var previousAmountPerMonth = 0
        var transferAmount = 1_000

        val result = monthlyLimitIsReached(cardType, previousAmountPerMonth, transferAmount)
        assert(!result)
    }
}