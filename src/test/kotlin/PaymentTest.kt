package ru.netology

import org.junit.Assert.assertEquals
import org.junit.Test

class PaymentTest {

    @Test
    fun payByMasterCardOrMaestro_wherePayLessThenMinPay() {
        val lastPay = 0
        val money = 200
        val paymentSystem = PaymentSystem.MasterCard
        val payment = Payment(paymentSystem)
        val expectedCommission = 140
        val commission = payment.pay(money, lastPay, paymentSystem)
        assertEquals(expectedCommission, format(commission))
    }

    @Test
    fun payByVisaOrMir_Test() {
        val lastPay = 0
        val money = 2_000
        val paymentSystem = PaymentSystem.Mir
        val payment = Payment(paymentSystem)
        val expectedCommission = 1_500
        val commission = payment.pay(money, lastPay, paymentSystem)
        assertEquals(expectedCommission, format(commission))
    }

    @Test
    fun payByVK_Test() {
        val lastPay = 0
        val money = 2_000
        val paymentSystem = PaymentSystem.VKPay
        val payment = Payment(paymentSystem)
        val expectedCommission = 0
        val commission = payment.pay(money, lastPay, paymentSystem)
        assertEquals(expectedCommission, format(commission))
    }

    @Test
    fun payByVK_OverMaxLimit() {
        val lastPay = 0
        val money = 16_000
        val paymentSystem = PaymentSystem.VKPay
        val payment = Payment(paymentSystem)
        val expectedCommission = 0
        val commission = payment.pay(money, lastPay, paymentSystem)
        assertEquals(expectedCommission, format(commission))
    }

    @Test
    fun payByVisaOrMir_ifCommissionLessThenMinCommission() {
        val lastPay = 0
        val money = 20
        val paymentSystem = PaymentSystem.Mir
        val payment = Payment(paymentSystem)
        val expectedCommission = 35
        val commission = payment.pay(money, lastPay, paymentSystem)
        assertEquals(expectedCommission, format(commission))
    }

    @Test
    fun payByVK_OverMaxLimitByLastPay() {
        val lastPay = 45_000_00
        val money = 6_000
        val paymentSystem = PaymentSystem.VKPay
        val payment = Payment(paymentSystem)
        val expectedCommission = 0
        val commission = payment.pay(money, lastPay, paymentSystem)
        assertEquals(expectedCommission, format(commission))
    }

    @Test
    fun payByMasterCardOrMaestro_Test() {
        val lastPay = 0
        val money = 2_000
        val paymentSystem = PaymentSystem.MasterCard
        val payment = Payment(paymentSystem)
        val expectedCommission = 0
        val commission = payment.pay(money, lastPay, paymentSystem)
        assertEquals(expectedCommission, format(commission))
    }

    @Test
    fun payByMasterCardOrMaestro_payOverLimit() {
        val lastPay = 75_000_00
        val money = 2_000
        val paymentSystem = PaymentSystem.MasterCard
        val payment = Payment(paymentSystem)
        val expectedCommission = 1_220
        val commission = payment.pay(money, lastPay, paymentSystem)
        assertEquals(expectedCommission, format(commission))
    }

    private fun format(commission: Int): Int {
        return commission / 100
    }
}