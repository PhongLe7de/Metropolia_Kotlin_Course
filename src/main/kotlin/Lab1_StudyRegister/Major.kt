package org.example.StudyRegister

class Major(
    val name: String
) {
    private val students: MutableList<Student> = mutableListOf()

    fun addStudent(student: Student){
        students.add(student)
    }

    fun stats() : Triple<Double, Double, Double> {
        var majorMinGrade: Double = Double.MAX_VALUE
        var majorMaxGrade: Double = Double.MIN_VALUE
        var totalAverage = 0.0
        for (s in students) {
            val (minGrade, maxGrade) = s.minMaxGrades()
            if (minGrade < majorMinGrade) {
                majorMinGrade = minGrade
            }
            if (maxGrade > majorMaxGrade) {
                majorMaxGrade = maxGrade
            }
            totalAverage += s.weightedAverage()
        }

        return Triple(majorMinGrade, majorMaxGrade, totalAverage/students.size)
    }

    fun stats(courseName: String): Triple<Double, Double, Double> {
        var majorMinGrade: Double = Double.MAX_VALUE
        var majorMaxGrade: Double = Double.MIN_VALUE
        var totalAverage = 0.0
        for (s in students) {
            for (c in s.getCourses()) {
                if (c.name == courseName) {
                    val (minGrade, maxGrade) = s.minMaxGradesByCourse(courseName)
                    if (minGrade < majorMinGrade) {
                        majorMinGrade = minGrade
                    }
                    if (maxGrade > majorMaxGrade) {
                        majorMaxGrade = maxGrade
                    }
                    totalAverage += c.grade
                }
            }
        }
        return Triple(majorMinGrade, majorMaxGrade, totalAverage/students.size)

    }
}
