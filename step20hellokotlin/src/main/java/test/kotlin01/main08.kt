package test.kotlin01

//정수 리스트
val nums= listOf<Int>(10, 5, 100, 65, 4, 9)

fun main() {
    println("first : ${nums.first()}")
    println("last : ${nums.last()}")
    println("max : ${nums.max()}")
    println("min : ${nums.min()}")

    var nums2 = mutableListOf<Int>()
    for(num in 1..10){
        nums2.add(num)
    }
    println(nums2)
    nums2.shuffle()
    println(nums2)
}