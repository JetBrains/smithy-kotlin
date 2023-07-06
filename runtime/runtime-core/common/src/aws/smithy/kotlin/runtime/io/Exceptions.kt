/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package aws.smithy.kotlin.runtime.io

// WA for Default argument values inside expect declaration 'IOException' are not allowed if it is
// actualized via typealias. Possible fix is to remove default argument values in members:
//    expect constructor(message: String? = ...): IOException
// public actual typealias IOException = java.io.IOException
//public expect open class LocalIOException(message: String?, cause: Throwable?) : Exception {
//    public constructor(message: String?)
//}
public expect open class LocalIOException(message: String?, cause: Throwable?) : Exception {
    public constructor(message: String?)
}

public expect open class EOFException(message: String?) : LocalIOException

/**
 * Indicates attempt to write on a closed channel (i.e. [SdkByteWriteChannel.isClosedForWrite] == true)
 * that was closed without a cause. A _failed_ channel rethrows the original [SdkByteWriteChannel.close] cause
 * exception on send attempts.
 */
//public class ClosedWriteChannelException(newMessage: String?) : LocalIOException(newMessage)
