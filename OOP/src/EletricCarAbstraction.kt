class EletricCarAbstraction(brand: String) : VehicleAbstraction(brand) {



    override fun accelarete() {
        println("Electric engine!!!")
    }
}