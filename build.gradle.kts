//plain.jar를 실행시키지 않기 위함
tasks.getByName<Jar>("jar") {
    enabled = false
}
