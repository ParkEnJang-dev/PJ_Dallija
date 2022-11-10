pipeline {
    agent any

    stages {
        stage('Change application.yml') {
            steps {
                sh '''
                    cp /home/application.yml /var/jenkins_home/workspace/dallija/src/main/resources
                '''
                echo 'move properties complete'
            }
        }
        stage('Gradle Build') {
            steps {
               sh './gradlew clean bootJar'
               echo 'build complete'
            }
        }

        stage('Deploy') {
            steps([$class: 'BapSshPromotionPublisherPlugin']) {
                sshPublisher(
                    continueOnError: false, failOnError: true,
                    publishers: [
                        sshPublisherDesc(
                            configName: "dallija_spring",
                            verbose: true,
                            transfers: [
                                sshTransfer(
                                    sourceFiles: "build/libs/*.jar",
                                    removePrefix: "build/libs",
                                    remoteDirectory: "/home/spring",
                                    execCommand: "sh /root/home/deploy.sh"
                                )
                            ]
                        )
                    ]
                )
            }
        }
    }
}
