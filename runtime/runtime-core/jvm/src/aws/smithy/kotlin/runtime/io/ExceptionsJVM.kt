/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package aws.smithy.kotlin.runtime.io

// WA for Default argument values inside expect declaration 'IOException' are not allowed if it is
// actualized via typealias. Possible fix is to remove default argument values in members:
//    expect constructor(message: String? = ...): IOException
// public actual typealias IOException = java.io.IOException
public actual typealias LocalIOException = java.io.IOException

public actual typealias EOFException = java.io.EOFException
