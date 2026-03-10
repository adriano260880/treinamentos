class BankAccountEncapsulation() {
    private var _balance: Double = 0.0

    var balance: Double
        get() = _balance
        private set(value) {
            if (value >= 0) {
                _balance = value
            } else {
                println("Cannot be negative!!!")
            }
        }
    fun deposit(amount: Double) {
        if (amount > 0) {
            balance += amount
        }
    }

}