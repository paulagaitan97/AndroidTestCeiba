apply {
    from("$rootDir/base-module.gradle")
}

dependencies {

    "implementation"(project(ModulesConfig.core))

    "implementation"(Coroutines.coroutines)
}