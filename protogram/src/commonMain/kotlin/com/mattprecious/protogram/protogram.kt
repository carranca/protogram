@file:JvmName("Protogram")
package com.mattprecious.protogram

import com.mattprecious.tinsel.Node.Branch
import com.mattprecious.tinsel.Node.Leaf
import com.mattprecious.tinsel.Tree
import com.mattprecious.tinsel.render
import com.squareup.wire.FieldEncoding.FIXED32
import com.squareup.wire.FieldEncoding.FIXED64
import com.squareup.wire.FieldEncoding.LENGTH_DELIMITED
import com.squareup.wire.FieldEncoding.VARINT
import com.squareup.wire.ProtoReader
import okio.Buffer
import okio.ByteString
import kotlin.jvm.JvmName

fun printProto(bytes: ByteString): String {
  return bytes.readProtoTree().render()
}

internal fun ByteString.readProtoTree(): Tree {
  return ProtoReader(Buffer().write(this)).readTree()
}

private fun ProtoReader.readTree(): Tree {
  return generateFieldSequence()
      .map { (tag, encoding) ->
        val tagString = tag.toString()

        val nodeValue = when (encoding) {
          VARINT -> readVarint64().toString()
          FIXED64 -> {
            val long = readFixed64()
            val double = Double.fromBits(long)

          "$long ($double)"
        }
        FIXED32 -> readFixed32().toString()
        LENGTH_DELIMITED -> {
          val bytes = readBytes()

          if (bytes.size == 0) {
            "(empty)"
          } else {
            try {
              return@map Branch(tagString, bytes.readProtoTree().nodes)
            } catch (_: Exception) {
              "\"${bytes.utf8().escape()}\""
            }
          }
        }
        }

        return@map Leaf(tagString, nodeValue)
      }
      .toList()
      .let { Tree(it) }
}

private fun String.escape(): String {
  return replace("\n", "\\n")
}
