/*
 * Copyright 2015 Jin Kwon &lt;jinahya_at_gmail.com&gt;.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.jinahya.bit.codec;

import com.github.jinahya.bit.io.BitOutput;
import java.io.IOException;
import java.util.function.BiConsumer;
import org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement;

/**
 * An abstract class for implementing {@code BitCodec}.
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 * @param <T> value type parameter
 */
public abstract class NullableEncoder<T> extends Nullable
        implements BitEncoder<T> {

    @IgnoreJRERequirement
    public static <T> NullableEncoder<T> newInstance(
            final boolean nullable, final BitEncoder<? super T> encoder) {

        if (encoder == null) {
            throw new NullPointerException("null encoder");
        }

        return new NullableEncoder<T>(nullable) {

            @Override
            protected void encodeValue(final BitOutput output, final T value)
                    throws IOException {

                encoder.encode(output, value);
            }

        };
    }

    @IgnoreJRERequirement
    public static <T> NullableEncoder<T> newInstance(
            final boolean nullable,
            final BiConsumer<BitOutput, ? super T> consumer) {

        if (consumer == null) {
            throw new NullPointerException("null consumer");
        }

        return new NullableEncoder<T>(nullable) {

            @Override
            protected void encodeValue(final BitOutput output, final T value)
                    throws IOException {

                consumer.accept(output, value);
            }

        };
    }

    /**
     * Creates a new instance.
     *
     * @param nullable a flag for nullability of the value.
     */
    public NullableEncoder(final boolean nullable) {

        super(nullable);
    }

    /**
     * {@inheritDoc} This method optionally (by the value of {@link #nullable})
     * encodes additional 1-bit boolean flag and, if the value should be
     * encoded, invokes
     * {@link #encodeValue(com.github.jinahya.bit.io.BitOutput, java.lang.Object)}
     * with given input and value.
     *
     * @param output {@inheritDoc}
     * @param value {@inheritDoc}
     *
     * @throws IOException {@inheritDoc}
     */
    @Override
    public void encode(final BitOutput output, final T value)
            throws IOException {

        if (output == null) {
            throw new NullPointerException("null output");
        }

        if (!nullable && value == null) {
            throw new NullPointerException("null value");
        }

        if (nullable) {
            output.writeBoolean(value != null);
        }

        if (!nullable || value != null) {
            encodeValue(output, value);
        }
    }

    /**
     * Encodes specified value to given output.
     *
     * @param output the output
     * @param value the value to encode
     *
     * @throws IOException if an I/O error occurs.
     */
    protected abstract void encodeValue(BitOutput output, T value)
            throws IOException;

}
