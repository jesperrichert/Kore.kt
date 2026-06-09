package zip.jespersen.korekt.config

interface Configurable {
    fun save()
    fun load() {}
    fun reset() {}
}