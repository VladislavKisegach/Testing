package ru.netology

fun main() {
    val paymentSystem = choosePaymentSystem()
    val payment = Payment(paymentSystem)
    print("Введите сумму перевода в рублях: ")
    val money = readln().toInt()
    val commission = payment.pay(money, paymentSystem = paymentSystem)
    println("Ваша комиссия при переводе составит - ${formatToRuble(commission)} руб.")
}

fun printListPaymentSystem() {
    println("Выберите платежную систему согласно списка: ")
    println("1. MasterCard")
    println("2. Maestro")
    println("3. Visa")
    println("4. VK Pay")
    println("5. Мир")
}

fun choosePaymentSystem(): PaymentSystem {
    printListPaymentSystem()
    val paymentSystem: PaymentSystem = when (readln()) {
        "1" -> PaymentSystem.MasterCard
        "2" -> PaymentSystem.Maestro
        "3" -> PaymentSystem.Visa
        "4" -> PaymentSystem.VKPay
        "5" -> PaymentSystem.Mir
        else -> {
            println("Вы неверно указали платежную систему, в связи с чем будет установлена система по умолчанию - VK Pay")
            PaymentSystem.VKPay
        }
    }
    return paymentSystem
}

fun formatToRuble(commission: Int): Int {
    return commission / 100
}