class CarAbstraction(brand: String) : VehicleAbstraction(brand) {
    override fun accelarete() {
        println("Gasoline engine")
    }

}