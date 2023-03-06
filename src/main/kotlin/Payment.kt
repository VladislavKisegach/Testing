package ru.netology

private const val REFORMAT_RUBLE = 100
private const val PERCENT_VISA_MIR = 0.75
private const val MIN_COMMISSION_VISA_MIR = 3500
private const val LIMIT_MASTER_MAESTRO = 75_000_00
private const val MIN_COMMISSION = 0
private const val PERCENT_MASTER_MAESTRO = 0.6
private const val COMMISSION_MASTER_MAESTRO = 2_000
private const val MIN_PAY_MASTER_MAESTRO = 30_000

class Payment(private val paymentSystem: PaymentSystem) {

    fun pay(money: Int, lastPay: Int = 0, paymentSystem: PaymentSystem = PaymentSystem.VKPay): Int {
        return when (paymentSystem) {
            PaymentSystem.MasterCard, PaymentSystem.Maestro -> {
                payByMasterCardOrMaestro(money, lastPay)
            }
            PaymentSystem.VKPay -> {
                payByVKPay(money, lastPay)
            }
            PaymentSystem.Visa, PaymentSystem.Mir -> {
                payByVisaOrMir(money)
            }
        }
    }

    private fun payByMasterCardOrMaestro(money: Int, lastPay: Int): Int {
        return if (lastPay + money * REFORMAT_RUBLE < LIMIT_MASTER_MAESTRO && money * REFORMAT_RUBLE > MIN_PAY_MASTER_MAESTRO) MIN_COMMISSION
        else (money * REFORMAT_RUBLE * PERCENT_MASTER_MAESTRO).toInt() + COMMISSION_MASTER_MAESTRO
    }

    private fun payByVKPay(money: Int, lastPay: Int): Int {
        return if (money * REFORMAT_RUBLE < 15_000_00 && money * REFORMAT_RUBLE + lastPay < 40_000_00) MIN_COMMISSION
        else {
            println("Вы превысили лимит по переводу денежных средств, воспользуйтесь другой платежной системой")
            MIN_COMMISSION
        }
    }

    private fun payByVisaOrMir(money: Int): Int {
        return if (money * REFORMAT_RUBLE * PERCENT_VISA_MIR > MIN_COMMISSION_VISA_MIR) (money * REFORMAT_RUBLE * PERCENT_VISA_MIR).toInt()
        else MIN_COMMISSION_VISA_MIR
    }
}