package zip.jespersen.korekt.logging.logger

import zip.jespersen.korekt.extension.blue
import zip.jespersen.korekt.extension.gray
import zip.jespersen.korekt.extension.ifNull
import zip.jespersen.korekt.extension.red
import zip.jespersen.korekt.extension.reset
import zip.jespersen.korekt.extension.white
import zip.jespersen.korekt.extension.yellow
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun logger(name: String, logger: LoggerConfig.() -> Unit): LoggerConfig {
    val config = LoggerConfig(name)
    logger.invoke(config)
    return config
}

enum class LogLevel {
    INFO,
    TRACE,
    DEBUG,
    FATAL,
}

class LoggerConfig {

    var name: String
        private set
    var level = LogLevel.INFO
        private set
    private var customPrint = false
    private var _print: (
        type: LogLevel,
        message: String
    ) -> Unit = { _, _ -> }

    fun name(name: String) {
        this.name = name
    }

    fun name(level: LogLevel) {
        this.level = level
    }

    fun print(
        print: (
            type: LogLevel,
            message: String
        ) -> Unit
    ) {
        customPrint = true
        this._print = print
    }

    constructor(name: String) {
        this.name = name
    }

    fun make(): Logger {
        var logger = Logger(name)
        if (customPrint) {
            logger = Logger(name, level, _print)
        }
        return logger
    }
}

class Logger : ILogger {

    private var name: String
    private var level: LogLevel
    private var _print: (
        type: LogLevel,
        message: String
    ) -> Unit = { type, message ->
        val timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss"))
        println(
            "${
                when (type) {
                    LogLevel.DEBUG -> "DEBUG".blue()
                    LogLevel.TRACE -> "DEBUG".yellow()
                    LogLevel.INFO -> "INFO"
                    LogLevel.FATAL -> "FATAL".red()
                }
            }: $name"
        )
        println("${timestamp}: $message")
    }

    constructor(
        name: String
    ) {
        this.name = name
        this.level = LogLevel.INFO
    }

    constructor(
        name: String,
        level: LogLevel,
        print: (
            type: LogLevel,
            message: String
        ) -> Unit
    ) {
        this.name = name
        this.level = level
        this._print = print
    }

    fun info(message: String): Logger {
        print(
            LogLevel.INFO,
            message
        )
        return this
    }

    fun trace(message: String): Logger {
        print(
            LogLevel.TRACE,
            message.yellow()
        )
        return this
    }

    fun fatal(message: String): Logger {
        print(
            LogLevel.FATAL,
            message.red()
        )
        return this
    }

    fun debug(message: String): Logger {
        print(
            LogLevel.DEBUG,
            message.blue()
        )
        return this
    }

    override fun print(
        type: LogLevel,
        message: String,
    ) {
        _print.invoke(type, message)
    }

    fun message(
        message: String,
    ) {
        _print.invoke(level, message)
    }
}