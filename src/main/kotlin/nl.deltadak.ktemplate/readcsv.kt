package nl.deltadak.ktemplate

import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import com.github.doyaaaaaken.kotlincsv.dsl.csvWriter
import java.io.File

/**
 * Example of reading a csv.
 */
fun main() {
    // Read all line by line, use ; for delimiter
    val reader = csvReader {
        delimiter = ';'
        charset = Charsets.ISO_8859_1.name()
    }
    val androidFile = File("src/main/resources/output/strings.xml")
    val iOSFile = File("src/main/resources/output/parameters.strings")
    var androidText = ""
    var swiftText = ""
    reader.open("src/main/resources/translations-1.16.0.csv") {
        readAll().forEach { row ->
            //Do something
            androidText += "<string name=\"${row[0]}\">${row[1]}</string>\n"
            swiftText += "\"${row[0]}\" = \"${row[1]}\";\n"
        }
    }
    androidFile.writeText(androidText)
    iOSFile.writeText(swiftText)
}