package zip.jespersen.korekt.extension

import java.io.File
import java.io.FileOutputStream
import java.security.MessageDigest
import kotlin.io.encoding.Base64


fun ByteArray.toFile(fileName: String) {
    FileOutputStream(fileName).use { it.write(this) }
}

fun ByteArray.toFile(file: File) {
    file.outputStream().use { it.write(this) }
}

fun ByteArray.toBase64(): String = Base64.encode(this)

fun ByteArray.toHex(): String = joinToString("") { "%02x".format(it) }

fun ByteArray.md5(): String = MessageDigest.getInstance("MD5").digest(this).toHex()
fun ByteArray.sha256(): String = MessageDigest.getInstance("SHA-256").digest(this).toHex()