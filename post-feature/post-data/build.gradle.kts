apply {
    from("$rootDir/base-module.gradle")
}

dependencies {
    "implementation"(project(ModulesConfig.core))
    "implementation"(project(ModulesConfig.postDomain))

    "implementation"(Retrofit.okHttp)
    "implementation"(Retrofit.retrofit)
    "implementation"(Retrofit.okHttpLoggingInterceptor)
    "implementation"(Retrofit.moshiConverter)

    "kapt"(Room.roomCompiler)
    "implementation"(Room.roomKtx)
    "implementation"(Room.roomRuntime)

}