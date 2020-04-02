package test.kotlin01

fun main(){
    val map1 = mutableMapOf<String, String>()
    map1.put("house","집")
    map1.put("phone","전화기")
    map1.put("suger","설탕")

    for( (key, value) in map1){
        println("map1 ${key}:${value}")
    }

    println("----")

    for( key in map1.keys){
        println("map1 ${key}:${map1.get(key)}")
    }

}