package com.example.administrator.rxjavademo;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Observer<String> observer = new Observer<String>() {
            @Override
            public void onCompleted() {
                Log.i("TAG","Completed");
            }

            @Override
            public void onError(Throwable e) {
                Log.i("TAG","Error");
            }

            @Override
            public void onNext(String s) {
                Log.i("TAG",s);
            }
        };
        Observable observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Hello");
                subscriber.onNext("World");
                subscriber.onCompleted();
            }
        });
        observable.subscribe(observer);
        //Observable 的几种创建方法
        Observable.just("Hello","World").subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.i("TAG",s);
            }
        });

        //方法二
        Observable observable1 = Observable.just("Hello","World");
        Action1<String> onNextAction = new Action1<String>() {
            @Override
            public void call(String s) {
                Log.i("TT",s);
            }
        };
        Action1<Throwable> onErrorAction = new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {

            }
        };
        Action0 onCompletedAction = new Action0() {
            @Override
            public void call() {
             Log.i("TAD","Completed");
            }
        };
        Observable.just("Your","World").subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
           Log.i("TT",s);
            }
        });
        Observable.just("Your","World").map(new Func1<String, Integer>() {
            @Override
            public Integer call(String s) {
                return s.hashCode();
            }
        }).map(new Func1<Integer, Object>() {
            @Override
            public Object call(Integer integer) {
                return null;
            }
        }).subscribe(new Action1<Object>() {
            @Override
            public void call(Object o) {

            }
        });


        //使用subscribe重载方法
        Observable.just("Hello","World").subscribe(onNextAction);
        Observable.just("Hello","World").subscribe(onNextAction,onErrorAction);
        Observable.just("Hello","World").subscribe(onNextAction,onErrorAction,onCompletedAction);

        String [] words = {"Hello","World"};
        Observable observable2 = Observable.from(words);

        List<String> list = new ArrayList<>();
        list.add("Hello");
        list.add("World");
        Observable observable3 = Observable.from(list);
    }




}

