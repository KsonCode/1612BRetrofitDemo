package com.bwie.retrofitdemo;

import com.bwie.retrofitdemo.entity.UserEntity;

import java.net.NoRouteToHostException;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class Test {
    static Disposable disposable;
    public static void main(String[] args){

        //第一步创建被观察者,创建操作符,事件流事件

        Observable observable = Observable.create(new ObservableOnSubscribe() {
            /**
             * 发送事件，事件是数据？就是对象
             * @param emitter
             * @throws Exception
             */
            @Override
            public void subscribe(ObservableEmitter emitter) throws Exception {

                UserEntity userEntity = new UserEntity();
                userEntity.message = "hello";
                emitter.onNext("a");
                emitter.onNext("b");
                emitter.onError(new NullPointerException());
                emitter.onNext("c");
                emitter.onNext("d");
                emitter.onNext("e");
                emitter.onNext("f");
                emitter.onNext(userEntity);

                emitter.onComplete();

            }
        });


        //创建观察者

        Observer observer = new Observer() {
            /**
             * 是否订阅成功
             * @param d
             */
            @Override
            public void onSubscribe(Disposable d) {
                if (d.isDisposed()){
                    System.out.println("订阅失败");
                }else{
                    System.out.println("订阅成功");
                }


            }

            /**
             * 接收，消费
             * @param o
             */
            @Override
            public void onNext(Object o) {

                System.out.println(o);


            }

            @Override
            public void onError(Throwable e) {

                System.out.println("error"+e);

            }

            @Override
            public void onComplete() {

                System.out.println("消费完成");

            }
        };

        //建立订阅关系
        observable.subscribe(observer);


        //链式调用，注意不是责任链模式
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {

            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                disposable = d;
                disposable.dispose();//取消订阅关系

            }

            @Override
            public void onNext(String s) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

        Observable.just(1,2,3,4,5,6,7,8,9,10).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer integer) {
                System.out.println(integer);

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

        Observable.just("hello").subscribe(new Consumer<String>() {
            /**
             * @param s
             * @throws Exception
             */
            @Override
            public void accept(String s) throws Exception {

                System.out.println(s);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

            }
        });


    }


}
