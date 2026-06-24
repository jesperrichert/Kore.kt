package zip.jespersen.korekt.extension

import java.io.File

fun File.copyToDir(dir: File): File {
    require(dir.isDirectory) { "Target must be a directory" }
    return resolve(dir.resolve(name)).also { copyTo(it, overwrite = true) }
}