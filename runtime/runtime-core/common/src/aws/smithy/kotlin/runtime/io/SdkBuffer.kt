/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package aws.smithy.kotlin.runtime.io

/**
 * A collection of bytes in memory. Moving data from one buffer to another is fast.
 *
 * **Thread Safety** Buffer is NOT thread safe and should not be shared between threads without
 * external synchronization.
 */
public expect class SdkBuffer : SdkBufferedSource, SdkBufferedSink {
    public val size: Long

    public constructor()

    internal val inner: okio.Buffer

    internal constructor(buffer: okio.Buffer)

    override val buffer: SdkBuffer
    override fun write(source: ByteArray, offset: Int, limit: Int)
    override fun writeAll(source: SdkSource): Long
    override fun write(source: SdkSource, byteCount: Long)
    override fun writeUtf8(string: String, start: Int, endExclusive: Int)
    override fun writeByte(x: Byte)
    override fun writeShort(x: Short)
    override fun writeShortLe(x: Short)
    override fun writeInt(x: Int)
    override fun writeIntLe(x: Int)
    override fun writeLong(x: Long)
    override fun writeLongLe(x: Long)
    override fun flush()
    override fun emit()
    override fun write(source: SdkBuffer, byteCount: Long)
    override fun skip(byteCount: Long)
    override fun readByte(): Byte
    override fun readShort(): Short
    override fun readShortLe(): Short
    override fun readLong(): Long
    override fun readLongLe(): Long
    override fun readInt(): Int
    override fun readIntLe(): Int
    override fun readAll(sink: SdkSink): Long
    override fun read(sink: ByteArray, offset: Int, limit: Int): Int
    override fun readByteArray(): ByteArray
    override fun readByteArray(byteCount: Long): ByteArray
    override fun readUtf8(): String
    override fun readUtf8(byteCount: Long): String
    override fun peek(): SdkBufferedSource
    override fun exhausted(): Boolean
    override fun request(byteCount: Long): Boolean
    override fun require(byteCount: Long)
    override fun read(sink: SdkBuffer, limit: Long): Long
    override fun close()
}
