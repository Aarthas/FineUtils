package com.arthas.postcard;

import java.util.ArrayList;

public class _Postcard {

    public static void navigate(final ArrayList<IInterceptor> interceptors, final Postcard postcard, final Action action) {

        final ILifeCircle iLifeCircle = postcard.getiLifeCircle();
        if (iLifeCircle != null)
            iLifeCircle.onStart(postcard);
        InterceptorService interceptorService = new InterceptorService() {
            @Override
            public void doInterceptions(Postcard postcard, InterceptorCallback callback) {
                if (postcard.isGreenChannel()) {
                    callback.onContinue(postcard);
                    return;
                }
                if (null != interceptors && interceptors.size() > 0) {
                    _excute(interceptors, 0, postcard, callback);

                } else {
                    callback.onContinue(postcard);
                }


            }
        };


        interceptorService.doInterceptions(postcard, new InterceptorCallback() {
            @Override
            public void onContinue(final Postcard postcard) {
                MainLooper.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (action != null)
                            action.process(postcard, postcard.getData());
                        if (iLifeCircle != null)
                            iLifeCircle.onComplete(postcard);
                    }
                });

            }

            @Override
            public void onInterrupt(Throwable exception) {
                if (postcard.getOnInterrupt() != null)
                    postcard.getOnInterrupt().onInterrupt(exception);
                if (iLifeCircle != null)
                    iLifeCircle.onComplete(postcard);
            }

            @Override
            public void onContinueData(Object object) {

            }
        });


    }

    private static void _excute(final ArrayList<IInterceptor> interceptors, final int index, final Postcard postcard, final InterceptorCallback callback) {

        if (postcard.isGreenChannel()) {
            callback.onContinue(postcard);
            return;
        }
        if (index < interceptors.size()) {
            final IInterceptor iInterceptor = interceptors.get(index);

            if (iInterceptor instanceof Interceptor) {
                Interceptor interceptor1 = (Interceptor) iInterceptor;
                interceptor1.preHandler(postcard, postcard.getData());
            }
            try {
                iInterceptor.process(postcard, new InterceptorCallback() {
                    @Override
                    public void onContinue(Postcard postcard) {

                        if (iInterceptor instanceof Interceptor) {
                            Interceptor interceptor1 = (Interceptor) iInterceptor;
                            Object data = postcard.getData();
                            interceptor1.afterCompletion(postcard, data);
                        }

                        _excute(interceptors, index + 1, postcard, callback);  // When counter is down, it will be execute continue ,but index bigger than interceptors size, then U know.

                    }

                    @Override
                    public void onInterrupt(Throwable exception) {
                        // Last interceptor excute over with fatal exception.

                        callback.onInterrupt(exception);
                    }

                    @Override
                    public void onContinueData(Object obj) {
                        _excute(interceptors, index + 1, postcard.withData(obj), callback);
                    }
                });

            } catch (Exception e) {
                callback.onInterrupt(e);
                e.printStackTrace();
            }
        } else {
            callback.onContinue(postcard);
        }

    }


}
