package com.vixiloc.vixgpt.di

import com.vixiloc.vixgpt.domain.use_case.ClearChats
import com.vixiloc.vixgpt.domain.use_case.GetAllChats
import com.vixiloc.vixgpt.domain.use_case.GetAllSettings
import com.vixiloc.vixgpt.domain.use_case.InsertChat
import com.vixiloc.vixgpt.domain.use_case.SendChat
import com.vixiloc.vixgpt.domain.use_case.UpdateSettings
import com.vixiloc.vixgpt.domain.use_case.ValidateQuestion
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val useCaseModule = module {
    single {
        GetAllChats(repository = get())
    }
    single {
        GetAllSettings(repository = get())
    }
    single {
        SendChat(repository = get())
    }
    single {
        UpdateSettings(repository = get())
    }
    single {
        ValidateQuestion(context = androidContext())
    }
    single {
        InsertChat(repository = get())
    }
    single {
        ClearChats(repository = get())
    }
}