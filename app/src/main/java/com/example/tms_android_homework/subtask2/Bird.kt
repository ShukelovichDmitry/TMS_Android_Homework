package com.example.tms_android_homework.subtask2

open class Bird {
}

interface FlyingBird {
    fun fly() {
        println("Flying...")
    }
}

class Ostrich : Bird() {
}

class Penguin : Bird() {
}

open class Pigeon : Bird(), FlyingBird {
    override fun fly() {
        println("Flying Pigeon")
    }
}

open class Eagle : Bird(), FlyingBird {
    override fun fly() {
        println("Flying Eagle")
    }
}

fun letBirdFly(bird: FlyingBird) {
    bird.fly()
}

fun main() {
    val pigeon = Pigeon()
    letBirdFly(pigeon)
}