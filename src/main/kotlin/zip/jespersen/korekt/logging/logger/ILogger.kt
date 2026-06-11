package zip.jespersen.korekt.logging.logger

interface ILogger {
    fun print(type: LogLevel, message: String)
}