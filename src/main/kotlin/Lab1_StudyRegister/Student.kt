package org.example.StudyRegister

class Student(
    name: String,
    age: Int,
): Human(name, age) {
    private val course: MutableList<CourseRecord> = mutableListOf()

    fun addCourse(courseRecord: CourseRecord){
        course.add(courseRecord)
    }

    fun getCourses(): List<CourseRecord> {
        return course
    }

    fun weightedAverage(): Double {
        var totalCredit = 0
        var totalGrade = 0.0

        for (c in course) {
            totalCredit += c.credits
            totalGrade += (c.grade * c.credits)
        }
        return totalGrade / totalCredit
    }


    fun weightedAverage(year: Int) : Double {
        var totalCredit = 0
        var totalGrade = 0.0

        for (c in course) {
            if (c.yearComplete == year) {
                totalCredit += c.credits
                totalGrade += c.grade
            }
        }
        return totalGrade / totalCredit
    }

    fun minMaxGrades(): Pair<Double, Double> {
        var minGrade: Double = Double.MAX_VALUE
        var maxGrade: Double = Double.MIN_VALUE

        for (c in course) {
            if (c.grade < minGrade) {
                minGrade = c.grade
            }
            if (c.grade > maxGrade) {
                maxGrade = c.grade
            }
        }
        return Pair(minGrade, maxGrade)
    }

    fun minMaxGradesByCourse(courseName: String): Pair<Double, Double> {
        var minGrade: Double = Double.MAX_VALUE
        var maxGrade: Double = Double.MIN_VALUE

        for (c in course) {
            if(c.name == courseName) {
                if (c.grade < minGrade) {
                    minGrade = c.grade
                }
                if (c.grade > maxGrade) {
                    maxGrade = c.grade
                }
            }

        }
        return Pair(minGrade, maxGrade)
    }
}