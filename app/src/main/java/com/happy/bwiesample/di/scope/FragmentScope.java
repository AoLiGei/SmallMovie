package com.happy.bwiesample.di.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * @Describtion
 * @Author LiAng
 * @Date 2017/12/14
 * @Time 11:43
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface FragmentScope {
}
