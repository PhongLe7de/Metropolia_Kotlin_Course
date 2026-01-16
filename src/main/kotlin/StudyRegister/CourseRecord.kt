package org.example.StudyRegister

open class CourseRecord(
    name: String,
    yearComplete: Int,
    credits: Int,
    grade: Double
) {
    var name : String = name
        private set
    var yearComplete : Int = yearComplete
        private set
    var credits :Int = credits
        private set
    var grade : Double = grade
        private set
}