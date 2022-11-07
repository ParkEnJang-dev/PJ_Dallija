pipeline {
    agent any

    stages {
        stage('Hello') {
            steps {
                sh 'java -version'
                echo 'Hello World'
            }
        }
        stage('Change application.yml') {
            steps {
                sh '''
                    cp /home/application-dev.yml /var/jenkins_home/workspace/dallija/src/main/resources
                '''
                echo 'move properties complete'
            }
        }
        stage('Gradle Build') {
            steps {
               sh './gradlew clean bootJar'
               echo 'move properties complete'
            }
        }
    }
}
