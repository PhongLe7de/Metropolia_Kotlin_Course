package pattern

interface  Observer {
    fun update(weatherData: WeatherData)
}

interface Subject {
    fun registerObserver(observer: Observer)
    fun removeObserver(observer: Observer)
    fun notifyObservers(observers: List<Observer>)
}

data class WeatherData(
    val temperature: Float,
    val humidity: Float,
    val pressure: Float
)

class WeatherStation : Subject {
    private var currentData: WeatherData? = null

    private val observers = mutableListOf<Observer>()

    fun measurementsChanged(newData: WeatherData) {
        this.currentData = newData
        println("WeatherStation: Got new data -> $currentData")
        notifyObservers(observers)
    }

    override fun registerObserver(observer: Observer) {
        observers.add(observer)
    }

    override fun removeObserver(observer: Observer) {
        observers.remove(observer)
    }

    override fun notifyObservers(observers: List<Observer>) {
        for (observer in observers){
            observer.update(currentData!!)
        }
    }
}

class CurrentConditionsDisplay: Observer {
    private var temperature: Float = 0.0f
    private var humidity: Float = 0.0f
    fun display() {
        println("CurrentConditionsDisplay: Current conditions: ${temperature}C degrees and ${humidity}% humidity")
    }

    override fun update(weatherData: WeatherData) {
        temperature = weatherData.temperature
        humidity = weatherData.humidity
        display()
    }
}

class StatisticsDisplay: Observer {
    private var temperature: Float = 0.0f
    private var humidity: Float = 0.0f
    private val temperatures = mutableListOf<Float>()

    fun display() {
        val temperatureAverage = temperatures.average().toFloat()
        println("StatisticsDisplay: Avg temperature: ${temperatureAverage}C")
    }

    override fun update(weatherData: WeatherData) {
        temperature = weatherData.temperature
        humidity = weatherData.humidity
        temperatures.add(temperature)
        display()
    }
}

fun main() {
    val weatherStation = WeatherStation()
    val currentDisplay = CurrentConditionsDisplay()
    val statsDisplay = StatisticsDisplay()

    weatherStation.registerObserver(currentDisplay)
    weatherStation.registerObserver(statsDisplay)

    println("--- Simulating new measurement ---")
    weatherStation.measurementsChanged(
        WeatherData(
            25.0f, 65f,
            1012f
        )
    )
    println("\n--- Simulating another measurement ---")
    weatherStation.measurementsChanged(
        WeatherData(
            27.5f, 70f,
            1011f
        )
    )

    println("\n--- Unregistering Statistics Display ---")
    weatherStation.removeObserver(statsDisplay)

    println("\n--- Simulating a final measurement ---")
    weatherStation.measurementsChanged(
        WeatherData(
            26.0f, 90f,
            1013f
        )
    )
}