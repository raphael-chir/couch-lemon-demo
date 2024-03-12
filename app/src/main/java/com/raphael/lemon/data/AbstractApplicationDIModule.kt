package com.raphael.lemon.data

import com.raphael.lemon.data.features.CouchThreadServices
import com.raphael.lemon.data.features.DefaultCouchThreadServices
import com.raphael.lemon.data.replicator.DefaultReplicatorServices
import com.raphael.lemon.data.replicator.ReplicatorServices
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AbstractApplicationDIModule {

    @Binds
    abstract fun bindReplicatorServices(impl: DefaultReplicatorServices): ReplicatorServices

    @Binds
    abstract fun bindCouchThreadServices(impl: DefaultCouchThreadServices): CouchThreadServices

}