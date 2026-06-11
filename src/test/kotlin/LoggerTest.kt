import zip.jespersen.korekt.logging.logger.logger

fun main() {
    val logger = logger("eier") {
    }.make()
    logger.debug("EIER?")
}