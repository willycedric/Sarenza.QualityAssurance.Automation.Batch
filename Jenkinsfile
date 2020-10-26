pipeline {
    agent 'vm-dev-test3'
    tools {
        maven 'Maven 3.6.3'
        jdk 'jdk8'
    }
    stages {
        stage ('Initialize') {
            steps {
                sh '''
                    echo "PATH = ${PATH}"
                    echo "M2_HOME = ${M2_HOME}"
                '''
            }
        }

        stage ('Build') {
            steps {
                sh 'mvn -Dbrowser=chrome_remote'
            }
            post {
                success {
                    junit 'target/surefire-reports/**/*.xml'
                }
            }
        }
    }
}