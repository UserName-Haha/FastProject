package com.haha.fastproject.library_net.mock.helpers;


import com.haha.fastproject.library_net.mock.providers.InputStreamProvider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author zhe.chen
 * @date 2021/5/17 14:38
 * Des:
 */
public class ResourcesHelper {
    public static String loadFileAsString(InputStreamProvider inputStreamProvider, String name) {
        BufferedReader bufferedReader = null;
        try {
            StringBuilder stringBuilder = new StringBuilder();
            InputStream inputStream = inputStreamProvider.provide(name);
            if (inputStream == null) {
                return null;
            }
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String str;
            boolean isFirst = true;
            while ((str = bufferedReader.readLine()) != null) {
                if (isFirst)
                    isFirst = false;
                else
                    stringBuilder.append('\n');
                stringBuilder.append(str);
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            System.out.print("JsonMockServer: Error opening asset " + name);
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    System.out.print("JsonMockServer: Error closing asset " + name);
                }
            }
        }
        return null;
    }
}
