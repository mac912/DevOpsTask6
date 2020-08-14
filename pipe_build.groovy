buildPipelineView('DevOpsTask6') {
    filterBuildQueue()
    filterExecutors()
    title('DevOpsTask6 build Pipeline')
    displayedBuilds(1)
    selectedJob('seed_job')
    alwaysAllowManualTrigger()
    showPipelineParameters()
    refreshFrequency(60)
}
