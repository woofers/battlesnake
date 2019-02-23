workflow "Test" {
  resolves = ["Build"]
  on = "pull_request"
}

action "Build" {
  uses = "MrRamych/gradle-actions@1.0"
  args = "build"
}
