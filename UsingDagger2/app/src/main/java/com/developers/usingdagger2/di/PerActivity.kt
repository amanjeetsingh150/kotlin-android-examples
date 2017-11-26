package com.developers.usingdagger2.di

import java.lang.annotation.RetentionPolicy
import javax.inject.Qualifier
import javax.inject.Scope

/**
 * Created by Amanjeet Singh on 26/11/17.
 */
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class PerActivity