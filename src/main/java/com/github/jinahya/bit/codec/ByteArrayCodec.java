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


import com.github.jinahya.bit.io.codec.ByteCodec;
import com.github.jinahya.bit.io.codec.ScaleCodec;
import com.github.jinahya.bit.io.BitInput;
import com.github.jinahya.bit.io.BitOutput;
import java.io.IOException;


/**
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public class ByteArrayCodec extends ScaleCodec<byte[], Byte> {


    public ByteArrayCodec(final boolean nullable, final int scale,
                          final boolean unsigned, final int size) {

        super(nullable, scale, new ByteCodec(false, unsigned, size));
    }


    @Override
    protected byte[] decodeValue(final BitInput input) throws IOException {

        final byte[] value = new byte[readCount(input)];

        for (int i = 0; i < value.length; i++) {
            value[i] = codec.decode(input);
        }

        return value;
    }


    @Override
    protected void encodeValue(final BitOutput output, final byte[] value)
        throws IOException {

        writeCount(output, value.length);

        for (final byte b : value) {
            codec.encode(output, b);
        }
    }

}

