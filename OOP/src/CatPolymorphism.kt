class CatPolymorphism() : AnimalPolymorphism() {

    override fun makeSound() {
        println("Meow!!")
    }

    fun eat(eat: String) {
        println(eat)
    }

    fun eat(eat: String, eatALot: String) {
        println(eat+" "+eatALot)
    }
}