
/*
 * MIT License
 *
 * Copyright (c) 2017 Alibaba Group
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

package com.libra.virtualview.compiler.parser.biz;


import com.libra.Log;
import com.libra.TextUtils;
import com.libra.virtualview.common.BizCommon;
import com.libra.virtualview.compiler.parser.Parser;
import com.libra.virtualview.compiler.parser.ViewBaseParser;
/**
 * Created by jiangbin.jiangb on 2017/5/19.
 */

public class TMCommodityShowWindowViewParser extends ViewBaseParser {
    private final static String TAG = "TMShowWindowParser_TMTEST";

    private int windowHeightId;

    @Override
    public void init() {
        super.init();
        windowHeightId = mStringSupport.getStringId("windowHeight", true);
    }

    @Override
    public int convertAttribute(int key, AttrItem value) {
        int ret = super.convertAttribute(key, value);

        if (ViewBaseParser.CONVERT_RESULT_FAILED == ret) {
            ret = ViewBaseParser.CONVERT_RESULT_OK;

            if (key == windowHeightId) {
                if (null != value && !TextUtils.isEmpty(value.mStrValue)) {
                    if (!parseNumber(value)) {
                        ret = CONVERT_RESULT_FAILED;
                    }
                } else {
                    Log.e(TAG, "parse value invalidate:" + value);
                    ret = CONVERT_RESULT_ERROR;
                }
            } else {
                ret = CONVERT_RESULT_FAILED;
            }
        }

        return ret;
    }

    public  static class Builder implements  IBuilder{
        @Override
        public Parser build(String name) {
            if (TextUtils.equals(name, "TMCommodityShowWindowView")) {
                return new TMCommodityShowWindowViewParser();
            }
            return null;
        }
    }

    @Override
    public int getId() {
        return BizCommon.TM_COMMODITY_UPGRADE_SHOW_WINDOW_VIEW;
    }
}

