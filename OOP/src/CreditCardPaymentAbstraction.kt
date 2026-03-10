class CreditCardPaymentAbstraction() : PaymentAbstraction {
    override fun pay(amount: Double) {
        println("Payment with credit card: "+amount)
    }
}