package ru.mertsalovda.countries.extensions

/**
 * Получить коллекцию в виде строки [String], где элементы коллекции расопложены в виде колонки.
 *
 * @return строка без прямоугольных скобок и запятие заменены символом переноса строки.
 */
fun List<Any>.printInColumn(): String {
    return this.toString()
        .replace("[", "")
        .replace("]", "")
        .replace(", ", "\n")
        .trim()
}