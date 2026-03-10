//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {

    val account = BankAccountEncapsulation()
    account.deposit(100.0)

    println(account.balance)

    val dog = DogInheritance()
    println(dog.eat())

    val cat = CatPolymorphism()
    val cat2 = AnimalPolymorphism()
    println(cat.makeSound())
    println(cat2.makeSound())

    println(cat.eat("Eat!!!"))
    println(cat.eat("Eat", "A Lot!!!"))

    val creditCard: PaymentAbstraction = CreditCardPaymentAbstraction()
    println(creditCard.pay(100.0))

    val car = CarAbstraction("Toyota")
    val tesla = EletricCarAbstraction("Tesla")

    car.start()
    car.accelarete()
    tesla.start()
    tesla.accelarete()
}