pipelineJob('Your App Pipeline') {

  def repo = 'https://github.com/andrewjkrull/docker-demo'
  def sshRepo = 'git@github.com:andrewjkrull/docker-demo.git'

  description("Pipeline running in Containers")
  keepDependencies(false)

  properties{

    pipelineTriggers {
      pollSCM('H/2 * * * *')
    }
    githubProjectUrl (repo)
    rebuild {
      autoRebuild(false)
    }
  }

  definition {

    cpsScm {
      scm {
        git {
          remote { url(repo) }
          branches('master')
          scriptPath('misc/Jenkinsfile.v2')
          extensions { }  // required as otherwise it may try to tag the repo, which you may not want
        }
      }
    }
  }
}
