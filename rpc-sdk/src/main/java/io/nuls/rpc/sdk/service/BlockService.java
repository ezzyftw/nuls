/**
 * MIT License
 *
 * Copyright (c) 2017-2018 nuls.io
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package io.nuls.rpc.sdk.service;

import io.nuls.rpc.sdk.entity.BlockDto;
import io.nuls.rpc.sdk.entity.RpcClientResult;
import io.nuls.rpc.sdk.utils.AssertUtil;
import io.nuls.rpc.sdk.utils.RestFulUtils;

import java.util.Map;

/**
 * @author Niels
 * @date 2017/11/1
 */
public class BlockService {
    private RestFulUtils restFul = RestFulUtils.getInstance();

    /**
     * @param hash：block hash
     * @return BlockHeader
     */
    public RpcClientResult getBlock(String hash) {
        AssertUtil.canNotEmpty(hash, "block hash cannot null!");
        RpcClientResult result = restFul.get("/block/" + hash, null);
        if (result.isSuccess()) {
            result.setData(new BlockDto((Map<String, Object>) result.getData(), true));
        }
        return result;
    }

    /**
     * @param height：block height
     * @return BlockHeader
     */
    public RpcClientResult getBlock(int height) {
        RpcClientResult result = restFul.get("/block/height/" + height, null);
        if (result.isSuccess()) {
            result.setData(new BlockDto((Map<String, Object>) result.getData(), true));
        }
        return result;
    }

    public RpcClientResult getBlockCount() {
        return restFul.get("/block/count", null);
    }

    public RpcClientResult getBestBlockHeight() {
        return restFul.get("/block/bestheight", null);
    }

    public RpcClientResult getBestBlockHash() {
        return restFul.get("/block/besthash", null);
    }

    public RpcClientResult getBlockHashByHeight(int height) {
        return restFul.get("/block/hash/height/" + height + "", null);
    }

    public RpcClientResult getBlockHeaderByHeight(int height) {
        RpcClientResult result = restFul.get("/block/header/height/" + height + "", null);
        if (result.isSuccess()) {
            result.setData(new BlockDto((Map<String, Object>) result.getData(), false));
        }
        return result;
    }

    public RpcClientResult getBlockHeaderByHash(int hash) {
        RpcClientResult result = restFul.get("/block/" + hash + "/header", null);
        if (result.isSuccess()) {
            result.setData(new BlockDto((Map<String, Object>) result.getData(), false));
        }
        return result;
    }

}
