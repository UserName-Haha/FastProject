package com.haha.fastproject.library_net.mock.providers;

import java.io.InputStream;

/**
 * @author zhe.chen
 * @date 2021/5/17 14:39
 * Des:
 */
public interface InputStreamProvider {
    InputStream provide(String path);
}
