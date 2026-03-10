abstract class VehicleAbstraction(val brand: String) {

    fun start() {
        println("$brand is starting!!!")
    }

    abstract fun accelarete();
}