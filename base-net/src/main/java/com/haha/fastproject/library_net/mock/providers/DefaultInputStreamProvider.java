package com.haha.fastproject.library_net.mock.providers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @author zhe.chen
 * @date 2021/5/17 14:39
 * Des:
 */
public class DefaultInputStreamProvider implements InputStreamProvider {
    @Override
    public InputStream provide(String path) {
        try {
            return new FileInputStream(new File(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}