apply {
    from("$rootDir/compose-module.gradle")
}

dependencies {

    "implementation"(project(ModulesConfig.core))
    "implementation"(project(ModulesConfig.coreUi))
    "implementation"(project(ModulesConfig.postDomain))

}