pipelineJob('Your App Pipeline') {

  def repo = 'https://github.com/andrewjkrull/docker-demo'
  def sshRepo = 'git@github.com:andrewjkrull/docker-demo.git'

  description("Pipeline running in Containers")
  keepDependencies(false)

  properties{

    githubProjectUrl (repo)
    rebuild {
      autoRebuild(false)
    }
  }

  definition {

    cpsScm {
      scm {
        git {
          remote { url(sshRepo) }
          branches('master')
          scriptPath('misc/Jenkinsfile.v2')
          extensions { }  // required as otherwise it may try to tag the repo, which you may not want
        }
      }
    }
  }
}
