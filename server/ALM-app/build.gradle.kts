dependencies {
    implementation("global.genesis:file-server-app:${project.ext["file-serverVersion"]}")
    compileOnly(genesis("script-dependencies"))
    genesisGeneratedCode(withTestDependency = true)
    testImplementation(genesis("dbtest"))
    testImplementation(genesis("testsupport"))
    testImplementation(genesis("pal-eventhandler"))

    implementation("global.genesis:kafka-genesis:${properties["platformIntegrationVersion"]}")
    implementation("software.amazon.msk:aws-msk-iam-auth:2.2.0")
}

description = "ALM-app"

sourceSets {
    main {
        resources {
            srcDirs("src/main/resources", "src/main/genesis")
        }
    }
}
