package co.bk.kotlinskills.sandbox.data

// data class provides the automatic generation of common functions such as equals(), hashCode(), and toString(),
data class Lamp(val id: Int, val colour: String, val model: String, val power: Int) {

    private var isOn: Boolean = false

    fun turnOn() {
        isOn = true
    }

    fun turnOff() {
        isOn = false
    }

    fun displayLightStatus(lamp: String) {
        if (isOn == true)
            println("$lamp lamp is on.")
        else
            println("$lamp lamp is off.")
    }
}
