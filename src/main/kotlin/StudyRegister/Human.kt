package org.example.StudyRegister

open class Human (
    val name : String,
    age: Int
){
    var age : Int  = age
        private set

    fun getOlder(){
        age ++
    }
}