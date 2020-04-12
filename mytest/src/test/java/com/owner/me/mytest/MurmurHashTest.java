package com.owner.me.mytest;

import effective.MurmurHash;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.concurrent.atomic.LongAdder;

public class MurmurHashTest {
    public static void main(String[] args) {

        //LongAdder
        System.out.println(MurmurHash.hashUnsigned("chenshuo").toString().equalsIgnoreCase("5016608279269930526"));
        assertThat(MurmurHash.hashUnsigned("chenshuo").toString()).isEqualTo("5016608279269930526");
        assertThat(MurmurHash.hashUnsigned("shaoguoqing").toString()).isEqualTo("17121371936686143062");
        assertThat(MurmurHash.hashUnsigned("baozenghui").toString()).isEqualTo("5451996895512824982");
        assertThat(MurmurHash.hashUnsigned("05ff62ff6f7749ffff43ffff6673ff65").toString()).isEqualTo("10652549470333968609");
        assertThat(MurmurHash.hashUnsigned("hahahaha").toString()).isEqualTo("15134676900169534748");
        assertThat(MurmurHash.hashUnsigned("hahah1369531321aha5466sfdfaerttedddd56da").toString()).isEqualTo("6439159232526071817");
        assertThat(MurmurHash.hashUnsigned("测试汉字").toString()).isEqualTo("1146745369200541601");
        try {
            assertThat(MurmurHash.hashUnsigned("1234566大大21".getBytes("GBK")).toString()).isEqualTo("10129762727109086067");
            assertThat(MurmurHash.hashUnsigned("12345665哦4哦3我的妈呀21".getBytes("GBK")).toString()).isEqualTo("5141842319936259217");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

}
