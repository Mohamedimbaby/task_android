package com.task.instabug.network.mappers



fun String.ignoreSpecialCharacters(): String {
    return mapContent(this)
    }
 fun String.toStringList(): List<String> = this.trim().split("\\s+".toRegex())

fun mapContent(content: String): String{
    val wordsMap = ArrayList<String>()
    val contentList = content.split(">")

    contentList.filter { s ->
        if (s.isNotEmpty() && (s[0] in 'A'..'Z' || s[0] in 'a'..'z')
                && !s.startsWith("span") && !s.startsWith("body")
        ) {
            var line = s.split("<")[0].split(" ")
            for (word in line) {
                wordsMap.add(word)
            }
            true
        } else {
            false
        }
    }
    var result : String="";
    for (i in wordsMap){
        result+= "$i "
    }
return  result
}
