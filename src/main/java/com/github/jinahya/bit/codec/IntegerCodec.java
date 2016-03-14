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


import com.github.jinahya.bit.io.codec.IntegralCodec;
import com.github.jinahya.bit.io.BitInput;
import com.github.jinahya.bit.io.BitIoConstraints;
import com.github.jinahya.bit.io.BitOutput;
import java.io.IOException;


/**
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public class IntegerCodec extends IntegralCodec<Integer> {


    public IntegerCodec(final boolean nullable, final boolean unsigned,
                        final int size) {

        super(nullable, unsigned,
              BitIoConstraints.requireValidSize(unsigned, 5, size));
    }


    @Override
    protected Integer decodeValue(final BitInput input) throws IOException {

        return input.readInt(unsigned, size);
    }


    @Override
    protected void encodeValue(final BitOutput output, final Integer value)
        throws IOException {

        output.writeInt(unsigned, size, value);
    }

}

