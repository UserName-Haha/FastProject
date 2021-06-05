package com.haha.fastproject.library_net;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Function;

/**
 * @author zhe.chen
 * @date 2021/5/14 17:15
 * Des:
 */
public class HttpTransformer {

    public static <U, D> ObservableTransformer<U, D> exceptionTransformer() {

        return new ObservableTransformer<U, D>() {
            @Override
            public ObservableSource apply(Observable<U> observable) {
                return observable
                        .onErrorResumeNext(new HttpResponseFunc());
            }
        };
    }

    private static class HttpResponseFunc<T> implements Function<Throwable, Observable<T>> {
        @Override
        public Observable<T> apply(Throwable t) {
            return Observable.error(ExceptionHandle.handleException(t));
        }
    }

}
