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

import java.math.BigInteger;

/**
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public class BigIntegerCodec_1 extends BridgeCodec<BigInteger, byte[]> {

    public BigIntegerCodec_1(final boolean nullable, final int scale) {

        super(new ByteArrayCodec(nullable, scale, false, 8));
    }

    @Override
    protected BigInteger convertFrom(final byte[] u) {

        return new BigInteger(u);
    }

    @Override
    protected byte[] convertTo(final BigInteger t) {

        return t.toByteArray();
    }

}
