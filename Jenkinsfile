pipeline {
    agent {
        node {
            label 'TEST3'
        }
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

        stage ('Test') {
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